package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public UserServiceImpl(){
		//userDao = new UserDaoImpl();
	}
	
	
	/**
	* Method : getAllUser
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVo> getAllUser(){
		List<UserVo> userList = userDao.getAllUser();
		return userList;
	}

	@Override
	public UserVo selectUser(String userId) {
		UserVo userVo = userDao.selectUser(userId); 
		return userVo;
	}

	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());
		
		return resultMap;
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
		
		int insertCnt = userDao.insertUser(userVo);
		
		return insertCnt;
	}

	@Override
	public int deleteUser(String userId) {
		
		int deleteCnt = userDao.deleteUser(userId);
		
		return deleteCnt;
	}


	@Override
	public int updUser(UserVo userVo) {
		
		int updCnt = userDao.updUser(userVo);
		
		return updCnt;
	}


	@Override
	public int encyptPass() {
		
		int updCnt=0;
		
		List<UserVo> userList = userDao.getAllUser();
		
		for(UserVo userVo : userList){
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			updCnt += userDao.encyptPass(userVo);
		}
		
		
		return updCnt;
	}



}
