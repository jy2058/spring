package kr.or.ddit.prod.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class ProdControllerTest extends WebTestConfig{

	@Test
	public void testLprodAllList() throws Exception{
		
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprodList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals(mav.getViewName(), "prod/lprodAllList");

	}
	
	@Test
	public void testProd() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prod").param("lprod_gu", "P102")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals(mav.getViewName(), "prod/prod");

	}
	
	@Test
	public void testLprodPagingList() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprodPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		ModelMap modelMap = mav.getModelMap();
		
		List<ProdVo> lprodList = (List<ProdVo>) modelMap.get("lprodList");
		int lprodCnt = (int) modelMap.get("lprodCnt");
		int page = (int) modelMap.get("page");
		int pageSize = (int) mav.getModel().get("pageSize");
		
		/***Then***/
		assertEquals("prod/lprodPagingList", viewName);
		assertNotNull(lprodList);
		assertTrue(lprodCnt > 10);
		assertEquals(1,  page);
		assertEquals(10, pageSize);



	}

}
