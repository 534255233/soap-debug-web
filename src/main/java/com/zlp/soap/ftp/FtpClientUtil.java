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
	
	private FTPClient ftp = new FTPClient();
	
	private String host = "srmftp.minotech.cn";
	private int port = 21;
	private String username = "SRM";
	private String password = "minosrm";
	private boolean isDeleteFtpFile = false;
	
	public FtpClientUtil(String host, int port, String username, String password, boolean isDeleteFtpFile) throws SocketException, IOException {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.isDeleteFtpFile = isDeleteFtpFile;
		connected();
	}
	
	public void connected() throws SocketException, IOException {
		ftp.setConnectTimeout(5000);
		ftp.setControlKeepAliveReplyTimeout(5000);
		ftp.setControlKeepAliveTimeout(5000);
		ftp.setDataTimeout(5000);
		ftp.connect(host, port);
		ftp.setSoTimeout(5000);
		ftp.login(username, password);
	}
	
	public InputStream downloadFileByFileName(String fileName) throws IOException {
		InputStream inputStream = ftp.retrieveFileStream(fileName);
		return inputStream;
	}
	
	public boolean isConnected() {
		return ftp.isConnected();
	}
	
	public void disconnect() throws IOException {
		ftp.disconnect();
	}
	
	public void completeDownload() throws IOException {
		ftp.completePendingCommand();
	}
	
	public void deleteFtpFile(String pathname) throws IOException {
		if (isDeleteFtpFile) {
			ftp.deleteFile(pathname);
		}
	}

}
