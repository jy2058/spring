package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig{

	/**
	* Method : testUserAllList
	* 작성자 : PC08
	* 변경이력 :
	* Method 설명 : 사용자 전체 조최 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModel().get("userList");
		
		/***Then***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() > 100);

	}
	
	/**
	* Method : testUserPagingList
	* 작성자 : PC08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 유저 페이징 리스트 조회
	*/
	@Test
	public void testUserPagingList() throws Exception{
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		ModelMap modelMap = mav.getModelMap();
		
		List<UserVo> userList = (List<UserVo>) modelMap.get("userList");
		int userCnt = (int) modelMap.get("userCnt");
		int page = (int) modelMap.get("page");
		int pageSize = (int) mav.getModel().get("pageSize");
		
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertNotNull(userList);
		assertEquals(10, userList.size());
		assertTrue(userCnt > 100);
		assertEquals(1,  page);
		assertEquals(10, pageSize);

	}
	
	/**
	* Method : testUser
	* 작성자 : PC08
	* 변경이력 :
	* Method 설명 : 사용자 상세 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUser() throws Exception{
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModel().get("userVo");
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertNotNull(userVo);
		assertEquals("brown", userVo.getUserId());
		assertEquals("브라운", userVo.getUserNm());
		
	}
	
	//@Test
	//public void testProfileImg(){}
	
	/**
	* Method : testUserForm
	* 작성자 : PC08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 폼 요청
	*/
	@Test
	public void testUserForm() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userForm", viewName);
	}
	
	@Test
	public void testUserMofifyForm() throws Exception{
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userModifyForm").param("userId", "brown")).andReturn();
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("user/userModify", mav.getViewName());

	}
	
	
}
