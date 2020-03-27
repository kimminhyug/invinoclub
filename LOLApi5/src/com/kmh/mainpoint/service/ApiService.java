package com.kmh.mainpoint.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public interface ApiService {

	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectUserInfo(Map<String, Object> map) throws Exception;

	void attendanceCheckUser(List<Map<String, Object>> list, Map<String, Object> map) throws Exception;

	List<Map<String, Object>> GetUserAccountId(List<Map<String, Object>> list)
			throws JSONException, IOException, ParseException;

	List<Map<String, Object>> selectUserInfoIgnoreAfk(Map<String, Object> map) throws Exception;

	void insertUser(Map<String, Object> map);
	void deleteUser(Map<String, Object> map);

	void updateUserSearchDate(Map<String, Object> map);

	List<Map<String, Object>> GetUserPuuId(List<Map<String, Object>> list)
			throws JSONException, IOException, ParseException;

	void checkTFTPlay(List<Map<String, Object>> ignorelist,List<Map<String, Object>> clubMember, Map<String, Object> map) throws InterruptedException,IOException, ParseException;

	List<Map<String, Object>> selectUserInfoIgnoreAfkTFT(Map<String, Object> commandMap);
	
	

	
	
}
