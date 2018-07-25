package com.danfeng.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressUtil {

	public static JsonObject jsonObject = getXpath("http://passer-by.com/data_location/list.json");

	public static JsonObject getXpath(String requestUrl) {
		String res = "";
		JsonObject object = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			if (200 == urlCon.getResponseCode()) {
				InputStream is = urlCon.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				BufferedReader br = new BufferedReader(isr);

				String str = null;
				while ((str = br.readLine()) != null) {
					buffer.append(str);
				}
				br.close();
				isr.close();
				is.close();
				res = buffer.toString();
				JsonParser parse = new JsonParser();
				object = (JsonObject) parse.parse(res);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	public static JsonObject postDownloadJson(String path, String post) {
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			// conn.setConnectTimeout(10000);//连接超时 单位毫秒
			// conn.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
			printWriter.write(post);// post的参数 xx=xx&yy=yy
			// flush输出流的缓冲
			printWriter.flush();
			// 开始获取数据
			BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len;
			byte[] arr = new byte[1024];
			while ((len = bis.read(arr)) != -1) {
				bos.write(arr, 0, len);
				bos.flush();
			}
			bos.close();
			JsonParser parse = new JsonParser();
			return (JsonObject) parse.parse(bos.toString("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> getAddressBytownCode(Integer townCode) {

		/// static/common/addressJson/list.json
		Map<String, String> result = new HashMap<>();
		String provinceCode = String.valueOf(townCode).substring(0, 2) + "0000";
		String cityCode = String.valueOf(townCode).substring(0, 4) + "00";
		String districtCode = String.valueOf(townCode).substring(0, 6);

		JsonObject townJsonObject = getXpath("http://passer-by.com/data_location/town/" + districtCode + ".json");

		String province = jsonObject.get(provinceCode).getAsString();
		String city;
		if (jsonObject.get(cityCode) != null)
			city = jsonObject.get(cityCode).getAsString();
		else
			city = "";
		String district = jsonObject.get(districtCode).getAsString();
		String town = townJsonObject.get(String.valueOf(townCode)).getAsString();
		result.put("province", province);
		result.put("city", city);
		result.put("district", district);
		result.put("town", town);
		return result;

	}
}
