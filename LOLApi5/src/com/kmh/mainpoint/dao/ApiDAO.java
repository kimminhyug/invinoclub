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
	public void updateUserPlayCheck() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = null;
		update("invino.updateUserPlayCheck", map);
	}
	
	public String selectTFTApiKey() {
		// TODO Auto-generated method stub
		return (String) selectOne("invino_lolapi.selectTFTApiKey");
	}

	public void updatePuuId(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		update("invino.updatePuuId",map);
		
	}
	public void userGameData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		insert("invino.userGameData",map);
		
	}
	

	public List<Map<String, Object>> selectUserInfoIgnoreAfkTFT(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("invino.selectUserInfoIgnoreAfkTFT", map);
	}

	public String select1() {
		// TODO Auto-generated method stub
		return (String) selectOne("invino_lolapi.select1");
	}


}
