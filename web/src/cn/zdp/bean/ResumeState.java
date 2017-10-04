package cn.zdp.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResumeState {
		
		private int elistId;
		private String eTitle;
		private int uId;
		private int eState;
		private String facetestTime;
		private String writetestTime;
	
		public ResumeState(int elistId,int uId) throws SQLException{
			this.uId = uId;
			this.elistId = elistId;
			ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
			DataSource dataSource=ctx.getBean("dataSource",DataSource.class);	
			String sql="select * from eperstu where elistId = '"+elistId+"' and uId = '"+uId+"';";
	    	System.out.println(sql);
			Connection connection=dataSource.getConnection();  
	        Statement stm=connection.createStatement();    
			ResultSet rs=stm.executeQuery(sql);	
			rs.next();
			this.eState = rs.getInt(3);
			this.facetestTime = rs.getString(4);
			this.writetestTime = rs.getString(5);
			System.out.println(this.eState);
			String sql2="select eTitle from elist where elistId = '"+elistId+"';";
			ResultSet rs2=stm.executeQuery(sql2);
			rs2.next();
			this.eTitle = rs2.getString(1);	
			System.out.println(this.eTitle);
		}
		
		public int getelistId() {
			return elistId;
		}
		
		public String geteTitle() {
			return eTitle;
		}
		
		public int getuId() {
			return uId;
		}
	
		public int geteState() {
			return eState;
		}
		
		public String getfacetestTime() {
			return facetestTime;
		}
		
		public String getwritetestTime() {
			return writetestTime;
		}
	}

