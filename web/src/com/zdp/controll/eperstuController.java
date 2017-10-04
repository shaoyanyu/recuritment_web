package com.zdp.controll;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.zdp.bean.Student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
public class eperstuController {
	//取某个招聘信息中应聘的所有人
	@RequestMapping(value = "eperstu.alrApply.do", method = RequestMethod.POST)
	public void alrApply(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException, UnsupportedEncodingException {
		
		String elistId=request.getParameter("elistId");
		System.out.println(elistId);
		JSONObject jsonObj = DB.alrApply(elistId);
		printWriter.write(  new String( jsonObj.toString().getBytes("UTF-8"),"GB2312")  );
		printWriter.flush();
		printWriter.close();
	}
	//修改应聘信息状态，改成笔试或者面试或者拒绝
	@RequestMapping(value = "eperstu.update.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public void update(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException {
		String elistId=request.getParameter("elistId");
		String uId=request.getParameter("uId");
		String eState=request.getParameter("eState");
		String facetestTime=request.getParameter("facetestTime");
		String writetestTime=request.getParameter("writetestTime");
		
			
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="update eperstu set eState="+eState+", facetestTime='"+facetestTime+"', writetestTime='"+writetestTime+"' where elistId="+elistId+" and uId="+uId;    
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
        int count=stm.executeUpdate(sql); 
    	if(count>0){ 
    		printWriter.write("{\"judge\":true}");		
    	}else{
    		printWriter.write("{\"judge\":false}");
    	}
		printWriter.flush();
		printWriter.close();
	}

}
