package com.tahir.userimport;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreateJSONFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 prepareData();
	}
	
	public static void prepareData() {
		try {
			List<String> lines =new ArrayList<String>();
			for(int i=0;i<=50000;i++) {
				lines.add(jsonString(i));
			}
			
			Path file=Paths.get("E:\\SWDevelopment\\java\\liferaysrc\\user500.txt");
			Files.write(file, lines, StandardCharsets.UTF_8);
			System.out.println("File Write Done");
			
		}catch(Exception e) {
			System.out.println("Data Exception: "+e);
		}
	}
	
	public static String jsonString(int i) {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"alternateName\":\"tnm"+i+"\",");
		sb.append("\"emailAddress\":\"nooremail"+i+"@liferay.com\",");
		sb.append("\"familyName\":\"noor"+i+"\",");
		sb.append("\"givenName\":\"Tahir"+i+"\",");
		sb.append("\"jobTitle\":\"SE"+i+"\"");
		sb.append("},");
		return sb.toString();
	}

}
