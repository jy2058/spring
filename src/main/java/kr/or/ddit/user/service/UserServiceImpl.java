package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

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
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserVo> userList = userDao.getAllUser(sqlSession);
		sqlSession.close();
		return userList;
	}

	@Override
	public UserVo selectUser(String userId) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserVo userVo = userDao.selectUser(sqlSession, userId); 
		sqlSession.close();
		return userVo;
	}

	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.selectUserPagingList(sqlSession, pageVo));
		resultMap.put("userCnt", userDao.getUserCnt(sqlSession));
		sqlSession.close();
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
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int insertCnt = userDao.insertUser(sqlSession, userVo);
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int deleteUser(String userId) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int deleteCnt = userDao.deleteUser(sqlSession, userId);
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCnt;
	}


	@Override
	public int updUser(UserVo userVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int updCnt = userDao.updUser(sqlSession, userVo);
		sqlSession.commit();
		sqlSession.close();
		
		return updCnt;
	}


	@Override
	public int encyptPass() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int updCnt=0;
		
		List<UserVo> userList = userDao.getAllUser(sqlSession);
		
		for(UserVo userVo : userList){
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			updCnt += userDao.encyptPass(sqlSession, userVo);
		}
		
		sqlSession.commit();
		sqlSession.close();
		
		return updCnt;
	}



}
