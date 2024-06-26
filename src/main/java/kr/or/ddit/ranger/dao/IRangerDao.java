package kr.or.ddit.ranger.dao;

import java.util.List;
import java.util.Map;

/**
* IRangerDao.java
*
* @author PC08
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC08 최초 생성
*
* </pre>
*/
public interface IRangerDao {
	
	/**
	* Method : getRangers
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 레인저스 조회
	*/
	List<String> getRangers(); 
	
	/**
	* Method : getRanger
	* 작성자 : PC08
	* 변경이력 :
	* @param listIndex
	* @return
	* Method 설명 : listIndex에 해당하는 레인저 이름을 반환
	*/
	String getRanger(int listIndex);
	
	/**
	* Method : getRangersDb
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : db를 통한 레인저스 전체 조회
	*/
	List<Map<String, String>> getRangersDb();
	
	/**
	* Method : getRanger
	* 작성자 : PC08
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 레인저 아이디로 레인저 정보 조회
	*/
	Map<String, String> getRanger(String id);
	
	/**
	* Method : insertRanger
	* 작성자 : PC08
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 신규 레인저 등록
	*/
	int insertRanger(Map<String, String> map);	
	
	/**
	* Method : deleteRanger
	* 작성자 : PC08
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 레인저 삭제
	*/
	int deleteRanger(String id);
	
	/**
	* Method : deleteRangerDept
	* 작성자 : PC08
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 레인저 소속 삭제
	*/
	int deleteRangerDept(String id);
	}
