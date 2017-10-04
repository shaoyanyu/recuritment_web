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

import cn.zdp.bean.Student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
public class ElistController {
	@RequestMapping(value = "elist.alrPublish.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public void check(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException, UnsupportedEncodingException {
		response.setContentType("application/json;charset=UTF-8");
		String eId= (String)session.getAttribute("eId");
		System.out.println(eId+"elist");
		JSONObject jsonObj = DB.employPublish(eId);
//		List<Integer> list = new ArrayList<Integer>();
//		int a = 0;
//		int b = 1;
//		list.add(a);
//		list.add(b);
		System.out.println(jsonObj.toString());
		//printWriter.write(jsonObj.toString());
		printWriter.write(  new String( jsonObj.toString().getBytes("UTF-8"),"GB2312")  );
		
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping(value = "elist.getAll.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public void getAll(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException, UnsupportedEncodingException {		
		JSONObject jsonObj = DB.allPublish();
		System.out.println(jsonObj.toString());
		printWriter.write(  new String( jsonObj.toString().getBytes("UTF-8"),"GB2312")  );	
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping(value = "elist.detail.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public void detail(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException {
		response.setContentType("application/json;charset=UTF-8");
		String elistId = request.getParameter("elistId");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from elist where elistId ='"+elistId+"';";
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
    	String s = new String("{");
    	if(rs.next()){ 	
    		s=s+"\"eId\":"+rs.getInt(2)+",";
    		s=s+"\"eClass\":\""+rs.getString(3)+"\",";
    		s=s+"\"eTitle\":\""+rs.getString(4)+"\",";
    		s=s+"\"eListIntro\":\""+rs.getString(5)+"\",";
    		s=s+"\"ePersonNum\":"+rs.getInt(6)+"}";
    		System.out.println(s);
    		printWriter.write(s);
    	}else{
    		printWriter.write("{\"judge\":false}");
    	}
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping(value = "elist.publish.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public void register(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException {
		response.setContentType("application/json;charset=UTF-8");
		String eId= (String)session.getAttribute("eId");
		String eClass=request.getParameter("eClass");
		String eTitle=request.getParameter("eTitle");
		String eListIntro=request.getParameter("eListIntro");
		String ePersonNum=request.getParameter("ePersonNum");
		
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="insert into elist(eId,eClass,eTitle,eListIntro,ePersonNum)values('"+eId+"','"+eClass+"','"+eTitle+"','"+eListIntro+"','"+ePersonNum+"')";    
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
        int count=stm.executeUpdate(sql); 
    	if(count!=0){ 
    		printWriter.write("{\"judge\":true}");		
    	}else{
    		printWriter.write("{\"judge\":false}");
    	}
		printWriter.flush();
		printWriter.close();
	}
	
	
	@RequestMapping(value = "elist.change.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public void change(PrintWriter printWriter,HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws SQLException {
		String elistId=request.getParameter("elistId");
		String eListIntro=request.getParameter("eListIntro");
		String eTitle=request.getParameter("eTitle");
			
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="update elist set eTitle='"+eTitle+"', eListIntro='"+eListIntro+"' where elistId="+elistId;    
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
