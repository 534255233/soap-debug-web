package com.zlp.test;

import java.io.File;
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
	
	@Test
	public void testConnectionFtp() {
		log.info("begin...");
		
		FTPClient ftp = new FTPClient();
		String host = "srmftp.minotech.cn";
		int port = 21;
		String username = "SRM";
		String password = "minosrm";
		
		try {
			
			
			ftp.setConnectTimeout(5000);
			ftp.setControlKeepAliveReplyTimeout(5000);
			ftp.setControlKeepAliveTimeout(5000);
			ftp.setDataTimeout(5000);
			
			ftp.connect(host, port);
			
			ftp.setSoTimeout(5000);
			
			ftp.login(username, password);
			
			log.info("ftp.isConnected() = {}", ftp.isConnected());
			
//			ftp.deleteFile(pathname)
			java.io.InputStream inputStream = ftp.retrieveFileStream("GR01P_UN101_005L.dwg");
			
			int l = inputStream.available();
			log.info("inputStream length = {}", l);
			
			File file = new File("/Users/zhoulongpeng/qiqitong/logs/ftp_file1.dwg");  
            byte[] bytes = new byte[1024];  
            int i;  
            FileOutputStream fos = new FileOutputStream(file);  
            while((i = inputStream.read(bytes)) != -1){  
                fos.write(bytes, 0, i);  
            }
            log.info("file length = {}", file.length());
            fos.close();
            
            String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
            String accessKeyId = "wtiUZd71Qvb0zIsQ";
            String accessKeySecret = "aOywWxrs9kc33CrhABv4f10gDqEttY";
            String bucketName = "elsdocman";
            log.info("1...");
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            log.info("2...");
            String fileId = "ftp_oss_file_id_1";
//            java.io.InputStream inputStream2 = null;
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileId, inputStream);
            
            log.info("oss StatusCode = {}", putObjectResult);
            
            ossClient.shutdown();
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("end...");
		
	}
	
	
	@Test
	public void testConnectionGetFile() {
		log.info("begin...");
		
			
            String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
            String accessKeyId = "wtiUZd71Qvb0zIsQ";
            String accessKeySecret = "aOywWxrs9kc33CrhABv4f10gDqEttY";
            String bucketName = "elsdocman";
            log.info("1...");
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            log.info("2...");
            String fileId = "ftp_oss_file_id_1";
            OSSObject ossObject = ossClient.getObject(bucketName, fileId);
            
            log.info("oss getContentLength = {}", ossObject.getObjectMetadata().getContentLength());
            
            ossClient.shutdown();
			
		
		log.info("end...");
		
	}
	
	
	@Test
	public void testTime() {
		Calendar c = Calendar.getInstance();
		long nowtime =  c.getTime().getTime();
		log.info("c.getTime().getTime() = {}", nowtime);
		long pretime = 1505820027880l;
		log.info("c.getTime().getTime() = {}", (nowtime - pretime) / 1000);
	}
	
	

}
