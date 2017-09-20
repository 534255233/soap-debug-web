package com.zlp.soap.ftp;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class FileDownloadItem {
	
	/**
	 * 文件ID
	 */
	private String fileId;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件下次下载时间
	 */
	private long nextDownloadTime;
	/**
	 * 文件下载次数
	 */
	private int downloadTimes;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getNextDownloadTime() {
		return nextDownloadTime;
	}
	public void setNextDownloadTime(long nextDownloadTime) {
		this.nextDownloadTime = nextDownloadTime;
	}
	public int getDownloadTimes() {
		return downloadTimes;
	}
	public void setDownloadTimes(int downloadTimes) {
		this.downloadTimes = downloadTimes;
	}
	
}
