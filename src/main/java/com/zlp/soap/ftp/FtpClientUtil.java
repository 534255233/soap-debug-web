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
	private static boolean isDeleteFtpFile = false;
	
	public FtpClientUtil(String host, int port, String username, String password, boolean isDeleteFtpFile) {
		FtpClientUtil.host = host;
		FtpClientUtil.port = port;
		FtpClientUtil.username = username;
		FtpClientUtil.password = password;
		FtpClientUtil.isDeleteFtpFile = isDeleteFtpFile;
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
		
		try {
			connected();
			InputStream inputStream = ftp.retrieveFileStream(fileName);
			return inputStream;
		} catch(java.net.SocketException e) {
			e.printStackTrace();
			isConnected();
		}
		return null;
	}
	
	public boolean isConnected() {
		return ftp.isConnected();
	}
	
	public void completeDownload() throws IOException {
		ftp.completePendingCommand();
	}
	
	public void deleteFtpFile(String pathname) throws IOException {
		
		if (isDeleteFtpFile) {
			try {
				connected();
				ftp.deleteFile(pathname);
			} catch(java.net.SocketException e) {
				e.printStackTrace();
				isConnected();
			}
		}
		
	}

}
