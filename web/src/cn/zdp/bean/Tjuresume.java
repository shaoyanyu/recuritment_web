package cn.zdp.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tjuresume {
		private int uId;
		private String personalInform;
		private String majorSkill;
		private String eduExp;
		private String personalExp;
		

		public Tjuresume() {	
		}
		
		public void putTjuresume(int uId) throws SQLException{
			this.uId = uId;
			ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
			DataSource dataSource=ctx.getBean("dataSource",DataSource.class);	
			String sql="select * from tjuresume where uId ='"+uId+"';";
	    	System.out.println(sql);
			Connection connection=dataSource.getConnection();  
	        Statement stm=connection.createStatement();    
			ResultSet rs=stm.executeQuery(sql); 	
			if(rs.next()){
				this.personalInform = rs.getString(2);
				this.majorSkill = rs.getString(3);
				this.eduExp = rs.getString(4);
				this.personalExp = rs.getString(5);	
			}
			else{
				String sql2="insert into tjuresume (uId) values("+uId+");";
				ResultSet rs2=stm.executeQuery(sql2); 
				rs2.next();
			}
		}
		
		public boolean resumechange(int uId,String personalInform,String majorSkill,String eduExp,String personalExp) throws SQLException{
			ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");  
			DataSource dataSource=ctx.getBean("dataSource",DataSource.class);	
			String sql="select * from tjuresume where uId = '"+uId+"';";
			Connection connection=dataSource.getConnection();  
	        Statement stm=connection.createStatement(); 
			ResultSet rs = stm.executeQuery(sql);
	        if(rs.next()){
	        	String sql2="update tjuresume set personalInfrom = '"+personalInform+"',majorSkill = '"+majorSkill
					+"',eduExp = '"+eduExp+"', personalExp ='"+personalExp+"' where uId ='"+uId+"';";
	        	
	        	int rs2=stm.executeUpdate(sql2);
	        	if(rs2 == 1){ 
	        		return true;    		
	        	}
	        }
	        else{
	        	String sql3="insert into tjuresume (uId,personalInfrom,majorSkill,eduExp, personalExp) values('"
	        				+uId+"','"+personalInform+"','"+majorSkill+"','"+eduExp+"','"+personalExp+"')";
	        	System.out.println(sql3);
	        	int rs3=stm.executeUpdate(sql3);
	        	if(rs3 == 1){ 
	        		return true;    		
	        	}
	        }		
			return false;
		}
		
		
		public int getuId() {
			return uId;
		}

		public String getpersonalInform() {
			return personalInform;
		}
		
		public String getmajorSkill() {
			return majorSkill;
		}
		
		public String geteduExperience() {
			return eduExp;
		}
		
		public String getpersonalExp() {
			return personalExp;
		}
		
		
		public void setPersonalInfrom(int uId) {
			this.uId = uId;
		}
		
		public void setmajorSkill(String personalInfrom) {
			this.personalInform = personalInfrom;
		}
		public void seteduExp(String eduExp) {
			this.eduExp = eduExp;
		}
		public void setersonalExp(String personalExp) {
			this.personalExp = personalExp;
		}
		
	}

