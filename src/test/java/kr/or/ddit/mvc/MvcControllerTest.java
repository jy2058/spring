package kr.or.ddit.mvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.service.IUserService;

public class MvcControllerTest extends WebTestConfig {
	
	private static final String USER_INSERT_TEST_ID = "sallyTest";
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initData(){
		userService.deleteUser(USER_INSERT_TEST_ID);
	}

	/**
	* Method : testView
	* 작성자 : PC08
	* 변경이력 :
	* Method 설명 : 파일 업로드 테스트 뷰 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void testView() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/mvc/view")).andExpect(MockMvcResultMatchers.status().isOk())
										 .andExpect(view().name("mvc/view"));
		/***Then***/
	}
	
	/**
	* Method : testFileupload
	* 작성자 : PC08
	* 변경이력 :
	* Method 설명 : 파일 업로드 테스트
	 * @throws Exception 
	*/
	@Test
	public void testFileupload() throws Exception {
		/***Given***/
		
		File profileFile = new File("D:\\A_TeachingMaterial\\6.JspSpring\\공유자료\\레인저스사진\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png", fis);
		mockMvc.perform(fileUpload("/mvc/fileupload").file(file)
													 .param("userId", "brown"))
													 .andExpect(status().isOk())
													 .andExpect(view().name("mvc/view"));
		/***When***/

		/***Then***/
		
	}
	
	/**
	* Method : testUserForm_post
	* 작성자 : PC08
	* 변경이력 :
	* Method 설명 : 사용자 등록 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUserForm_post_success() throws Exception{
		/***Given***/
		
		File profileFile = new File("D:\\A_TeachingMaterial\\6.JspSpring\\공유자료\\레인저스사진\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
																		.param("userId", USER_INSERT_TEST_ID)
																		.param("userNm", "샐리테스트")
																		.param("alias", "병아리")
																		.param("addr1", "대전시 중구 대흥로 76")
																		.param("addr2", "2층 DDIT")
																		.param("zipcode", "34942")
																		.param("pass", "testpass"))
											.andExpect(view().name("redirect:/user/userPagingList"))
											.andReturn();
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();

		/***Then***/
		assertEquals("정상 등록 되었습니다.", session.getAttribute("msg"));

	}
	
	/**
	* Method : testUserForm_post_fail
	* 작성자 : PC08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 요청(중복 사용자로 인한 등록 실패 케이스) 테스트
	*/
	@Test
	public void testUserForm_post_fail() throws Exception{
		/***Given***/
		
		File profileFile = new File("D:\\A_TeachingMaterial\\6.JspSpring\\공유자료\\레인저스사진\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
																		.param("userId", "brown")
																		.param("userNm", "샐리테스트")
																		.param("alias", "병아리")
																		.param("addr1", "대전시 중구 대흥로 76")
																		.param("addr2", "2층 DDIT")
																		.param("zipcode", "34942")
																		.param("pass", "testpass"))
											.andExpect(view().name("user/userForm"))
											.andReturn();
		/***When***/
		ModelAndView mav =  mvcResult.getModelAndView();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("중복 체크에 실패 하였습니다.", msg);

	}
	
	/**
	* Method : testUserForm_post_fail_insertError
	* 작성자 : PC08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록(zipcode 사이즈 sql에러 ) 테스트
	*/
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		/***Given***/
		
		File profileFile = new File("D:\\A_TeachingMaterial\\6.JspSpring\\공유자료\\레인저스사진\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
																		.param("userId", USER_INSERT_TEST_ID)
																		.param("userNm", "샐리테스트")
																		.param("alias", "병아리")
																		.param("addr1", "대전시 중구 대흥로 76")
																		.param("addr2", "2층 DDIT")
																		.param("zipcode", "2154654684684354")
																		.param("pass", "testpass"))
											.andExpect(view().name("user/userForm"))
											.andReturn();
		/***When***/

		/***Then***/

	}
	
	/**
	* Method : testUserModify_Success
	* 작성자 : PC08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 수정 성공 테스트
	*/
	@Test
	public void testUserModify_Success() throws Exception{
		/***Given***/
		File profileFile = new File("D:\\A_TeachingMaterial\\6.JspSpring\\공유자료\\레인저스사진\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png", fis);
		mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
																		.param("userId", "sally")
																		.param("userNm", "샐리테스트")
																		.param("alias", "병아리")
																		.param("addr1", "대전시 중구 대흥로 76")
																		.param("addr2", "2층 DDIT")
																		.param("zipcode", "34942")
																		.param("pass", "testpass"))
											.andExpect(view().name("redirect:/user/user?userId=sally"));
		
		/***When***/

		/***Then***/

	}
	
	/**
	* Method : testUserModify_fail
	* 작성자 : PC08
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 수정 실패 테스트
	*/
	@Test
	public void testUserModify_fail()throws Exception{
		File profileFile = new File("D:\\A_TeachingMaterial\\6.JspSpring\\공유자료\\레인저스사진\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png", fis);
		mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
																		.param("userId", "sally")
																		.param("userNm", "샐리테스트")
																		.param("alias", "병아리")
																		.param("addr1", "대전시 중구 대흥로 76")
																		.param("addr2", "2층 DDIT")
																		.param("zipcode", "349423494234942")
																		.param("pass", "testpass"))
											.andExpect(view().name("user/userModifyForm"));
		
	}
}
