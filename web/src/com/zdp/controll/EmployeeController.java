package com.zdp.controll;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zdp.bean.Employee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
public class EmployeeController {
	//登录页面

	@RequestMapping(value = "employee.login.do", method = RequestMethod.POST)
	public void eLogin(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
		String eAccount=request.getParameter("eAccount");
		String ePwd=request.getParameter("ePwd");
		
		DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
		String sql="select ePwd,eId from employee where eAccount = " + "'"+eAccount+"';";  
		System.out.printf(sql);
		Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();   
        ResultSet rs=stm.executeQuery(sql); 
        if(rs.next()){ 
    		String e= rs.getString(1);
    		int eId = rs.getInt(2);
    		if(e.equals(ePwd) ){	
    			session.removeAttribute("uId");
    			session.removeAttribute("sId");
    			System.out.println(eId+"employ");
    			session.setAttribute("eId", eId+"");
    			session.setAttribute("eAccount", eAccount);
    			printWriter.write("{\"judge\":true}");
    		}
    		else{
    			printWriter.write("{\"judge\":false}");
    		}
    	 }
        else{
    		printWriter.write("{\"judge\":false}");
    	}
		printWriter.flush();
		printWriter.close();
       

	}
	
	//用户名占用检测
	@RequestMapping(value = "employee.judge.do", method = RequestMethod.POST)
	public void accountJudge(PrintWriter printWriter,HttpServletRequest request) throws SQLException {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
		
		String eAccount=request.getParameter("eAccount");		
		DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
		String sql="select * from employee where eAccount = " + eAccount; 
		
		Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();   
        ResultSet rs=stm.executeQuery(sql); 
        if(rs.next()){ 
    			printWriter.write("{\"judge\":false}");
    		}
        else{
    			printWriter.write("{\"judge\":true}");
    		}  
    	printWriter.flush();
		printWriter.close();
       } 
	//取招聘者信息
	@RequestMapping(value = "employee.get.do", method = RequestMethod.POST)
	public void get(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  	
		String eAccount=(String) session.getAttribute("eAccount");		
		DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
		String sql="select * from employee where eAccount='"+eAccount+"'"; 		
		Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();   
        ResultSet rs=stm.executeQuery(sql); 
        String s = new String("{");
    	if(rs.next()){ 	
    		s=s+"\"eId\":"+rs.getInt(1)+",";
    		s=s+"\"eAccount\":\""+rs.getString(2)+"\",";
    		s=s+"\"eName\":\""+rs.getString(4)+"\",";
    		s=s+"\"eTel\":\""+rs.getString(5)+"\",";
    		s=s+"\"eIntro\":\""+rs.getString(6)+"\"}";
    		System.out.println(s);
    		printWriter.write(s);
    	}else{
    		printWriter.write("{\"judge\":false}");
    	}
    	printWriter.flush();
		printWriter.close();
       } 
	
	
	//注册
	@RequestMapping(value = "employee.register.do", method = RequestMethod.POST)
	public void eRegist(PrintWriter printWriter,HttpServletRequest request) throws SQLException {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
		
		String eAccount=request.getParameter("eAccount");
		String ePwd=request.getParameter("ePwd");
		String eName=request.getParameter("eName");
		String eTel=request.getParameter("eTel");
		String eIntro=request.getParameter("eIntro");
		
		DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
 
		String sql="insert into employee (eAccount,ePwd,eName,eTel,eIntro) values('" +
    						 eAccount+"','"+ePwd+"','"+eName+"','"+eTel+"','"+eIntro+"');";  		System.out.printf(sql); 
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

	//雇主个人信息


}
