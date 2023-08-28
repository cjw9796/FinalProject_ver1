package com.kh.myproject.api.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NaverApiParser {

	
	private static String removeTag(String text) {
		if (text == null || text.length() == 0) {
			return text;
		}
		text = text.replaceAll("<br>", "\n");
		text = text.replaceAll("&gt;", ">");
		text = text.replaceAll("&lt;", "<");
		text = text.replaceAll("&quot;", "");
		text = text.replaceAll("&nbsp;", " ");
		text = text.replaceAll("&amp;", "&");
		text = text.replaceAll("&apos;", "'");
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""); // 특수문자 제거
	}
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	public static Date toDate(JSONObject object, String tag) {
		try {
			return sdf.parse((String)object.get(tag));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static int toInt(JSONObject object, String tag) {
		try {
			return Integer.parseInt((String)object.get(tag));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String toString(JSONObject object, String tag, boolean isRemoveTag) {
		String text = toString(object, tag);
		if(isRemoveTag == true && text != null && text.length() > 0) {
			return removeTag(text);
		}
		return text;
	}
	
	public static String toString(JSONObject object, String tag) {
		try {
			return (String)object.get(tag);
		} catch (Exception e) {
			return "-";
		}
	}
	
	public static List<News> parseNews(String json) throws ParseException {
		List<News> list = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		JSONObject rootObj = (JSONObject) jsonParser.parse(json);
		JSONArray array = (JSONArray) rootObj.get("items");

		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			String title = toString(obj, "title", true);
			String originallink = toString(obj, "originallink");
			String link = toString(obj, "link");
			String description = toString(obj, "description");
			Date pubDate = toDate(obj, "pubDate");
			News news = new News(title, originallink, link, description, pubDate);
			list.add(news);
		}
		return list;
	}
	
	
	public static List<Product> parseShop(String json) throws ParseException {
		List<Product> list = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		JSONObject rootObj = (JSONObject) jsonParser.parse(json);
		JSONArray array = (JSONArray) rootObj.get("items");
		
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			String title = toString(obj, "title", true);
			String link = toString(obj, "link");
			String image = toString(obj, "image");
			int lprice = toInt(obj, "lprice");
			int hprice = toInt(obj, "hprice");
			String mallName = toString(obj, "mallName");
			String productId = toString(obj, "productId");
			String productType = toString(obj, "productType");
			String brand = toString(obj, "brand");
			String maker = toString(obj, "maker");
			String category1 = toString(obj, "category1");
			String category2 = toString(obj, "category2");
			String category3 = toString(obj, "category3");
			String category4 = toString(obj, "category4");
			Product product = new Product(title, link, image, lprice, hprice, mallName, productId, productType, brand, maker, category1, category2, category3, category4);
			list.add(product);
		}
		return list;
	}
}
