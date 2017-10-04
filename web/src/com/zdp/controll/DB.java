
package com.zdp.controll;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

import cn.zdp.bean.Student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class DB {
	static public JSONObject employPublish(String eId) throws SQLException {
		System.out.println(eId+"DB");
		JSONObject jsonObj = new JSONObject();
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from elist where eId ='"+eId+"';";
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
    	if(!rs.next()){
    		return jsonObj;
    	}
    	rs.last();
    	int num = rs.getRow();
    	rs.first();
    	
    	int[] elistId = new int[num];
    	String[] eTitle = new String[num];
    	elistId[0]=rs.getInt(1);
    	eTitle[0]=rs.getString(4);
    	int i =1;
    	while(rs.next() && i < num){ 
    		elistId[i]=rs.getInt(1);
        	eTitle[i]=rs.getString(4);
    		i++;
    		    		
    	}
    	
    	jsonObj.put("elistId", elistId);
    	jsonObj.put("eTitle", eTitle);
    	return jsonObj;
	}
	//elist表取所有应聘信息
	static public JSONObject allPublish() throws SQLException {
		JSONObject jsonObj = new JSONObject();
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from elist";
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
    	if(!rs.next()){
    		return jsonObj;
    	}
    	rs.last();
    	int num = rs.getRow();
    	rs.first();
    	
    	int[] elistId = new int[num];
    	int[] eId = new int[num];
    	String[] eClass = new String[num];
    	String[] eTitle = new String[num];
    	String[] eListIntro = new String[num];
    	int[] ePersonNum = new int[num];
    	
    	elistId[0]=rs.getInt(1);
    	eId[0]=rs.getInt(2);
    	eClass[0]=rs.getString(3);
    	eTitle[0]=rs.getString(4);
    	eListIntro[0]=rs.getString(5);
    	ePersonNum[0]=rs.getInt(6);
    	
    	
    	int i =1;
    	while(rs.next() && i < num){ 
        	elistId[i]=rs.getInt(1);
        	eId[i]=rs.getInt(2);
        	eClass[i]=rs.getString(3);
        	eTitle[i]=rs.getString(4);
        	eListIntro[i]=rs.getString(5);
        	ePersonNum[i]=rs.getInt(6);
    		i++;
    		    		
    	}
    	
    	jsonObj.put("elistId", elistId);
    	jsonObj.put("eId", eId);
    	jsonObj.put("eClass", eClass);
    	jsonObj.put("eTitle", eTitle);
    	jsonObj.put("eListIntro", eListIntro);
    	jsonObj.put("ePersonNum", ePersonNum);
    	return jsonObj;
	}
	
	//eperstu表取某条招聘信息对应的应聘人员
	static public JSONObject alrApply(String elistId) throws SQLException {	
		JSONObject jsonObj = new JSONObject();
    	
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from eperstu where elistId="+elistId+" and eState<>2";
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
        Statement stm2=connection.createStatement();   
    	ResultSet rs=stm.executeQuery(sql); 
    	if(!rs.next()){
    		return jsonObj;
    	}
    	rs.last();
    	int num = rs.getRow();
    	rs.first();
    	
    	int[] uId = new int[num];
    	int[] eState= new int[num];
    	String[] facetestTime = new String[num];
    	String[] writetestTime = new String[num];
    	String[] sName = new String[num];
    	String[] sCollege = new String[num];
    	String[] sMajor = new String[num];
    	String[] sGrade = new String[num];
    	String[] sSex = new String[num];
    	//Date[] sBirth = new Date[num];
    	String[] sTel = new String[num];
    	String[] selfIntro = new String[num];
    	 
    	
    	uId[0]=rs.getInt(2);
    	String sql2="select * from student where uId="+uId[0];
    	ResultSet rs2=stm2.executeQuery(sql2);
    	if(rs2.next()){
    		sName[0]=rs2.getString(4);
    		sCollege[0]=rs2.getString(5);
    		sMajor[0]=rs2.getString(6);
    		sGrade[0]=rs2.getString(7);
    		sSex[0]=rs2.getString(8);
    		//sBirth[0]=rs2.getDate(9);
    		sTel[0]=rs2.getString(9);
    		selfIntro[0]=rs2.getString(10);
    	}
    	
    	eState[0]=rs.getInt(3);
    	facetestTime[0]=rs.getString(4);
    	writetestTime[0]=rs.getString(5);

    	
    	
    	int i =1;
    	while(rs.next() && i < num){ 
    		uId[i]=rs.getInt(2);
    		sql2="select * from student where uId="+uId[i];
        	rs2=stm2.executeQuery(sql2);
        	if(rs2.next()){
        		sName[i]=rs2.getString(4);
        		sCollege[i]=rs2.getString(5);
        		sMajor[i]=rs2.getString(6);
        		sGrade[i]=rs2.getString(7);
        		sSex[i]=rs2.getString(8);
        		//sBirth[i]=rs2.getDate(9);
        		sTel[i]=rs2.getString(9);
        		selfIntro[i]=rs2.getString(10);
        	}    		
        	eState[i]=rs.getInt(3);
        	facetestTime[i]=rs.getString(4);
        	writetestTime[i]=rs.getString(5);
    		i++;	    		
    	}    	
    	jsonObj.put("uId", uId);
    	jsonObj.put("eState", eState);
    	jsonObj.put("facetestTime", facetestTime);
    	jsonObj.put("writetestTime", writetestTime);
    	
    	jsonObj.put("sName", sName);
    	jsonObj.put("sCollege", sCollege);
    	jsonObj.put("sMajor", sMajor);
    	jsonObj.put("sGrade", sGrade);
    	jsonObj.put("sSex", sSex);
    	//jsonObj.put("sBirth", sBirth);
    	jsonObj.put("sTel", sTel);
    	jsonObj.put("selfIntro", selfIntro);

    	return jsonObj;
	}
	

}
