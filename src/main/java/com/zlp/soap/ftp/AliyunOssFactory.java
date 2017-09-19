package com.zlp.soap.ftp;

import java.util.ResourceBundle;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class AliyunOssFactory {
	
	private final static String CONF_FILE_NAME = "aliyun_oss_conf";

	private static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	private static String accessKeyId = "wtiUZd71Qvb0zIsQ";
	private static String accessKeySecret = "aOywWxrs9kc33CrhABv4f10gDqEttY";
	private static String bucketName = "elsdocman";

	static {
		ResourceBundle rb = ResourceBundle.getBundle(CONF_FILE_NAME);
		endpoint = rb.getString("aliyun.oss.endpoint");
		accessKeyId = rb.getString("aliyun.oss.accessKeyId");
		accessKeySecret = rb.getString("aliyun.oss.accessKeySecret");
		bucketName = rb.getString("aliyun.oss.bucketName");
	}
	
	private static AliyunOssUtil aliyunOssUtil = new AliyunOssUtil(endpoint, accessKeyId, accessKeySecret, bucketName);
	
	public static AliyunOssUtil getAliyunOssUtil() {
		return aliyunOssUtil;
	}

}
