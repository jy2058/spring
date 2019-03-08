package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	* Method : getAllUser
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVo> getAllUser(){
		List<UserVo> userList = sqlSessionTemplate.selectList("user.getAllUser");
		
		return userList;
	}
	
	public UserVo selectUser(String userId){
		UserVo userVo =  sqlSessionTemplate.selectOne("user.selectUser", userId);
		
		return userVo;
		
	}

	/**
	* Method : selectUserPagingList
	* 작성자 : PC08
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Override
	public List<UserVo> selectUserPagingList(PageVo pageVo) {
		List<UserVo> userList = sqlSessionTemplate.selectList("user.selectUserPagingList", pageVo);
		
		return userList;
	}

	/**
	* Method : getUserCnt
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 수 조회
	*/
	@Override
	public int getUserCnt() {
		int userCnt = sqlSessionTemplate.selectOne("user.getUserCnt");
		return userCnt;
	}

	/**
	* Method : insertUser
	* 작성자 : PC08
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVo userVo) {
		int insertCnt = sqlSessionTemplate.insert("user.insertUser", userVo);
		return insertCnt;
	}

	/**
	* Method : deleteUser
	* 작성자 : PC08
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		int deleteCnt = sqlSessionTemplate.delete("user.deleteUser", userId);
		return deleteCnt;
		
	}

	@Override
	public int updUser(UserVo userVo) {
		int updCnt = sqlSessionTemplate.update("user.updUser", userVo);
		return updCnt;
	}

	@Override
	public int encyptPass(UserVo userVo) {
		int updCnt = sqlSessionTemplate.update("user.encyptPass", userVo);
		return updCnt;
	}
}
