package it.polimi.db2.project.utils;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSONConverter {
	public static JsonArray converter(List<Object[]> list) {
		JsonArray arr = new JsonArray();
		for (Object[] content : list) {
			JsonObject obj = new JsonObject();
			for (int i = 0; i < content.length; i++) {
				String str = null;
				str = content[i].toString();
				obj.addProperty("property" + String.valueOf(i), str);
			}
			arr.add(obj);
		}
		return arr;
	}
}
