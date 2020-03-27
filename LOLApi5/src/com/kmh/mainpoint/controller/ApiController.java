package com.kmh.mainpoint.controller;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kmh.mainpoint.service.ApiService;

@Controller
public class ApiController {

	
		
		
		@Resource(name="apiService")
		private ApiService apiService;
		
		@RequestMapping(value="/invino/openSampleBoardList.do")
	    public ModelAndView openSampleBoardList(Map<String,Object> commandMap) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/ShowUser");
	    	System.out.println("AAA");
	    	List<Map<String,Object>> list = apiService.selectBoardList(commandMap);
	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		
		@RequestMapping(value="/invino/TEST.do")
	    public ModelAndView TEST(Map<String,Object> commandMap) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/attendanceCheck");
	    	
	    	Map<String,Object> map = new HashMap<String, Object>();
	    	
	    	
//	    	map.put("date",date);
//	    	map.put("date2",date2);
//	    	apiService.updateUserSearchDate(map);
	    	List<Map<String,Object>> list = apiService.selectUserInfo(commandMap);
	    	
	    	List<Map<String,Object>> Ignorelist = apiService.selectUserInfoIgnoreAfk(commandMap);
	    	
	    	List<Map<String,Object>> list3 = apiService.GetUserPuuId(Ignorelist);
//	    	List<Map<String,Object>> list2 = apiService.attendanceCheckUser(Ignorelist,map);
	    	
	    	
	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		@RequestMapping(value="/invino/attendanceCheck.do")
	    public ModelAndView attendanceCheck(Map<String,Object> commandMap,@RequestParam(value="date",required=false,defaultValue="fail") String date,@RequestParam(value="date2",required=false,defaultValue="fail") String date2) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/attendanceCheck");
	    	System.out.println(date);
	    	Map<String,Object> map = new HashMap<String, Object>();

	    	
	    	map.put("date",date);
	    	map.put("date2",date2);
	    	apiService.updateUserSearchDate(map);
	    	
	    	List<Map<String,Object>> list = apiService.selectUserInfo(commandMap);
	    	
	    	
	    	List<Map<String,Object>> Ignorelist = apiService.selectUserInfoIgnoreAfk(commandMap);
	    	
	    	apiService.GetUserAccountId(Ignorelist);
	    	
	    	apiService.GetUserPuuId(list);
	    	
//	    	apiService.attendanceCheckUser(Ignorelist,map);
	    	 Ignorelist = apiService.selectUserInfoIgnoreAfkTFT(commandMap);
	    	apiService.checkTFTPlay(Ignorelist,list,map);
	    	
	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		
		
		@RequestMapping(value="/invino/userInsert.do")
	    public ModelAndView userInsert(Map<String,Object> commandMap) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/insertUser");
	    	System.out.println("bbb");
	    	
	    	return mv;
	    }
		@RequestMapping(value="/invino/insertUserProcess.do")
	    public String insertUserProcess(Map<String,Object> commandMap,@RequestParam(value="name",required=false,defaultValue="") String name,@RequestParam(value="tier",required=false,defaultValue="fail") String tier,@RequestParam(value="line",required=false,defaultValue="") String line,@RequestParam(value="authority",required=false,defaultValue="") String authority) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/user");
	    	Map<String,Object> map = new HashMap<String, Object>();
	    	System.out.println(name);
	    	System.out.println(tier);
	    	System.out.println(line);
	    	System.out.println(authority);
	    	
	    	map.put("name",name);
	    	map.put("tier",tier);
	    	map.put("line",line);
	    	map.put("authority",authority);
	    	apiService.insertUser(map);
	    	return "redirect:user.do";

	    	
	    }
		
		@RequestMapping(value="/invino/user.do")
	    public ModelAndView NoattendanceCheck(Map<String,Object> commandMap) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/attendanceCheck");
	    	System.out.println("bbb");
	    	
	    	List<Map<String,Object>> list = apiService.selectUserInfo(commandMap);
	    	System.out.println(list);
	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		
		
		@RequestMapping(value="/invino/delUser.do")
	    public ModelAndView userDel(Map<String,Object> commandMap) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/delUser");
	    	
	    	
	    	List<Map<String,Object>> list = apiService.selectUserInfo(commandMap);

	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		@RequestMapping(value="/invino/userDelP.do")
	    public String userDel(Map<String,Object> commandMap,@RequestParam(value="delUser",required=false,defaultValue="") String delUser) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/user");
	    	System.out.println(delUser);
	    	String name[] = delUser.split(",");
	    	
	    	for(int i=0;i<name.length;i++) {
	    		Map<String,Object> map = new HashMap<String, Object>();
	    		map.put("name",name[i]);
		    	apiService.deleteUser(map);	
	    	}
	    	
	    	
	    
	    	return "redirect:user.do";
	    }
		@RequestMapping(value="/invino/user.do/riot.txt")
		@ResponseStatus(HttpStatus.OK)
	    public void riot(HttpServletRequest req, HttpServletResponse res) throws Exception{
			String href = req.getSession().getServletContext().getRealPath("/");
		    File f = new File(href+"resources/riot.txt");
		    System.out.println(req.getAttribute("javax.servlet.forward.request_uri"));
System.out.println(req.getSession().getServletContext().getRealPath("/"));

		    String downloadName = null;
		    String browser = req.getHeader("User-Agent");
		    //파일 인코딩
		    if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
		      //브라우저 확인 파일명 encode  		             
		      downloadName = URLEncoder.encode(f.getName(), "UTF-8").replaceAll("\\+", "%20");		             
		    }else{		             
		      downloadName = new String(f.getName().getBytes("UTF-8"), "ISO-8859-1");

		    }        
		    res.setHeader("Content-Disposition", "attachment;filename=\"" + downloadName +"\"");             
		    res.setContentType("application/octer-stream");
		    res.setHeader("Content-Transfer-Encoding", "binary;");

		    try(FileInputStream fis = new FileInputStream(f);
		        ServletOutputStream sos = res.getOutputStream();	){

		      byte[] b = new byte[1024];
		      int data = 0;

		      while((data=(fis.read(b, 0, b.length))) != -1){		             
		        sos.write(b, 0, data);		             
		      }

		      sos.flush();
		    } catch(Exception e) {
		      throw e;
		    }
	    }
		
		

}
