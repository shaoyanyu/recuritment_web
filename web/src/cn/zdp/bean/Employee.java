package cn.zdp.bean;

import java.sql.Date;

public class Employee {
		private int eId;
		private String eAccount;
		private String ePwd;
		private String eName;
		private String eTel;
		private String eIntro;
		
		
		public int getEId() {
			return eId;
		}
		public String getEAccount() {
			return eAccount;
		}
		public String getEPwd() {
			return ePwd;
		}
		public String getEName() {
			return  eName;
		}
		public String getETel() {
			return eTel;
		}
		public String getEIntro() {
			return eIntro;
		}
		
			
		public void setEId(int uId) {
			this.eId = eId;
		}
		public void setEAccount(String sId) {
			this.eAccount = eAccount;
		}
		public void setEPwd(String sPwd) {
			this.ePwd = ePwd;
		}
		public void setEName(String sCollege) {
			this.eName = eName;
		}
		public void setETel(String sName) {
			this.eTel = eTel;
		}
		public void setEIntro(String sSex) {
			this.eIntro = eIntro;
		}
		
	}

