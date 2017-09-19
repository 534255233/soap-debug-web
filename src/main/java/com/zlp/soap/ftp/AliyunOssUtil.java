package com.zlp.soap.ftp;

import java.io.InputStream;
import java.util.Calendar;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class AliyunOssUtil {
	
	private static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	private static String accessKeyId = "wtiUZd71Qvb0zIsQ";
	private static String accessKeySecret = "aOywWxrs9kc33CrhABv4f10gDqEttY";
	private static String bucketName = "elsdocman";
	
	private static ClientConfiguration conf = new ClientConfiguration();
	private static OSSClient ossClient = null;
	
	public AliyunOssUtil(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
		AliyunOssUtil.endpoint = endpoint;
		AliyunOssUtil.accessKeyId = accessKeyId;
		AliyunOssUtil.accessKeySecret = accessKeySecret;
		AliyunOssUtil.bucketName = bucketName;
		conf.setIdleConnectionTime(1000 * 60 * 6);//6分钟
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
		Calendar c = Calendar.getInstance();
		pretime =  c.getTime().getTime() / 1000;
	}
	
	
	private static long pretime = 0l;
	
	public String uploadFile(String fileId, InputStream inputStream) {
		
		Calendar c = Calendar.getInstance();
		long nowtime =  c.getTime().getTime() / 1000;
		
		if ((nowtime - pretime) > 300) {//5分钟
			pretime = nowtime;
			synchronized(ossClient) {
				shutdown();
				if ((nowtime - pretime) > 300) {//5分钟
					ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
					pretime =  c.getTime().getTime() / 1000;
				}
			}
		}
		
		PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileId, inputStream);
		return putObjectResult.getRequestId();
	}
	
	public void shutdown() {
		ossClient.shutdown();
	}

}
