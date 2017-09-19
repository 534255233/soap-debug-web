package com.zlp.soap.ftp;

import java.util.Date;

/**
 * 获取FTP文件的结果记录
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class GetFtpFileRecord implements java.io.Serializable {

	/***/
	private static final long serialVersionUID = 1L;
	
	/**
	 * 文件ID，作为主键
	 */
	private String fileId;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private long fileSize;
	/**
	 * 文件存储路径
	 */
	private String storePath;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 文件请求开始时间
	 */
	private Date requestBeginTime;
	/**
	 * 文件请求结束时间
	 */
	private Date requestEndTime;
	/**
	 * 去FTP服务器请求文件的次数
	 * 【请求一次FTP加1】
	 */
	private int requestFailTimes;
	/**
	 * 错误信息
	 */
	private String errorMessage;
	/**
	 * 上传文件到阿里云的结果
	 * 0失败，1成功】
	 */
	private int uploadAliyunStatus;
	/**
	 * 上传文件到阿里云返回的结果
	 */
	private String aliyunOssRequestId;
	/**
	 * 文件最终处理的结果
	 * 【0失败，1成功】
	 */
	private int status;
	
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getRequestBeginTime() {
		return requestBeginTime;
	}
	public void setRequestBeginTime(Date requestBeginTime) {
		this.requestBeginTime = requestBeginTime;
	}
	public Date getRequestEndTime() {
		return requestEndTime;
	}
	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public int getRequestFailTimes() {
		return requestFailTimes;
	}
	public void setRequestFailTimes(int requestFailTimes) {
		this.requestFailTimes = requestFailTimes;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getUploadAliyunStatus() {
		return uploadAliyunStatus;
	}
	public void setUploadAliyunStatus(int uploadAliyunStatus) {
		this.uploadAliyunStatus = uploadAliyunStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getStorePath() {
		return storePath;
	}
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	public String getAliyunOssRequestId() {
		return aliyunOssRequestId;
	}
	public void setAliyunOssRequestId(String aliyunOssRequestId) {
		this.aliyunOssRequestId = aliyunOssRequestId;
	}
	
}
