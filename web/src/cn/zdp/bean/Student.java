package cn.zdp.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Student {
		private int uId;
		private String sId;
		private String sPwd;
		private String sName;
		private String sCollege;
		private String sMajor;
		private String sGrade;
		private String sSex;
		private String sTel;
		private String selfIntro;
		

		public Student(String sId) throws SQLException{
			ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
			DataSource dataSource=ctx.getBean("dataSource",DataSource.class);	
			String sql="select * from student where sId ='"+sId+"';";
//	    	System.out.println(sql);
			Connection connection=dataSource.getConnection();  
	        Statement stm=connection.createStatement();    
	    	ResultSet rs=stm.executeQuery(sql); 	
			rs.next();
			this.uId = rs.getInt(1);
			this.sId = rs.getString(2);
			this.sPwd = rs.getString(3);
			this.sName = rs.getString(4);
			this.sCollege = rs.getString(5);
			this.sMajor = rs.getString(6);
			this.sGrade = rs.getString(7);
			this.sSex= rs.getString(8);
			this.sTel = rs.getString(9);
			this.selfIntro = rs.getString(10);		
			
		}
		
		public int getuId() {
			return uId;
		}
		public String getsId() {
			return sId;
		}
		public String getsPwd() {
			return sPwd;
		}
		public String getsName() {
			return sName;
		}
		public String getsCollege() {
			return sCollege;
		}
		public String getsMajor() {
			return sMajor;
		}
		public String getsGrade() {
			return sGrade;
		}
		public String getsSex() {
			return sSex;
		}

		public String getsTel() {
			return sTel;
		}
		public String getselfIntro() {
			return selfIntro;
		}
		
		
		public void setUId(int uId) {
			this.uId = uId;
		}
		public void setSId(String sId) {
			this.sId = sId;
		}
		public void setSPwd(String sPwd) {
			this.sPwd = sPwd;
		}
		public void setSCollege(String sCollege) {
			this.sCollege = sCollege;
		}
		public void setSName(String sName) {
			this.sName = sName;
		}
		public void setSSex(String sSex) {
			this.sSex = sSex;
		}
		public void setSMajor(String sMajor) {
			this.sMajor = sMajor;
		}
		public void setSGrade(String sGrade) {
			this.sGrade = sGrade;
		}
		public void setSTel(String sTel) {
			this.sTel = sTel;
		}
		public void setSelfIntro(String selfIntro) {
			this.selfIntro = selfIntro;
		}
		
		

//		public String toString() {
//			return "{\"name\": \"" + name + "\", \"age\": " + age*2 + "}";
//		}
	}

