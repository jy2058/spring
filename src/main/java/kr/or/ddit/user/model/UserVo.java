package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserVo implements HttpSessionBindingListener{
	
	// hibernate-validator dependency 추가해서 사용 가능 함
	
	// @NotBlank => white space 가능
	// @NotEmpty => white space도 거절
	// error.properties에 에러코드 : 어노테이션 명 => 메시지 소스에 어노테이션명 , 필드 : 에러메세지 등록
//	@NotEmpty
	private String userId; 		// 사용자 아이디
	private String userNm; 	// 사용자 이름
	private String alias;	// 별명
	private String addr1;	// 주소
	private String addr2;	// 상세주소
	private String zipcode;	// 우편번호
	
//	@Size(min=8)
	private String pass; 	// 사용자 비밀번호
	private String filename; 	// 사용자 비밀번호
	private String realFilename; 	// 사용자 비밀번호
	private Date reg_dt; 	// 등록일시
	
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	
	
	public UserVo(){
		
	}

	public UserVo(String userId, String userNm, String alias, String addr1,
			String addr2, String zipcode, String pass) {
		super();
		this.userId = userId;
		this.userNm = userNm;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.pass = pass;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRealFilename() {
		return realFilename;
	}

	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	// reg_dt 값을 yyyy-MM-dd 포맷팅
	public String getReg_dt_fmt(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(reg_dt);
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userNm=" + userNm + ", alias="
				+ alias + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", zipcode=" + zipcode + ", pass=" + pass + ", filename="
				+ filename + ", realFilename=" + realFilename + ", reg_dt="
				+ reg_dt + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		logger.debug("userVo valueBound : {}", session.getId());
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		logger.debug("userVo valueUnbound : {}", session.getId());
		
	}


	
}
	
	
