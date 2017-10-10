package com.zlp.soap.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.URL;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

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
	private String SERVER_CHARSET = "ISO-8859-1";
	
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
	
	public InputStream downloadFileByFilePath(String filePath) throws IOException {
		String path = initFilePath(filePath);
		InputStream inputStream = ftp.retrieveFileStream(path);
		return inputStream;
	}
	
	private String initFilePath(String filePath) throws IOException {
		String LOCAL_CHARSET = "GBK";
		if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {
			LOCAL_CHARSET = "UTF-8";
		}

		ftp.setControlEncoding(LOCAL_CHARSET);// Set the encoding format
		ftp.setFileType(FTP.BINARY_FILE_TYPE);// Set the mode of
		
		String filePathInfo = "ftp://" + host + "/" + filePath;
		URL url = new URL(filePathInfo);
		String path = new String(url.getPath().getBytes(LOCAL_CHARSET), SERVER_CHARSET);
		return path;
	}
	
	public long fileLength(String filePath) throws IOException {
		
		String path = initFilePath(filePath);

		// Get file size
		long fileSize = 0l;
		if (FTPReply.isPositiveCompletion(ftp.sendCommand("SIZE " + path))) {
			String replyStr = ftp.getReplyString();
			replyStr = replyStr.replace("\r\n", "");
			String[] arr = replyStr.split(" ");
			if (arr.length == 2) {
				fileSize = Long.parseLong(arr[1]);
			}
		}
		
		return fileSize;
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
