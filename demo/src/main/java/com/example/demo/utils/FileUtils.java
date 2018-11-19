package com.example.demo.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Permission;

public final class FileUtils {

	public static final SecurityManager SECURITYMANAGER = new SecurityManager() {
		public void checkPermission(Permission perm) {
		}
	};
	public static final SecurityManager SYSSECURITYMANAGER = System
			.getSecurityManager();

	/**
	 * 
	 * @param filePath
	 *            相对路径
	 * @param content
	 *            保存内容
	 * @return 返回相对路径
	 * @throws IOException
	 */
	public static String writeFile(String filePath, byte[] content)
			throws IOException {
		if (content == null) {
			return null;
		}
		System.setSecurityManager(SECURITYMANAGER);

		FileOutputStream fos = null;
		try {
			File infoFile = new File(filePath);

			infoFile = infoFile.getCanonicalFile();

			if (!infoFile.exists()) {
				File dir = infoFile.getParentFile();
				if (dir != null && !dir.exists()) {
					dir.mkdirs();
				}
			}

			fos = new FileOutputStream(infoFile);
			fos.write(content);
			fos.flush();
			fos.close();

			return filePath;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (SYSSECURITYMANAGER != null) {
				System.setSecurityManager(SYSSECURITYMANAGER);
			}
		}
	}

	public static String storeFile(String filePath, byte[] content)
			throws IOException {
		if (content == null) {
			return null;
		}
		System.setSecurityManager(SECURITYMANAGER);

		FileOutputStream fos = null;
		try {
			File infoFile = new File(filePath);

			infoFile = infoFile.getCanonicalFile();

			if (!infoFile.exists()) {
				File dir = infoFile.getParentFile();
				if (dir != null && !dir.exists()) {
					dir.mkdirs();
				}
			}

			fos = new FileOutputStream(infoFile);
			fos.write(content);
			fos.flush();
			fos.close();

			return filePath;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (SYSSECURITYMANAGER != null) {
				System.setSecurityManager(SYSSECURITYMANAGER);
			}
		}
	}


	public static boolean deleteFile(String file) {
		System.setSecurityManager(SECURITYMANAGER);
		boolean isDeleted;
		File aFile = new File(file);
		if (aFile.exists() && aFile.isFile()) {
			isDeleted = aFile.delete();
		} else {
			isDeleted = false;
		}
		System.setSecurityManager(SECURITYMANAGER);
		return isDeleted;
	}

	public static byte[] readFile(String filePath) {
		File infoFile = new File(filePath);
		byte[] result = null;
		if (infoFile.exists()) {
			result = new byte[(int) infoFile.length()];
			try {
				FileInputStream fis = new FileInputStream(infoFile);
				fis.read(result);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 顺序读取文本文件的内容
	 * @param file
	 * @param encoding
	 * @return
	 */

	public static String readToString(File file, String encoding) {
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

}
