package com.kmh.mainpoint.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kmh.common.AbstractDAO;

@Repository("apiDAO")
public class ApiDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("invino.selectBoardList", map); 
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserInfo(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("invino.selectUserInfo", map); 
	}

	public String selectApiKey() {
		return (String) selectOne("invino_lolapi.selectApiKey"); 
		
	}
	public void updateAccountId(HashMap<String, String> map) {
		
		 insert("invino.updateAccountId",map);
		
	}
	public void updatecheckPlay(HashMap<String, String> map) {
		
		 insert("invino.updatecheckPlay",map);
		
	}

	public void insertUser(Map<String, Object> map) {
		
		 insert("invino.insertUser",map);
		
	}
	public List<Map<String, Object>> selectIgnoreUserInfo(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("invino.selectIgnoreUserInfo", map); 
	}

	public void deleteUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		delete("invino.deleteUser",map);
	}

	public void updateUserSearchDate(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		update("invino.updateUserSearchDate",map);
	}
		
}
