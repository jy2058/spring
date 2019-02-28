package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserDao {

	/**
	* Method : getAllUser
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	List<UserVo> getAllUser(SqlSession sqlSession);
	/**
	* Method : selectUser
	* 작성자 : PC08
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 조회
	*/
	UserVo selectUser(SqlSession sqlSession, String userId);
	
	/**
	* Method : selectUserPagingList
	* 작성자 : PC08
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVo);
	
	
	/**
	* Method : getUserCnt
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 수 조최
	*/
	int getUserCnt(SqlSession sqlSession);
	
	/**
	* Method : insertUser
	* 작성자 : PC08
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	
	int insertUser(SqlSession sqlSession, UserVo userVo);
	
	
	/**
	* Method : deleteUser
	* 작성자 : PC08
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(SqlSession sqlSession, String userId);
	
	/**
	* Method : updUser
	* 작성자 : PC08
	* 변경이력 :
	* @param sqlSession
	* @param userVo
	* @return
	* Method 설명 : 사용자 수정
	*/
	int updUser(SqlSession sqlSession, UserVo userVo);
	
	/**
	* Method : encyptPass
	* 작성자 : PC08
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 패스워드 암호화 후 업데이트
	*/
	int encyptPass(SqlSession sqlSession, UserVo userVo);
}
