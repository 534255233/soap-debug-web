package com.zlp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Calendar;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;

public class TestFtpCLient {

	private static Logger log = LoggerFactory.getLogger(TestFtpCLient.class);

	String fileId = "310000/D0000011Z|&&|1505896584395.gif";

	FTPClient ftp = new FTPClient();
	String host = "srmftp.minotech.cn";
	int port = 21;
	String username = "SRM";
	String password = "minosrm";

	String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	String accessKeyId = "wtiUZd71Qvb0zIsQ";
	String accessKeySecret = "aOywWxrs9kc33CrhABv4f10gDqEttY";
	String bucketName = "elsdocman";

	@Test
	public void testConnectionFtp() {
		log.info("begin...");

		try {

			ftp.setConnectTimeout(5000);
			ftp.setControlKeepAliveReplyTimeout(5000);
			ftp.setControlKeepAliveTimeout(5000);
			ftp.setDataTimeout(5000);

			ftp.connect(host, port);

			ftp.setSoTimeout(5000);

			ftp.login(username, password);

			log.info("ftp.isConnected() = {}", ftp.isConnected());

			// ftp://srmftp.minotech.cn/BS_010_GR01P_UN101_001L.dwg

			// ftp.deleteFile(pathname)
			java.io.InputStream inputStream = ftp.retrieveFileStream("BS_010_GR01P_UN101_001L.dwg");

			int l = inputStream.available();
			log.info("inputStream length = {}", l);

			File file = new File("/Users/zhoulongpeng/qiqitong/logs/ftp_file1.dwg");
			byte[] bytes = new byte[1024];
			int i;
			FileOutputStream fos = new FileOutputStream(file);
			while ((i = inputStream.read(bytes)) != -1) {
				fos.write(bytes, 0, i);
			}
			log.info("file length = {}", file.length());

			fos.close();
			inputStream.close();
			
			log.info("1...");
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			log.info("2...");
			
			// java.io.InputStream inputStream2 = null;
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileId, new FileInputStream(file));


			log.info("oss StatusCode = {}", putObjectResult.getRequestId());

			ossClient.shutdown();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("end...");

	}
	
	@Test
	public void testDeleteFtpFile() throws SocketException, IOException {
		log.info("begin...");
		
		ftp.setConnectTimeout(5000);
		ftp.setControlKeepAliveReplyTimeout(5000);
		ftp.setControlKeepAliveTimeout(5000);
		ftp.setDataTimeout(5000);

		ftp.connect(host, port);

		ftp.setSoTimeout(5000);

		ftp.login(username, password);

		log.info("ftp.isConnected() = {}", ftp.isConnected());

		//ftp://srmftp.minotech.cn/BS_010_GR01P_UN101_001L.dwg
		String pathname = "BS_010_GR01P_UN101_001L.dwg";
		
		java.io.InputStream inputStream = ftp.retrieveFileStream(pathname);
		int l = inputStream.available();
		log.info("inputStream length = {}", l);
		
		inputStream.close();
		
		ftp.completePendingCommand();
		
		boolean del = ftp.deleteFile(pathname);
		log.info("delete = {}", del);
		
		log.info("end...");
	}

	@Test
	public void testConnectionGetFile() {
		log.info("begin...");

		log.info("1...");
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		log.info("2...");
		// String fileId = "310000/MWKA-1701-002-01-004|&&|1505892930263.dwg";
		OSSObject ossObject = ossClient.getObject(bucketName, fileId);

		log.info("oss getContentLength = {}", ossObject.getObjectMetadata().getContentLength());

		ossClient.shutdown();

		log.info("end...");

	}

	@Test
	public void testTime() {
		Calendar c = Calendar.getInstance();
		long nowtime = c.getTime().getTime();
		log.info("c.getTime().getTime() = {}", nowtime);
		long pretime = 1505820027880l;
		log.info("c.getTime().getTime() = {}", (nowtime - pretime) / 1000);

		String fileName = "310000/MWKA-1701-001-01-004|&&|1505888444306.dwg";

		int in = fileName.indexOf("|&&|");

		log.info(fileName.substring(7, fileName.length()));
		log.info(fileName.substring(in, fileName.length()).replace("|&&|", ""));

		String localFileName = fileName.substring(fileName.indexOf("|&&|"), fileName.length()).replace("|&&|", "");
		log.info(localFileName);

	}

}
