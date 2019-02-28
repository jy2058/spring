package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Console;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest extends LogicTestConfig {

	@Resource(name = "userService")
	IUserService userService;

	@Before
	public void setUp() {
		// userService = new UserServiceImpl();
		userService.deleteUser("test1");
	}

	// getAllUser 메소드를 테스트 하는 메소드 작성
	@Test
	public void testGetAllUser() {
		/*** Given ***/
		// IUserService userService = new UserServiceImpl();

		/*** When ***/
		List<UserVo> userList = userService.getAllUser();

		/*** Then ***/
		// assertEquals(5, userList.size());

	}

	@Test
	public void testSelectUserPagingList() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 10);

		/*** When ***/
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);

		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int userCnt = (int) resultMap.get("userCnt");

		for (UserVo user : userList) {
			System.out.println(user);
		}
		System.out.println(userCnt);

		/*** Then ***/

		// userList
		assertNotNull(userList);
		assertEquals(10, userList.size());

		// userCnt
		assertEquals(105, userCnt);

	}

	@Test
	public void testInsert() {
		/*** Given ***/
		Date date = new Date();
		UserVo userVo = new UserVo();
		userVo.setUserId("test");
		userVo.setUserNm("테스트");
		userVo.setAlias("별명");
		userVo.setAddr1("대전 중구 대흥로 76");
		userVo.setAddr2("2층 ddit");
		userVo.setZipcode("34942");
		userVo.setPass("testpas");

		/*** When ***/
		int result = userService.insertUser(userVo);

		/*** Then ***/
		assertEquals(1, result);

	}

	@Test
	public void testSelect() {
		UserVo userVo = userService.selectUser("kk");
		// assertNull(userService.selectUser("bro"));
		System.out.println(userVo.getUserNm());
		assertNotNull(userVo);
	}

	@Test
	public void testUpdUser() {
		/*** Given ***/
		UserVo userVo = new UserVo();
		userVo.setUserId("kk");
		userVo.setUserNm("테");
		userVo.setAlias("별명");
		userVo.setAddr1("대전 중구 대흥로 76");
		userVo.setAddr2("2층 ddit");
		userVo.setZipcode("34942");
		userVo.setPass("testpass");

		/*** When ***/
		int result = userService.updUser(userVo);

		/*** Then ***/
		assertEquals(1, result);

	}
}
