package kr.or.ddit.ranger.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RangerVo {
	private String userId;
	private String userNm;
	private int listIndex;
	
	
	public int getListIndex() {
		return listIndex;
	}

	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}

	@DateTimeFormat(pattern="MM-dd-yyyy")
	private Date birth;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDt;
	
	public RangerVo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	
	@Override
	public String toString() {
		return "RangerVo [userId=" + userId + ", userNm=" + userNm + ", listIndex=" + listIndex + ", birth=" + birth
				+ ", regDt=" + regDt + "]";
	}

	public RangerVo(String userId, String userNm, Date birth) {
		super();
		this.userId = userId;
		this.userNm = userNm;
		this.birth = birth;
	}
	
	
}