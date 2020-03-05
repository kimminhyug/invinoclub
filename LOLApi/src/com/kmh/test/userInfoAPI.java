package com.kmh.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;

public class userInfoAPI {
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

		  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }

		  
		  public static JSONObject getUserInfo(String Id,String apiKey) throws IOException, JSONException {
			  
			  Id =  URLEncoder.encode(Id, "UTF-8");
			  Id=Id.replace("+", "");

System.out.println("이거네");

				URL url = new URL("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
			    								  +Id
			    								  +"?api_key="
			    								  +apiKey);
				JSONObject json = null;
				    json = readJsonFromUrl("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
							  +Id
							  +"?api_key="
							  +apiKey);

				    String summonerLevel = json.get("summonerLevel").toString();
				

			    
			  return json;
		  }
		  public static JSONObject getUserMatchInfo(String accountId,String apiKey, long beginTime, long endTime) throws IOException, JSONException {
			  String beginTimeText = "";
			  String endTimeText = "";
			  
			  JSONObject json = new JSONObject();
			  System.out.println(22222);
			  if(beginTime != 0) {
				  beginTimeText = "&beginTime="+beginTime;
			  }
			  if(endTime != 0) {
				  endTimeText = "?endTime="+endTime;
			  }  
			  URL url = new URL("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
					  +accountId
					  +endTimeText
					  +beginTimeText												  
					  +"&api_key=" +apiKey);
			  javax.net.ssl.HttpsURLConnection con = (javax.net.ssl.HttpsURLConnection)url.openConnection();
			  int responseCode = con.getResponseCode();
		

			  System.out.print("responseCode="+responseCode);
			  
		      if(responseCode==200) { // 정상 호출
		    	   json = readJsonFromUrl("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
						  +accountId
						  +endTimeText
						  +beginTimeText												  
						  +"&api_key=" +apiKey);
		    	   json.put("checkSuccess","success");
		    	   
		      } else {  // 에러 발생\
				  System.out.println("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
						  +accountId
						  +endTimeText
						  +beginTimeText												  
						  +"&api_key=" +apiKey);
		    	  json.put("checkSuccess",responseCode);
		    	  
		    	 
		      }
			  
			  
			  
				  System.out.println("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
						  +accountId
						  +endTimeText
						  +beginTimeText												  
						  +"&api_key=" +apiKey);

				  
			  return json;
		  }
		  public static JSONObject getUserMatchDetail(String matchId,String apiKey) throws IOException, JSONException {
			    JSONObject json = readJsonFromUrl("https://kr.api.riotgames.com/lol/match/v4/matches/"+matchId+"?api_key="
			    								  +apiKey);
			  return json;
		  }
		  

		  
		  
		  public static void main(String[] args) throws IOException, JSONException {
		    JSONObject json = readJsonFromUrl("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%EC%97%AC%ED%96%89%EB%96%A0%EB%82%98%EB%8A%94%EB%B0%94%EB%93%9C%EC%B0%A1?api_key=RGAPI-324d1cb3-959a-445e-aaf5-bf488ebe7cff");
		    String lolId  = "여행떠나는바드찡";
		    String encodedId = URLEncoder.encode(lolId, "UTF-8");
		    System.out.println(encodedId);
		    System.out.println(json.toString());
		    System.out.println(json.get("id"));
		    System.out.println(json.get("name"));
		    
		    

		  }
}