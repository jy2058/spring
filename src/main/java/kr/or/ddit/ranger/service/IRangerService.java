package kr.or.ddit.ranger.service;

import java.util.List;

import kr.or.ddit.ranger.dao.IRangerDao;

public interface IRangerService {
	/**
	* Method : getRangers
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 레인저스 조회
	*/
	List<String> getRangers();
	
	IRangerDao getRangerDao();

	String getRanger(int index);
	
}
