package com.zlp.soap.ftp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhoulongpeng
 * @date   2017-10-10
 */
public class FtpFileListStore {

	private static Map<String, FileDownloadItem> files = new HashMap<>();
	
	public static void add(List<FileDownloadItem> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (FileDownloadItem item : list) {
			files.put(item.getFileName(), item);
		}
	}
	
	public static void add(FileDownloadItem item) {
		if (item == null) {
			return;
		}
		files.put(item.getFileName(), item);
	}
	
	public static FileDownloadItem getByFileName(String fileName) {
		if (files.containsKey(fileName)) {
			return files.get(fileName);
		}
		throw new RuntimeException("[" + fileName + "] key not found ");
	}
	
	public static List<FileDownloadItem> getAll() {
		List<FileDownloadItem> items = new ArrayList<>();
		for(Map.Entry<String, FileDownloadItem> entry : files.entrySet()) {
			items.add(entry.getValue());
		}
		return items;
	}
	
	public static void remove(String fileName) {
		if (files.containsKey(fileName)) {
			files.remove(fileName);
		}
	}
	
	public static void removeAll() {
		for(Map.Entry<String, FileDownloadItem> entry : files.entrySet()) {
			files.remove(entry.getValue());
		}
	}
	
}
