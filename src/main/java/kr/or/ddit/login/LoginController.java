package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	* Method : loginView
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 로그인 화면 요청을 처리
	*/
	
	// method 속성이 설정되어 있지 않으면 모든 method에 대해 처리
	@RequestMapping(path={"/login"}, method={RequestMethod.GET})
	public String loginView(){
		
		//<property name="prefix" value="/WEB-INF/views/"/>
		//<property name="suffix" value=".jsp"/>
		
		// "/WEB-INF/views/" + "login/login" + ".jsp"
		return "login/login";
	}
	
	@RequestMapping(path={"/login"}, method={RequestMethod.POST})
	public String loginProcess(UserVo userVo, HttpSession session){	// param들이 UserVo에 있는 객체면 저절로 담김
																	// session이 필요하면 여기에 설정
	
		// 사용자가 요청한 id에 해당하는 실제 데이터 베이스에 저장된 값
		UserVo dbUserVo = userService.selectUser(userVo.getUserId());
		
		// 정상 로그인 한 경우
		if(dbUserVo.getUserId().equals(userVo.getUserId()) && dbUserVo.getPass().equals(KISA_SHA256.encrypt(userVo.getPass()))){
			session.setAttribute("userVo", dbUserVo);
			
			return "main";
		}
		else
			return "login/login";
	}

}
