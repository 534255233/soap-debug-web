package com.zlp.soap.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-09-19
 */
public class AliyunOssUtil {
	
//	private String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
//	private String accessKeyId = "wtiUZd71Qvb0zIsQ";
//	private String accessKeySecret = "aOywWxrs9kc33CrhABv4f10gDqEttY";
	private String bucketName = "elsdocman";
	private String localTempFilePath = null;
	private boolean deleteLocalTempFile = false;
	
	
	private ClientConfiguration conf = new ClientConfiguration();
	private OSSClient ossClient = null;
	
	public AliyunOssUtil(String endpoint, String accessKeyId, 
			String accessKeySecret, String bucketName, 
			String localTempFilePath, boolean deleteLocalTempFile) {
//		this.endpoint = endpoint;
//		this.accessKeyId = accessKeyId;
//		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
		this.localTempFilePath = localTempFilePath;
		this.deleteLocalTempFile = deleteLocalTempFile;
		conf.setIdleConnectionTime(1000 * 60 * 5);//5分钟
//		Calendar c = Calendar.getInstance();
//		pretime =  c.getTime().getTime() / 1000;
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
	}
	
	
//	private long pretime = 0l;
	
	public String uploadFile(String fileId, InputStream inputStream) throws OSSException, ClientException, FileNotFoundException {
		
//		Calendar c = Calendar.getInstance();
//		long nowtime =  c.getTime().getTime() / 1000;
//		
//		if ((nowtime - pretime) > 300 || ossClient == null) {//5分钟
//			synchronized(conf) {
//				if ((nowtime - pretime) > 300 || ossClient == null) {//5分钟
//					shutdown();
//					ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
//					pretime =  c.getTime().getTime() / 1000;
//				}
//			}
//		}
		
		String fileName = fileId.substring(7, fileId.length());
		
		String localFileName = fileId.substring(fileId.indexOf("|&&|"), fileId.length()).replace("|&&|", "");
		File file = new File(localTempFilePath+localFileName);  
        byte[] bytes = new byte[1024];  
        int i;  
        FileOutputStream fos = null;
        long fileSize = 0l;
		try {
			fos = new FileOutputStream(file);
			while((i = inputStream.read(bytes)) != -1){  
				fos.write(bytes, 0, i);  
			}
			fileSize = file.length();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		ObjectMetadata objectMetadata=new ObjectMetadata();  
	    objectMetadata.setContentLength(fileSize);  
	    objectMetadata.setCacheControl("no-cache");  
	    objectMetadata.setHeader("Pragma", "no-cache");  
	    objectMetadata.setContentType(contentType(fileName.substring(fileName.lastIndexOf(".")+1)));  
	    objectMetadata.setContentDisposition("inline;filename=" + fileName);
	    
	    InputStream fileStream = new FileInputStream(file);
	    
	    System.err.println("oss fileId = "+fileId);
		
		PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileId, fileStream, objectMetadata);
		
		try {
			if(fileStream != null) {
				fileStream.close();
			}
			if(deleteLocalTempFile && file.exists()) {
				file.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return putObjectResult.getRequestId();
	}
	
	public void shutdown() {
		if(ossClient != null) {
			ossClient.shutdown();
		}
	}
	
	private String contentType(String FilenameExtension){  
        if(FilenameExtension.equals("BMP")||FilenameExtension.equals("bmp")){return "image/bmp";}  
        if(FilenameExtension.equals("GIF")||FilenameExtension.equals("gif")){return "image/gif";}  
        if(FilenameExtension.equals("JPEG")||FilenameExtension.equals("jpeg")||  
           FilenameExtension.equals("JPG")||FilenameExtension.equals("jpg")||     
           FilenameExtension.equals("PNG")||FilenameExtension.equals("png")){return "image/jpeg";}  
        if(FilenameExtension.equals("HTML")||FilenameExtension.equals("html")){return "text/html";}  
        if(FilenameExtension.equals("TXT")||FilenameExtension.equals("txt")){return "text/plain";}  
        if(FilenameExtension.equals("VSD")||FilenameExtension.equals("vsd")){return "application/vnd.visio";}  
        if(FilenameExtension.equals("PPTX")||FilenameExtension.equals("pptx")||  
            FilenameExtension.equals("PPT")||FilenameExtension.equals("ppt")){return "application/vnd.ms-powerpoint";}  
        if(FilenameExtension.equals("DOCX")||FilenameExtension.equals("docx")||  
            FilenameExtension.equals("DOC")||FilenameExtension.equals("doc")){return "application/msword";}  
        if(FilenameExtension.equals("XML") || FilenameExtension.equals("xml")){return "text/xml";}
        if(FilenameExtension.equals("DWG")||FilenameExtension.equals("dwg")){return "application/x-dwg";}
        if(FilenameExtension.equals("PDF")||FilenameExtension.equals("pdf")){return "application/pdf";}
        if(FilenameExtension.equals("XLS") || FilenameExtension.equalsIgnoreCase("XLSX") ||
          FilenameExtension.equals("xls") ||FilenameExtension.equals("xlsx")){return "application/vnd.ms-excel";}
        return "application/octet-stream";  
     }  
	
	
//	public static void main(String[] args) {
////		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
////		System.out.println(ossClient);
//		File file = new File("E:\\temp\\"+"21323213.dwg");
//		if(!file.exists()) {
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	

}
