package com.kmh.mainpoint.controller;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		@RequestMapping(value="/invino/attendanceCheck.do")
	    public ModelAndView attendanceCheck(Map<String,Object> commandMap,@RequestParam(value="date",required=false,defaultValue="fail") String date) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/attendanceCheck");
	    	System.out.println(date);
	    	Map<String,Object> map = new HashMap<String, Object>();


	    	map.put("date",date);
	    	List<Map<String,Object>> list = apiService.selectUserInfo(commandMap);
	    	
	    	List<Map<String,Object>> Ignorelist = apiService.selectUserInfoIgnoreAfk(commandMap);
	    	
	    	List<Map<String,Object>> list3 = apiService.GetUserAccountId(Ignorelist);
	    	List<Map<String,Object>> list2 = apiService.attendanceCheckUser(Ignorelist,map);
	    	
	    	
	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		@RequestMapping(value="/invino/user.do")
	    public ModelAndView NoattendanceCheck(Map<String,Object> commandMap) throws Exception{
	    	ModelAndView mv = new ModelAndView("/invino/attendanceCheck");
	    	System.out.println("bbb");
	    	
	    	List<Map<String,Object>> list = apiService.selectUserInfo(commandMap);

	    	mv.addObject("list", list);
	    	
	    	return mv;
	    }
		@RequestMapping(value="/invino/riot.txt")
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
