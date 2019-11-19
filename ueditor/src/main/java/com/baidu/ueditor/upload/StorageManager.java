package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.sftp.SftpConfig;
import com.baidu.ueditor.sftp.SftpUtil;

public class StorageManager {
	public static final int BUFFER_SIZE = 8192;
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(StorageManager.class);

	public StorageManager() {
	}
	
	public static String trim(String src) {
		return null == src ? "" : src.trim();
	}

	public static State saveBinaryFile(byte[] data, String path, String otherPath, List<SftpConfig> sftpConfigList) {
		File file = new File(path);
		
		State state = valid(file);
		
		if (!state.isSuccess()) {
			return state;
		}

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bos.write(data);
			bos.flush();
			bos.close();
			otherPath = trim(otherPath);
			if (!"".equals(otherPath)) {
				File otherFile = new File(otherPath);
				FileUtils.copyFile(file, otherFile);
				
				SftpUtil.upload(sftpConfigList, otherFile.getName(), new FileInputStream(otherFile));
			}
			
//		} catch (IOException ioe) {
		} catch (Exception e) {	
			LOGGER.error("StorageManager.saveBinaryFile", e);
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = new BaseState(true, file.getAbsolutePath());
		state.putInfo( "size", data.length );
		state.putInfo( "title", file.getName() );
		return state;
	}

	public static State saveFileByInputStream(InputStream is, String path,
			long maxSize, String otherPath, List<SftpConfig> sftpConfigList) {
		State state = null;

		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[ 2048 ];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			if (tmpFile.length() > maxSize) {
				tmpFile.delete();
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			state = saveTmpFile(tmpFile, path, otherPath, sftpConfigList);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;
			
		} catch (IOException e) {
			LOGGER.error("StorageManager.saveFileByInputStream(1)", e);
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	public static State saveFileByInputStream(InputStream is, String path, String otherPath, List<SftpConfig> sftpConfigList) {
		State state = null;

		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[ 2048 ];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			state = saveTmpFile(tmpFile, path, otherPath, sftpConfigList);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;
		} catch (IOException e) {
			LOGGER.error("StorageManager.saveFileByInputStream(2)", e);
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static File getTmpFile() {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		return new File(tmpDir, tmpFileName);
	}

	private static State saveTmpFile(File tmpFile, String path, String otherPath, List<SftpConfig> sftpConfigList) {
		State state = null;
		File targetFile = new File(path);

		if (targetFile.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}
		try {
			FileUtils.moveFile(tmpFile, targetFile);
			otherPath = trim(otherPath);
			if (!"".equals(otherPath)) {
				File otherFile = new File(otherPath);
				FileUtils.copyFile(targetFile, otherFile);
				
				SftpUtil.upload(sftpConfigList, otherFile.getName(), new FileInputStream(otherFile));
			}
//		} catch (IOException e) {
		} catch (Exception e) {
			LOGGER.error("StorageManager.saveTmpFile", e);
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = new BaseState(true);
		state.putInfo( "size", targetFile.length() );
		state.putInfo( "title", targetFile.getName() );
		
		return state;
	}

	private static State valid(File file) {
		File parentPath = file.getParentFile();

		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
		}

		if (!parentPath.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}

		return new BaseState(true);
	}
}
