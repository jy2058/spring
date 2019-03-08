package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public class UserDaoImplTest extends LogicTestConfig{
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	//@Before - @Test - @After
	
	
	@Before
	public void setUp(){
		
		userDao.deleteUser("test1");
	}
	
	@After
	public void tearDown(){
	}
	
	// getAllUser 메소드를 테스트 하는 메소드 작성
	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVo> userList = userDao.getAllUser();
		UserVo user = userDao.selectUser("brown");

		/***Then***/
//		assertEquals(5, userList.size());
		assertEquals("brown1234", user.getPass());
		

	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/

		
		PageVo pageVo = new PageVo(1, 10);
		
		
		/***When***/
		List<UserVo> userList = userDao.selectUserPagingList(pageVo);

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());

	}
	
	@Test
	public void testGetUserCnt(){
		/***Given***/
		
		/***When***/
		int userCnt = userDao.getUserCnt();

		/***Then***/
		assertNotNull(userCnt);
		assertEquals(105, userCnt);

	}
	
	@Test
	public void testPagination(){
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
		
		/***When***/
		int lastPage = userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0);
		

		/***Then***/
		assertEquals(11, lastPage);

	}
	
	@Test
	public void testPagination2(){
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
		
		/***When***/
		int lastPage = userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0);
		

		/***Then***/
		assertEquals(11, lastPage);

	}
	
	@Test
	public void testInsert(){
		/***Given***/
		Date date = new Date();
		
	    UserVo userVo = new UserVo();
		userVo.setUserId("test1");
	    userVo.setUserNm("테스트");
	    userVo.setAlias("별명");
	    userVo.setAddr1("대전 중구 대흥로 76");
	    userVo.setAddr2("2층 ddit");
	    userVo.setZipcode("34942");
	    userVo.setPass("testpass");
	    
		/***When***/
	    int result = userDao.insertUser(userVo);

		/***Then***/
	    assertEquals(1, result);

	}
	@Test
	public void testUpdUser(){
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserId("kk");
	    userVo.setUserNm("테");
	    userVo.setAlias("별명");
	    userVo.setAddr1("대전 중구 대흥로 76");
	    userVo.setAddr2("2층 ddit");
	    userVo.setZipcode("34942");
	    userVo.setPass("testpass");

		/***When***/
	    int result = userDao.updUser(userVo);

		/***Then***/
	    assertEquals(1, result);

	}
}
