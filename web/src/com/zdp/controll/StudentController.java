package com.zdp.controll;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zdp.bean.ResumeState;
import cn.zdp.bean.Student;
import cn.zdp.bean.Tjuresume;



@Controller
public class StudentController {
	
	//学生登录
	@RequestMapping(value = "student.login.do", method = RequestMethod.POST)
	public void login(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
//		System.out.println(request.getParameter("aage"));
//		String jsonString = JSON.toJSONString(person, SerializerFeature.PrettyFormat);
//		System.out.println(request.getParameter("aage").toString());
//		printWriter.write(request.getParameter("aage").toString());
//		printWriter.flush();
//		printWriter.close();
		String sId=request.getParameter("sId");
		String sPwd=request.getParameter("sPwd");
		System.out.println(sId);
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select sPwd,uId from student where sId ='"+sId+"';";
		System.out.println(sql);

    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
    	if(rs.next()){ 
    		String s= rs.getString(1);
    		int uId = rs.getInt(2);
    		if(s.equals(sPwd)){
    			session.removeAttribute("eId");
    			session.removeAttribute("eAccount");
    			session.setAttribute("uId", uId+"");
    			session.setAttribute("sId", sId);
    			printWriter.write("{\"judge\":true}");
    		}
    		else{
    			
    			printWriter.write("{\"judge\":false}");
    		}
    		
    	}else{
    		printWriter.write("{\"judge\":false}");
    	}
		printWriter.flush();
		printWriter.close();
	}
	
	//学生登录信息检查
	@RequestMapping(value = "student.check.do", method = RequestMethod.POST)
	public void check(PrintWriter printWriter,HttpServletRequest request) throws SQLException {

		String sId=request.getParameter("sId");
		String sPwd=request.getParameter("sPwd");
		System.out.println(sId);
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from student where sId ='"+sId+"';";
		System.out.println(sql);

    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
    	if(rs.next()){ 
    		printWriter.write("{\"judge\":false}");
    		    		
    	}else{
    		printWriter.write("{\"judge\":true}");
    	}
		printWriter.flush();
		printWriter.close();
	}
	
