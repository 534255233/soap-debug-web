package com.zlp.soap.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class FtpClientUtil {
	
	private static FTPClient ftp = new FTPClient();
	
	private static String host = "srmftp.minotech.cn";
	private static int port = 21;
	private static String username = "SRM";
	private static String password = "minosrm";
	
	public FtpClientUtil(String host, int port, String username, String password) {
		FtpClientUtil.host = host;
		FtpClientUtil.port = port;
		FtpClientUtil.username = username;
		FtpClientUtil.password = password;
	}
	
	public void connected() throws SocketException, IOException {
		
		if (!ftp.isConnected()) {
			synchronized (ftp) {
				if (!ftp.isConnected()) {
					ftp.setConnectTimeout(5000);
					ftp.setControlKeepAliveReplyTimeout(5000);
					ftp.setControlKeepAliveTimeout(5000);
					ftp.setDataTimeout(5000);
					//
					ftp.connect(host, port);
					ftp.setSoTimeout(5000);
					//
					ftp.login(username, password);
				}
			}
		}
	}
	
	public InputStream downloadFileByFileName(String fileName) throws IOException {
		connected();
		InputStream inputStream = ftp.retrieveFileStream(fileName);
		return inputStream;
	}
	
	public boolean isConnected() {
		return ftp.isConnected();
	}

}
