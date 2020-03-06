package com.kmh.mainpoint.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.kmh.mainpoint.dao.ApiDAO;
import com.kmh.test.userInfoAPI;


@Service("apiService")
public class ApiServiceImpl implements ApiService{
	
	
	@Resource(name="apiDAO")
	private ApiDAO apiDAO;

	int apiIOCount = 0;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		return apiDAO.selectBoardList(map);
	}


	@Override
	public List<Map<String, Object>> selectUserInfo(Map<String, Object> map) throws Exception {
		return apiDAO.selectUserInfo(map);
	}
	@Override
	public List<Map<String, Object>> selectUserInfoIgnoreAfk(Map<String, Object> map) throws Exception {
		return apiDAO.selectIgnoreUserInfo(map);
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
	private static int getWeekOfYear(String date) {
	    Calendar calendar = Calendar.getInstance();
	    String[] dates = date.split("-");
	    int year = Integer.parseInt(dates[0]);
	    int month = Integer.parseInt(dates[1]);
	    int day = Integer.parseInt(dates[2]);
	    calendar.set(year, month - 1, day);
	    return calendar.get(Calendar.WEEK_OF_YEAR);
	}


	
	public static long dateCtrl(String date,String type) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
		String[] _date = date.split("-");
		String year,month,day,week;
		year = _date[0];
		month = _date[1];
		day = _date[2];
		
		int weekend = getWeekOfYear(date);
		if(type.equals("w")) {
			week = getcal(year,month,day);
			week = week + " 00:00:01";
			System.out.println("월"+week);
		} else {
			week = getcal(year,month,day);
			week = week + " 23:59:59";
			System.out.println("일"+week);
		}
		


		Date nowTime1 = dateFormat.parse(week);
		long unixday = nowTime1.getTime();
		
		return unixday;
	}

	@Override
	public List<Map<String, Object>> attendanceCheckUser(List<Map<String, Object>> list,Map<String, Object> map) throws JSONException, IOException, ParseException, InterruptedException {
		
		String ApiKey = apiDAO.selectApiKey();
		String errorCode = "";
		boolean continueCheck;
		apiIOCount = apiIOCount + 1;
		if(apiIOCount >= 20) {
			apiIOCount = 0;
			Thread.sleep(120000);
		}
//		JSONObject level = userInfoAPI.getUserInfo(umodel.getUserId().toString(),"TEST");
//		String ab2 = getDate("2019","12",getWeek(-1));

		
		
//		ab2 = ab2+" 00:01:01";
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss",Locale.US);
//		String monday = getDate("2020","01",getWeek(-1));
//		String sunday = getSunday("2020","01",getWeek(-1));
		
//		monday = monday + " 00:00:01";
//		sunday = sunday + " 23:59:59";
//		Date nowTime1 = dateFormat.parse(monday);
//		Date nowTime2 = dateFormat.parse(sunday);
//		long unixMonday = nowTime1.getTime();
//		long unixSunday = nowTime2.getTime();
//		


		String play = "";
		
		
		String date = map.get("date").toString();
		String date2 = map.get("date2").toString();

		for(int i=0;i<=list.size()-1;i++) { //Ŭ���� �Ѹ��Ѹ�üũ
			try {
				continueCheck = false;
				
				String user = (list.get(i).get("name")).toString();
				
				
				long unixMonday = dateCtrl(date,"w");
				long unixSunday = dateCtrl(date2,"");
				
				String uaccountID = (list.get(i).get("accountId")).toString();
				
				String accountId = null;
				
				
				JSONObject matchsInfo = null;
				JSONObject info = null;
				info = userInfoAPI.getUserInfo(user,ApiKey);
				apiIOCount = apiIOCount + 1;
				if(apiIOCount >= 20) {
					apiIOCount = 0;
					Thread.sleep(120000);
					System.out.println("�޽���");
				}
				accountId = (info.get("accountId")).toString();
				matchsInfo = userInfoAPI.getUserMatchInfo(accountId,ApiKey,unixMonday,unixSunday); 

				if(!matchsInfo.get("checkSuccess").toString().equals("success")) {
					String errMsg = null;
					continueCheck = true;
					errorCode = matchsInfo.get("checkSuccess").toString();
					if(errorCode.equals("404")) {
						errMsg = "닉네임 확인필요 또는 게임기록없음";
					} else if(errorCode.equals("500")) {
						errMsg = "서버 에러(limit초과 또는 로직에러)";
					} else {
						errMsg = "민트초코먹고싶다 " +errorCode;
					}
					valueUpdate(errMsg,user,"checkPlay");
					continue;
				}
				if(continueCheck == true) {
					continue;
				}
				apiIOCount = apiIOCount + 1;
				if(apiIOCount >= 20) {
					apiIOCount = 0;
					Thread.sleep(120000);
					System.out.println("�޽���");
				}
				
				
				//matchid ��������
				
				int matchTotalCount = matchsInfo.getJSONArray("matches").length();
				int currentMatchIndex = -1; //�Ѹ�ġ�����Ϳ��� ��ġ���� �ϳ��� �E�� �� ����
				do
				{
					play = "";
					if(currentMatchIndex == matchTotalCount-1) { //��ġ���� ��ȸ�� ������ ����
						
						break;
					}
					currentMatchIndex = currentMatchIndex + 1;
					JSONObject matchDetail = userInfoAPI.getUserMatchDetail((matchsInfo.getJSONArray("matches").getJSONObject(currentMatchIndex).get("gameId")).toString(),ApiKey);
					apiIOCount = apiIOCount + 1;
					if(apiIOCount >= 20) {
						apiIOCount = 0;
						Thread.sleep(120000);
						System.out.println("�޽���");
					}
					
					
					JSONArray matchDetailPlayer = matchDetail.getJSONArray("participantIdentities");
					String gameid = matchDetail.get("gameId").toString();
	
	
			
					
					for(int j=0;j<=matchDetailPlayer.length()-1;j++) { //�ش� ��ġ ���� ���̵� �ϳ��� �̱�
	//					System.out.println(matchDetailPlayer);
	//					System.out.println(matchDetailPlayer.length());
//						System.out.println((JSONObject) matchDetailPlayer.get(0));
						JSONObject playerInfos = (JSONObject) matchDetailPlayer.get(j);
						JSONObject playerInfo = playerInfos.getJSONObject("player");
						String teamAccountId = playerInfo.get("accountId").toString();
						
	
						for(int k=0;k<=list.size()-1;k++) {//Ŭ���� �������̵� �ϳ��ϳ� �̾ư��� �ش��ġ�ο��� ��
	
	//						System.out.println(k);
	//						
	//						System.out.println(user+" ���ΰ� "+playerInfo.get("summonerName").toString()+"  "+ playerInfo.get("accountId").toString());
	//						System.out.println(list.get(k).get("name").toString() +" "+list.get(k).get("accountId").toString() );
	//						
							
							
							if(teamAccountId.equals(list.get(k).get("accountId").toString())) {
								if(uaccountID.equals(teamAccountId)) {
									
								} else {
									play = "play";
									//play = teamAccountId + "   " + list.get(k).get("accountId").toString();
									System.out.println("171wnf");
									break;	
								}
								
							}
							
						}
						
					}
					
				}   while(play == "");
	
	
	
			
	
				
	//			System.out.print(play+"  ");
	//			System.out.println(user);
				valueUpdate(play,user,"checkPlay");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
		}
		return null;
	
	}
	//���� accountId insert����
	@Override
	public List<Map<String, Object>> GetUserAccountId(List<Map<String, Object>> list) throws JSONException, IOException, ParseException {
		String ApiKey = apiDAO.selectApiKey();
		String user = null;
		String accountId=null;
		for(int i=0;i<=list.size()-1;i++) { //���� ���ڸ�ŭ �ݺ�
			try {
			user = null;
			user = (list.get(i).get("name")).toString();	
			
			
			JSONObject info = userInfoAPI.getUserInfo(user,ApiKey);
			accountId = (info.get("accountId")).toString();
			valueUpdate(accountId,user,"accountId");
			} catch(IOException e) {
				valueUpdate(accountId,user,"accountId");
				continue;
			}
		}
		return null;
	}
	
	public void valueUpdate(String value, String userName,String col){
		HashMap<String, String> map = new HashMap<>();
		
		map.put("value", value);
		map.put("userName", userName);
		if(col.equals("accountId")) {
			apiDAO.updateAccountId(map);
		}
		else if(col.equals("checkPlay")) {
			apiDAO.updatecheckPlay(map);
		}
 	}
	
	public static String getWeek(int idx){
		TimeZone Seoul = TimeZone.getTimeZone("Asia/Seoul");
 		Calendar c = Calendar.getInstance(Seoul);
 		
 		String week = String.valueOf(c.get(Calendar.WEEK_OF_MONTH)+idx);

 		return week;
 	}


	public static String getDate(String yyyy,String mm, String day){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy.MM.dd",Locale.US);
 		TimeZone newYorkTime = TimeZone.getTimeZone("America/New_York");
 		Calendar c = Calendar.getInstance(newYorkTime);
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm);
 		int d=Integer.parseInt(day);
 	
 		c.set(y,m,d);


 		return formatter.format(c.getTime());
 	}


	public static String getcal(String yyyy,String mm, String day){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
 		TimeZone Seoul = TimeZone.getTimeZone("Asia/Seoul");
 		Calendar c = Calendar.getInstance(Seoul);
 		
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm);
 		int d=Integer.parseInt(day);
 		c.set(Calendar.YEAR,y);
 		c.set(Calendar.MONTH,m-1);
 		c.set(Calendar.DAY_OF_MONTH,d);
 		System.out.println(day);

 		System.out.println(c);
 		System.out.println("BBBBBB");
 		System.out.println(c.getTime());
 		return formatter.format(c.getTime());
 	}
	public static String getMonday(Calendar c){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
 		int day = c.get(Calendar.DATE);
 		c.set(Calendar.DATE,day-6);
 		
 		System.out.println("BBBBBBBBBB");
 		System.out.println(c);
 		return formatter.format(c.getTime());
 	}

	public static String getSunday(String yyyy,String mm, int day){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
 		TimeZone Seoul = TimeZone.getTimeZone("Asia/Seoul");
 		Calendar c = Calendar.getInstance(Seoul);
 		
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm);
// 		int wk = Integer.parseInt(getWeek(day));
 		
 		c.set(Calendar.YEAR,y);
 		c.set(Calendar.MONTH,m);
 		c.set(Calendar.DAY_OF_MONTH,day);
 		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
 		System.out.println("aaaaaaa");
 		System.out.println(c.getTime());
 		return formatter.format(c.getTime());
 	}

	public static Calendar getSunday2(String yyyy,String mm, int day){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
 		TimeZone Seoul = TimeZone.getTimeZone("Asia/Seoul");
 		Calendar c = Calendar.getInstance(Seoul);
 		
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm);
// 		int wk = Integer.parseInt(getWeek(day));
 		
 		c.set(Calendar.YEAR,y);
 		c.set(Calendar.MONTH,m);
 		c.set(Calendar.DAY_OF_MONTH,day);
 		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
 		System.out.println("aaaaaaa");
 		System.out.println(c.getTime());
 		return c;
 	}







}
