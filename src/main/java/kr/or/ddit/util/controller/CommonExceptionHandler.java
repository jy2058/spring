package kr.or.ddit.util.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// context:component-scan 에 등록 후 모든 곳에서 사용 가능
@ControllerAdvice
public class CommonExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler({ArithmeticException.class})
	public String handleException(){
		logger.debug("arithmeticException");
		return "error/error";
	}
}