	//学生注册
	@RequestMapping(value = "student.register.do", method = RequestMethod.POST)
	public void register(PrintWriter printWriter,HttpServletRequest request) throws SQLException {
//		System.out.println(request.getParameter("aage"));
//		String jsonString = JSON.toJSONString(person, SerializerFeature.PrettyFormat);
//		System.out.println(request.getParameter("aage").toString());
//		printWriter.write(request.getParameter("aage").toString());
//		printWriter.flush();
//		printWriter.close();
		String sId=request.getParameter("sId");
		String sPwd=request.getParameter("sPwd");
		String sName=request.getParameter("sName");
		String sCollege=request.getParameter("sCollege");
		String sMajor=request.getParameter("sMajor");
		String sGrade=request.getParameter("sGrade");
		String sSex=request.getParameter("sSex");
		String sTel=request.getParameter("sTel");
		String selfIntro=request.getParameter("selfIntro");
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="insert into student(sId,sPwd,sName,sCollege,sMajor,sGrade,sSex,sTel,selfIntro)values('"+sId+"','"+sPwd+"','"+sName+"','"+sCollege+"','"+sMajor+"','"+sGrade+"','"+sSex+"','"+sTel+"','"+selfIntro+"')";    
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
	//学生个人信息
	@RequestMapping(value = "student.information.do", method = RequestMethod.POST)
	public void sInformation(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {	
		if(session.getAttribute("sId") == null){
			System.out.println("there is no session"); 
			//return "index"; 
		} 
		else{
		String sId=session.getAttribute("sId").toString();
	//	int uId=(int) session.getAttribute("uId");
		int uId = Integer.parseInt((String) session.getAttribute("uId"));
		Student student = new Student(sId);
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from tjuresume where uId ='"+uId+"';";
		System.out.println(sql);	
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
		Tjuresume resume = new Tjuresume();
    	if(rs.next()){
			 resume.putTjuresume(uId);
		}	
		JSONObject  studentinfor = new JSONObject();
    	studentinfor.put("resume", resume);
		studentinfor.put("student", student);
		
		
    	System.out.println(studentinfor.toString());
    	printWriter.write(studentinfor.toString());
    	
		printWriter.flush();
		printWriter.close();
		//return "";
		}
	}
	//个人简历修改
	@RequestMapping(value = "student.resumeChange.do", method = RequestMethod.POST)
	public void resumeChange(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
		int uId = Integer.parseInt((String) session.getAttribute("uId")); 
		String personalInform=request.getParameter("personalInform");
		String majorSkill=request.getParameter("majorSkill");
		String eduExp=request.getParameter("eduExperience");
		String personalExp=request.getParameter("personalExp");
		
		Tjuresume Changeresume = new Tjuresume();
		if(Changeresume.resumechange(uId, personalInform, majorSkill, eduExp, personalExp)){
			printWriter.write("{\"judge\":true}");
		}else{
    		printWriter.write("{\"judge\":false}");
    	}
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping(value = "student.resumeState.do", method = RequestMethod.POST)
	public void resumestate(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
		if(session.getAttribute("sId") == null){
			System.out.println("there is no session in resume"); 
			return;
		}
		
		int uId = Integer.parseInt((String) session.getAttribute("uId"));
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
    	DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
    	String sql="select * from eperstu where uId ='"+uId+"';";
		System.out.println(sql);	
    	Connection connection=dataSource.getConnection();  
        Statement stm=connection.createStatement();    
    	ResultSet rs=stm.executeQuery(sql); 
    	JSONObject  resState = new JSONObject();
    	int count = 1;
    	while(rs.next()){
			ResumeState resumestate = new ResumeState(rs.getInt(1),uId);
			resState.put("state_"+count, resumestate);
			count++;
		}
    	System.out.println(resState.toString());
    	printWriter.write(resState.toString());
		
		printWriter.flush();
		printWriter.close();		
	}
	
	//
	@RequestMapping(value = "student.sendresume.do", method = RequestMethod.POST)
	public void sendresume(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
		Object obj = session.getAttribute("uId");
		if(obj == null){
			printWriter.write("{\"judge\":\"student not login\"}");
		}
		else{
			int uId = Integer.parseInt((String) session.getAttribute("uId"));
			int elistId = Integer.parseInt(request.getParameter("elistId"));
			ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
			DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
			String sql="select * from eperstu where uId ='"+uId+"' and elistId ='"+elistId+"';";
			System.out.println(sql);	
			Connection connection=dataSource.getConnection();  
			Statement stm=connection.createStatement();    
			ResultSet rs=stm.executeQuery(sql); 
			if(rs.next()){
				printWriter.write("{\"judge\":\"false\"}");
			}
			else{
				String sql2="insert into eperstu (elistId,uId,eState) values('"+elistId+"','"+uId+"',1);";
				int rs2 = stm.executeUpdate(sql2);
				printWriter.write("{\"judge\":\"send succeed\"}");
			}
		
			printWriter.flush();
			printWriter.close();		
		}

	}
		@RequestMapping(value = "student.cancel.do", method = RequestMethod.POST)
		public void cancelResume(PrintWriter printWriter,HttpServletRequest request,HttpSession session) throws SQLException {
			Object obj = session.getAttribute("uId");
			if(obj == null){
				printWriter.write("{\"judge\":\"student not login\"}");
			}
			else{
				int uId = Integer.parseInt((String) session.getAttribute("uId"));
				int elistId = Integer.parseInt(request.getParameter("elistId"));
				ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-servlet.xml");   
				DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
				String sql="delete  from eperstu where uId ='"+uId+"' and elistId ='"+elistId+"';";
				System.out.println(sql);	
				Connection connection=dataSource.getConnection();  
				Statement stm=connection.createStatement();    
				int rs=stm.executeUpdate(sql); 
				if(rs > 0){
					printWriter.write("{\"judge\":\"delete succeed\"}");
				}else{
					printWriter.write("{\"judge\":\"delete failed\"}");
				}
				printWriter.flush();
				printWriter.close();		
		}
	}
	
}