package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class userVoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override			// Vo객체		// 에러 가공
	public void validate(Object target, Errors errors) {
		UserVo userVo = (UserVo) target;
		// 비밀번호는 8자리 이상이어야 한다.
		if(userVo.getPass().length() < 8){
			errors.rejectValue("pass", "passLen");
		}
		
		// 사용자 아이디 검증 (빈 값이면 안 된다)
		if(userVo.getUserId().equals("")){
			errors.rejectValue("userId", "required");
		}
		
		if(userVo.getUserId().length() < 6){
			errors.rejectValue("userId", "userIdLen");
		}
		
	}
	
}
