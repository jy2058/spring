package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;
import kr.or.ddit.ranger.service.IRangerService;
import kr.or.ddit.ranger.service.RangerServiceImpl;

// 해당 자바파일이 스프링 빈 설정 클래스임을 알려주는 어노테이션
//@Configuration
public class SpringJavaConfig {
	// <bean name="">
	@Bean
	public IRangerDao getRangerDao() {
		IRangerDao rangerDao = new RangerDaoImpl();
		
		return rangerDao;
	}
	
	@Bean
	public IRangerService rangerService() {
		// rangerService에 주입해야 하는 rangerDao 객체는 스프링에서 관리되는 스프링 빈을 주입해야 한다.
		// *** 주입 할 rangerDao를 new 연산자를 통해서 생성하면 안 된다.
		IRangerService rangerService = new RangerServiceImpl(getRangerDao());	// 메소드를 통해서 새로 만드는 것이 아니라 메소드를 통해서 불러오는 것
		return rangerService;
		
	}
}
