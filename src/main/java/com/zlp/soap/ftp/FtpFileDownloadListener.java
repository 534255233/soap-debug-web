package com.zlp.soap.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class FtpFileDownloadListener implements ApplicationContextAware {
	
	private static Logger log = LoggerFactory.getLogger(FtpFileDownloadListener.class);
	
	private static LinkedBlockingQueue<Map<String, String>> queue = new LinkedBlockingQueue<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		//从数据库找出以前失败的记录
		
		Runnable run = new Runnable() {
			Map<String, String> map = null;

			@Override
			public void run() {
				while(true) {
					try {
						map = queue.take();
						Map.Entry<String, String> entry = map.entrySet().iterator().next();
						String fileId = entry.getKey();
						String fileName = entry.getValue();
						
						/*
						 * 1.检查该记录是否存在数据库
						 * 没有的话，立即增加一条记录
						 * 否则，更新该记录
						 */
						
						//2.从FTP获取文件
						FtpClientUtil ftpClient = FtpClientFactory.getFtpClientUtil();
						InputStream fileStream = ftpClient.downloadFileByFileName(fileName);
						
						if (fileStream == null) {
							log.info("file not find in ftp, file name is {}", fileName);
							
							queue.offer(map);
						} else {
							//3.上传文件到阿里云
							AliyunOssUtil aliyunOss = AliyunOssFactory.getAliyunOssUtil();
							String requestId = aliyunOss.uploadFile(fileId, fileStream);
							
							log.info(requestId);
						}
						
						if (fileStream != null)  {
							fileStream.close();
						}
						
						//4.记下文件状态
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		new Thread(run).start();
	}
	
	public void offer(Map<String, String> map) {
		queue.offer(map);
	}

}
