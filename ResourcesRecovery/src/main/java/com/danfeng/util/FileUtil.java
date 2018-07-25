package com.danfeng.util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {
	private static String seperator = System.getProperty("file.separator");
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss"); // 时间格式化的格式
	private static final Random r = new Random();

	public static String getImgBasePath() {	
		String basePath =  FileUtil.class.getResource("/").getPath()+"static/static/upload/img/goods/";
		basePath = basePath.replace("/", seperator);
		return basePath;
	}


	public static String getRandomFileName() {
		// 随机文件名：时间+五位随机数（防止重名）
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		return nowTimeStr + rannum;
	}

	
}
