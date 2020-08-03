package com.kmh.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		  public static JSONArray readJsonArrFromUrl(String url) throws IOException, JSONException {
			    InputStream is = new URL(url).openStream();
			    try {
			      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			      String jsonText = readAll(rd);
			      JSONArray json = new JSONArray(jsonText);
			      return json;
			    } finally {
			      is.close();
			    }
			  }
		  public static JSONObject getUserPuuId(String Id,String apiKey) throws IOException, JSONException {
			  
			  Id =  URLEncoder.encode(Id, "UTF-8");
			  Id=Id.replace("+", "");



				JSONObject json = null;
				    json = readJsonFromUrl("https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/"
							  +Id
							  +"?api_key="
							  +apiKey);

//				    String summonerLevel = json.get("summonerLevel").toString();
				

			    
			  return json;
		  }
		  public static JSONArray getUserTFTInfo(String Id,String apiKey) throws IOException, JSONException {
			  
			  Id =  URLEncoder.encode(Id, "UTF-8");
			  Id=Id.replace("+", "");


			    
			  
				JSONArray json = null;
				    json = readJsonArrFromUrl("https://asia.api.riotgames.com/tft/match/v1/matches/by-puuid/"
							  +Id
							  +"/ids?count=50"
							  +"&api_key="
							  +apiKey
							  );


			    
			  return json;
		  }
		  
		  public static JSONObject getUserInfo(String Id,String apiKey) throws IOException, JSONException {
			  
			  Id =  URLEncoder.encode(Id, "UTF-8");
			  Id=Id.replace("+", "");




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
		

			  
			  
		      if(responseCode==200) { // ���� ȣ��
		    	   json = readJsonFromUrl("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
						  +accountId
						  +endTimeText
						  +beginTimeText												  
						  +"&api_key=" +apiKey);
		    	   json.put("checkSuccess","success");
		    	   
		      } else {  // ���� �߻�\
				
		    	  json.put("checkSuccess",responseCode);
		    	  
		    	 
		      }
			  
			  
			  
			
			  return json;
		  }
		  public static JSONObject getUserMatchDetail(String matchId,String apiKey) throws IOException, JSONException {
			    JSONObject json = readJsonFromUrl("https://kr.api.riotgames.com/lol/match/v4/matches/"+matchId+"?api_key="
			    								  +apiKey);
			  return json;
		  }
		  

		  
		  
		  public static void main(String[] args) throws IOException, JSONException {
		    JSONObject json = readJsonFromUrl("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%EC%97%AC%ED%96%89%EB%96%A0%EB%82%98%EB%8A%94%EB%B0%94%EB%93%9C%EC%B0%A1?api_key=RGAPI-324d1cb3-959a-445e-aaf5-bf488ebe7cff");
		    String lolId  = "���ධ���¹ٵ���";
		    String encodedId = URLEncoder.encode(lolId, "UTF-8");

		    
		    

		  }

		public static  JSONObject getUserTFTMatchInfo(String matchId,String apiKey) throws JSONException, IOException {
			matchId =  URLEncoder.encode(matchId);
			matchId=matchId.replace("+", "");
			String urlValue = "https://asia.api.riotgames.com/tft/match/v1/matches/"
					  +matchId
					  +"?api_key="
					  +apiKey;
			URL url = new URL(urlValue);
			javax.net.ssl.HttpsURLConnection con = (javax.net.ssl.HttpsURLConnection)url.openConnection();
			int responseCode = con.getResponseCode();

			JSONObject json = null;
			json = readJsonFromUrl(urlValue);
			if(responseCode == 200) {
				json.put("checkSuccess","success");
			} else {
				json.put("checkSuccess",responseCode);
			}
			    
			  return json;
		}

		public static JSONObject getUserMatchInfo(String accountId, String apiKey, long unixday) throws JSONException, IOException {
			  String beginTimeText = "";
			  String endTimeText = "";
			  
			  JSONObject json = new JSONObject();
			  
			  if(unixday != 0) {
				  beginTimeText = "&beginTime="+unixday;
			  }

			  URL url = new URL("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
					  +accountId
					  +beginTimeText												  
					  +"&api_key=" +apiKey);
			  javax.net.ssl.HttpsURLConnection con = (javax.net.ssl.HttpsURLConnection)url.openConnection();
			  int responseCode = con.getResponseCode();
		

			  
			  
		      if(responseCode==200) { // ���� ȣ��
		    	   json = readJsonFromUrl("https://kr.api.riotgames.com//lol/match/v4/matchlists/by-account/"
						  +accountId
						  +beginTimeText												  
						  +"&api_key=" +apiKey);
		    	   json.put("checkSuccess","success");
		    	   
		      } else {  // ���� �߻�\
				
		    	  json.put("checkSuccess",responseCode);
		    	  
		    	 
		      }
			  
			  
			  
			
			  return json;
		}
}