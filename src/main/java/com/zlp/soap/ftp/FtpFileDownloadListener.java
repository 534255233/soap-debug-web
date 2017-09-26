package com.zlp.soap.ftp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Calendar;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;

/**
 * 
 * @author zhoulongpeng
 * @date 2017-09-19
 */
@Service
public class FtpFileDownloadListener implements ApplicationContextAware {

	private static Logger log = LoggerFactory.getLogger(FtpFileDownloadListener.class);

	// private static LinkedBlockingQueue<FileDownloadItem> queue = new
	// LinkedBlockingQueue<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		// 从数据库找出以前失败的记录
		log.info("find need to download file record.");

		// Runnable run = new Runnable() {
		// FileDownloadItem item = null;
		//
		// @Override
		// public void run() {
		// while(true) {
		// try {
		// item = queue.take();
		//
		// long downloadTime = item.getNextDownloadTime();
		//
		// Calendar c = Calendar.getInstance();
		// long currentTime = c.getTime().getTime() / 1000;
		//
		// //3秒后再去FTP服务器拿文件
		// if ((currentTime - downloadTime ) < 3) {
		// queue.offer(item);
		// continue;
		// }
		//
		// String fileId = item.getFileId();
		// String fileName = item.getFileName();
		//
		// log.info("begin download file from FTP server, fileName = {}, fileId
		// = {}", fileName, fileId);
		//
		// /*
		// * 1.检查该记录是否存在数据库
		// * 没有的话，立即增加一条记录
		// * 否则，更新该记录
		// */
		//
		// //2.从FTP获取文件
		// FtpClientUtil ftpClient = FtpClientFactory.getFtpClientUtil();
		// InputStream fileStream = ftpClient.downloadFileByFileName(fileName);
		//
		// boolean fileDealSuccess = false;
		//
		// if (fileStream == null) {
		// log.info("file not find in FTP server, fileName = {}, downloadTimes =
		// {}", fileName, item.getDownloadTimes());
		// item.setDownloadTimes(item.getDownloadTimes()+1);
		// item.setNextDownloadTime(currentTime);
		// queue.offer(item);
		// } else {
		// log.info("begin upload file to aliyun. fileName = {}, downloadTimes =
		// {}", fileName, item.getDownloadTimes());
		//
		// //3.上传文件到阿里云
		// AliyunOssUtil aliyunOss = AliyunOssFactory.getAliyunOssUtil();
		// String requestId = aliyunOss.uploadFile(fileId, fileStream);
		//
		// log.info("aliyun oss return the requestId: {}", requestId);
		// fileDealSuccess = true;
		// }
		//
		// if (fileStream != null) {
		// fileStream.close();
		// ftpClient.completeDownload();
		// }
		//
		// //4.记下文件状态，同时删除FTP服务器的文件
		// if (fileDealSuccess) {
		// log.info("file deal success.");
		// ftpClient.deleteFtpFile(fileName);
		// } else {
		// log.info("file deal fail.");
		// }
		//
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// queue.offer(item);
		// } catch (IOException e) {
		// e.printStackTrace();
		// queue.offer(item);
		// }
		// }
		//
		// }
		// };
		// new Thread(run).start();
	}

	/**
	 * 
	 * @param item
	 */
	public void offer(FileDownloadItem item) {
		// queue.offer(item);
	}

	/**
	 * 
	 * @param item
	 */
	public void offer(final List<FileDownloadItem> items) {
		if (items == null || items.isEmpty()) {
			log.error("sap file is empty.");
			return;
		}

		Runnable run = new Runnable() {
			@Override
			public void run() {
				downloadFile(items);
			}
		};
		new Thread(run).start();

	}

	private void downloadFile(List<FileDownloadItem> items) {
		// 1.连接FTP服务器
		FtpClientUtil ftpClient = null;
		try {
			ftpClient = FtpClientFactory.getFtpClientUtil();
		} catch (SocketException e) {
			e.printStackTrace();
			log.error("connected FTP server error.{}", e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("connected FTP server error.{}", e);
		}

		if (ftpClient == null || ftpClient.isConnected() == false) {
			log.error("FTP server not connected.");
			return;
		}

		// 2.连接阿里云oss
		AliyunOssUtil aliyunOss = AliyunOssFactory.getAliyunOssUtil();
		if (aliyunOss == null) {
			log.error("aliyun oss not connected.");
			return;
		}

		Queue<FileDownloadItem> queue = new ConcurrentLinkedQueue<>();
		for (FileDownloadItem item : items) {
			queue.offer(item);
		}

		// 2.上传文件到阿里云oss
		FileDownloadItem item = null;
		while ((item = queue.poll()) != null) {
			long downloadTime = item.getNextDownloadTime();

			Calendar c = Calendar.getInstance();
			long currentTime = c.getTime().getTime() / 1000;

			// 3秒后再去FTP服务器拿文件
			if ((currentTime - downloadTime) < 6) {
				queue.offer(item);
				continue;
			}

			String fileId = item.getFileId();
			String fileName = item.getFileName();
			log.info("begin download file from FTP server, fileName = {}, fileId = {}", fileName, fileId);

			InputStream fileStream = null;
			try {
				fileStream = ftpClient.downloadFileByFileName(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			boolean fileDealSuccess = false;

			if (fileStream == null) {
				log.info("file not find in FTP server, fileName = {}, downloadTimes = {}", fileName,
						item.getDownloadTimes());
				item.setDownloadTimes(item.getDownloadTimes() + 1);
				if (item.getDownloadTimes() < 10) {
					item.setNextDownloadTime(currentTime);
					queue.offer(item);
				} else if (item.getDownloadTimes() == 10) {
					item.setNextDownloadTime(currentTime + 600);
					queue.offer(item);
				}
			} else {
				log.info("begin upload file to aliyun. fileName = {}, downloadTimes = {}", fileName,
						item.getDownloadTimes());

				String requestId = null;
				try {
					requestId = aliyunOss.uploadFile(fileId, fileStream);
				} catch (OSSException e) {
					e.printStackTrace();
					log.error("uploadFile, {}", e);
					queue.offer(item);
				} catch (ClientException e) {
					e.printStackTrace();
					log.error("uploadFile, {}", e);
					queue.offer(item);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					log.error("uploadFile, {}", e);
					queue.offer(item);
				}
				log.info("aliyun oss return the requestId: {}", requestId);
				fileDealSuccess = true;
			}

			try {
				if (fileStream != null)
					fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("file stream close, {}", e);
			}
			try {
				if (ftpClient != null)
					ftpClient.completeDownload();
				ftpClient.deleteFtpFile(fileName);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("FTP download file finish, {}", e);
			}

			// 4.记下文件状态，同时删除FTP服务器的文件
			if (fileDealSuccess) {
				log.info("file deal success. fileName = {}, fileId = {}", fileName, fileId);
			} else {
				log.info("file deal fail, fileName = {}, fileId = {}", fileName, fileId);
			}
		}

		// 5. 文件处理完成，关闭连接
		try {
			ftpClient.disconnect();
			aliyunOss.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("关闭连接, {}", e);
		}
	}

}
