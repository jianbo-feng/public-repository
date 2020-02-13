package com.baidu.ueditor.upload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.sftp.SftpConfig;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public final class Base64Uploader {

	@SuppressWarnings("unchecked")
	public static State save(String content, Map<String, Object> conf) {
		
		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));
		
		savePath = savePath + suffix;
		String physicalPath = (String) conf.get("rootPath") + savePath;
		String otherPath = "";
		String customSaveDir = ((String) conf.get("customSaveDir")).trim();
		if (null != customSaveDir && !"".equals(customSaveDir)) {
			otherPath = customSaveDir + savePath;
//			System.err.println("自定义保存：Base64Uploader （customSaveDir）" + customSaveDir);
		}
		List<SftpConfig> sftpConfigList = (List<SftpConfig>)conf.get("sftpConfigList");
		State storageState = StorageManager.saveBinaryFile(data, physicalPath, otherPath, sftpConfigList);
		
		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}