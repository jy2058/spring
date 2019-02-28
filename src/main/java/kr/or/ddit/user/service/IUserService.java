package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserService {
	List<UserVo> getAllUser();
	UserVo selectUser(String userId);
	
	Map<String, Object> selectUserPagingList(PageVo pageVo);
	
	/**
	* Method : insertUser
	* 작성자 : PC08
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVo userVo);
	
	int deleteUser(String userId);
	
	int updUser(UserVo userVo);
	
	int encyptPass();
	
}
