package it.polimi.db2.project.utils;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSONConverter {
	public static JsonArray converter(List<Object[]> list ) {
		JsonArray arr= new JsonArray();
		for(Object[] content:list) {
			JsonObject obj= new JsonObject();
			for(int i=0; i<content.length; i++) {
				String str=null;
				if(content[i].getClass().equals(Date.class)) {
					Date date=(Date)content[i];
					str=date.getDay()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
				}
				else{
					str=content[i].toString();
				}
				obj.addProperty("property"+String.valueOf(i), str);
			}
			arr.add(obj);
		}
		return arr;
	}
}
