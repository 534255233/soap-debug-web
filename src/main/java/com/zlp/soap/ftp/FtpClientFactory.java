package com.zlp.soap.ftp;

import java.io.IOException;
import java.net.SocketException;
import java.util.ResourceBundle;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class FtpClientFactory {
	
	private final static String CONF_FILE_NAME = "ftp";

	private static String host = "srmftp.minotech.cn";
	private static int port = 21;
	private static String username = "SRM";
	private static String password = "minosrm";
	
	private static boolean isDeleteFtpFile = false;

	static {
		ResourceBundle rb = ResourceBundle.getBundle(CONF_FILE_NAME);
		host = rb.getString("ftp.server.host");
		port = Integer.parseInt(rb.getString("ftp.server.port"));
		username = rb.getString("ftp.server.username");
		password = rb.getString("ftp.server.password");
		isDeleteFtpFile = Boolean.parseBoolean(rb.getString("ftp.server.delete.file"));
	}
	
//	private static FtpClientUtil ftpClientUtil = new FtpClientUtil(host, port, username, password, isDeleteFtpFile);
	
	public static FtpClientUtil getFtpClientUtil() throws SocketException, IOException {
		return new FtpClientUtil(host, port, username, password, isDeleteFtpFile);
	}

}
