package com.kmh.mainpoint.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
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
	public List<Map<String, Object>> selectUserInfoIgnoreAfkTFT(Map<String, Object> map) {
		return apiDAO.selectUserInfoIgnoreAfkTFT(map);
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
	public void updateUserSearchDate(Map<String, Object> map) {
		String regDate = map.get("date") + " - " + map.get("date2");
		HashMap<String, String> _map = new HashMap<>();
		_map.put("regDate", regDate);
		apiDAO.updateUserSearchDate(_map);
	}
	
	public void checkTFTPlay(List<Map<String, Object>> list,List<Map<String, Object>> club,Map<String, Object> map) throws InterruptedException,IOException, ParseException {
		
		apiIOCount= apiIOCount+10;
		String ApiKey = apiDAO.selectTFTApiKey();
		String errorCode = "";
		boolean continueCheck = false;
		
		apiIOCount = apiIOCount + 1;
	
		String date = map.get("date").toString();
		String date2 = map.get("date2").toString();

		long unixMonday = dateCtrl(date,"w");
		long unixSunday = dateCtrl(date2,"");
		
		String play = "";
		

		for(int i=0;i<=list.size()-1;i++) { //Ŭ���� �Ѹ��Ѹ�üũ
			try {
				System.out.println("i "+i);
				continueCheck = false;
				String user = (list.get(i).get("name")).toString();
				String puuid = (list.get(i).get("PUUID")).toString();
				JSONArray info = null;
				JSONObject matchInfo = null;
				
				info = userInfoAPI.getUserTFTInfo(puuid,ApiKey);
				apiIOCount = apiIOCount + 1;
				if(apiIOCount >= 20) {
					apiIOCount = 0;
					Thread.sleep(120000);
				}

				
				//get match id(array)
				for(int j=info.length()-1;j>=0;j--) {
					System.out.println("j "+j);
					continueCheck = false;
					String matchId = (info.get(j)).toString();
					
					matchInfo = userInfoAPI.getUserTFTMatchInfo(matchId,ApiKey);
					apiIOCount = apiIOCount + 1;
					if(apiIOCount >= 20) {
						apiIOCount = 0;
						Thread.sleep(120000);
					}
					JSONObject gameDateArr =   matchInfo.getJSONObject("info");
					
					String gameDate =  gameDateArr.get("game_datetime").toString();
					
//					long gameDateL = Integer.parseInt(gameDate);
					Date gameDateD = changeUnixToDate(gameDate);
					Date mondayD = changeUnixToDate(String.valueOf(unixMonday));
					Date sundayD = changeUnixToDate(String.valueOf(unixSunday));
					int check = dateCompare(gameDateD,mondayD,"monday");
					int check2 = dateCompare(gameDateD,sundayD,"sunday");

					if(check < 0) {
						continue;
					}
					if(check2 > 0 ) {
						break;
					}
					System.out.println("pass");
					
//					JSONObject matchDetail = userInfoAPI.getUserMatchDetail((matchsInfo.getJSONArray("matches").getJSONObject(currentMatchIndex).get("gameId")).toString(),ApiKey);
					
					int currentUserIdx = 0;
					do {
						System.out.println("do ");
						JSONObject matchObject = (JSONObject) matchInfo.get("metadata");
						
						if(!matchInfo.get("checkSuccess").toString().equals("success")) {
							String errMsg = null;
							continueCheck = true;
							errorCode = matchObject.get("checkSuccess").toString();
							if(errorCode.equals("404")) {
								errMsg = "닉네임 확인필요 또는 게임기록없음";
							} else if(errorCode.equals("500")) {
								errMsg = "서버 에러(limit초과 또는 로직에러)";
							} else {
								errMsg = "민트초코먹고싶다 " +errorCode;
							}
							valueUpdate(errMsg,user,"checkPlay");
							break;
						}
					
						JSONArray matchUserList =  matchObject.getJSONArray("participants");

						String matchUser =  matchUserList.get(currentUserIdx).toString();
						
						
					
						
						
						
						
						for(int c=0;c<club.size();c++) {
							System.out.println("c "+c);
							String clubMember = (club.get(c).get("PUUID")).toString();
							System.out.println(user + "         " +puuid);
							System.out.println(matchUser +" "+ clubMember );
							if( matchUser.equals(clubMember) && !(matchUser.equals(puuid))) {
								System.out.println(user + "         " +puuid);
								System.out.println(matchUser +" "+ clubMember );
								System.out.println( matchUser.equals(clubMember) && !(matchUser.equals(puuid)));
								valueUpdate("play",user,"checkPlay");
								currentUserIdx = 9;
								continueCheck = true;
								break;
							}
						}
						currentUserIdx = currentUserIdx +1;
					} while(currentUserIdx <= 7);
					if(continueCheck == true) {
						break;
					}
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
		}
	}
	@Override
	public void attendanceCheckUser(List<Map<String, Object>> list,Map<String, Object> map) throws JSONException, IOException, ParseException, InterruptedException {
		
		String ApiKey = apiDAO.selectApiKey();
		String errorCode = "";
		boolean continueCheck;
		apiIOCount = apiIOCount + 1;
		if(apiIOCount >= 20) {
			apiIOCount = 0;
			Thread.sleep(120000);
		}

		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss",Locale.US);



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
		else if(col.equals("puuId")) {
			apiDAO.updatePuuId(map);
		}
 	}
	public void insertUser(Map<String,Object> map){
			apiDAO.insertUser(map);
 	}
	//���� accountId insert����
	@Override
	public List<Map<String, Object>> GetUserPuuId(List<Map<String, Object>> list) throws JSONException, IOException, ParseException {
		String ApiKey = apiDAO.selectTFTApiKey();
		String user = null;
		String puuId=null;
		
		for(int i=0;i<=list.size()-1;i++) { //���� ���ڸ�ŭ �ݺ�
			try {
			user = null;
			user = (list.get(i).get("name")).toString();	
			
			
			JSONObject info = userInfoAPI.getUserPuuId(user,ApiKey);
			
			puuId = (info.get("puuid")).toString();
			
			valueUpdate(puuId,user,"puuId");
			} catch(IOException e) {
				valueUpdate(puuId,user,"puuId");
				continue;
			}
		}
		return null;
	}
	
	public void deleteUser(Map<String,Object> map){
		apiDAO.deleteUser(map);
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

	public static Date changeUnixToDate(String unixSeconds) throws ParseException{
  		java.sql.Timestamp timestamp_3 = new java.sql.Timestamp(Long.parseLong(unixSeconds));
  		
  		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z",Locale.KOREA);
  		  String d1 = sdf.format(new java.sql.Date(timestamp_3.getTime()));
  		
  		Date dd =   (Date)sdf.parse(d1);

  		return dd;
		
	}

		   

	public static int dateCompare(Date date1,Date date2,String type) throws ParseException{
		  Date day1 = null;
		  Date day2 = null;

		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z",Locale.KOREA);
//
//		  day1 = sdf.parse(date1);
//		  day2 = sdf.parse(date2);

//		  sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
		  
//	  		Date dd2 =   (Date)sdf.parse(d1);
		  if(type.equals("monday")) {
			  date2.setHours(0);
			  date2.setMinutes(0);
			  date2.setSeconds(0);
		  }else if(type.equals("sunday")) {
		  	  date2.setHours(23);
		  	  date2.setMinutes(59);
		  	  date2.setSeconds(59);
		  } else {
			  
		  }


	  		int compare = date1.compareTo(date2);
	  		System.out.println("compare");
	  		System.out.println(date1 +"    " + date2);
	  		System.out.println(compare);
	  		

		  return  compare;
		
	}

		   





}



