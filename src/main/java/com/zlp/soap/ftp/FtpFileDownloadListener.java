package com.zlp.soap.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
@Service
public class FtpFileDownloadListener implements ApplicationContextAware {
	
	private static Logger log = LoggerFactory.getLogger(FtpFileDownloadListener.class);
	
	private static LinkedBlockingQueue<FileDownloadItem> queue = new LinkedBlockingQueue<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		//从数据库找出以前失败的记录
		log.info("find need to download file record.");
		
		Runnable run = new Runnable() {
			FileDownloadItem item = null;

			@Override
			public void run() {
				while(true) {
					try {
						item = queue.take();
						
						long downloadTime = item.getNextDownloadTime();
						
						Calendar c = Calendar.getInstance();
						long currentTime =  c.getTime().getTime() / 1000;
						
						//3秒后再去FTP服务器拿文件
						if ((currentTime - downloadTime ) < 3) {
							queue.offer(item);
							continue;
						}
						
						String fileId = item.getFileId();
						String fileName = item.getFileName();
						
						log.info("begin download file from FTP server, fileName = {}, fileId = {}", fileName, fileId);
						
						/*
						 * 1.检查该记录是否存在数据库
						 * 没有的话，立即增加一条记录
						 * 否则，更新该记录
						 */
						
						//2.从FTP获取文件
						FtpClientUtil ftpClient = FtpClientFactory.getFtpClientUtil();
						InputStream fileStream = ftpClient.downloadFileByFileName(fileName);
						
						boolean fileDealSuccess = false;
						
						if (fileStream == null) {
							log.info("file not find in FTP server, fileName = {}, downloadTimes = {}", fileName, item.getDownloadTimes());
							item.setDownloadTimes(item.getDownloadTimes()+1);
							item.setNextDownloadTime(currentTime);
							queue.offer(item);
						} else {
							log.info("begin upload file to aliyun. fileName = {}, downloadTimes = {}", fileName, item.getDownloadTimes());
							
							//3.上传文件到阿里云
							AliyunOssUtil aliyunOss = AliyunOssFactory.getAliyunOssUtil();
							String requestId = aliyunOss.uploadFile(fileId, fileStream);
							
							log.info("aliyun oss return the requestId: {}", requestId);
							fileDealSuccess = true;
						}
						
						if (fileStream != null)  {
							fileStream.close();
							ftpClient.completeDownload();
						}
						
						//4.记下文件状态，同时删除FTP服务器的文件
						if (fileDealSuccess) {
							log.info("file deal success.");
							ftpClient.deleteFtpFile(fileName);
						} else {
							log.info("file deal fail.");
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
						queue.offer(item);
					} catch (IOException e) {
						e.printStackTrace();
						queue.offer(item);
					}
				}
				
			}
		};
		new Thread(run).start();
	}
	
	/**
	 * 
	 * @param item
	 */
	public void offer(FileDownloadItem item) {
		queue.offer(item);
	}

}
