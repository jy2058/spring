package kr.or.ddit.prod.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodServiceImplTest extends LogicTestConfig{
	
	@Resource(name="lprodService")
	private ILprodService lprodService;

	@Test
	public void testGetAllLprod(){
		/***Given***/
		
		/***When***/
		List<LprodVo> allLprod = lprodService.getAllLprod();

		/***Then***/
		assertNotNull(lprodService);

	}
	
	@Test
	public void testSelectLprodPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		/***When***/
		Map<String, Object> selectLprodPagingList = lprodService.selectLprodPagingList(pageVo);
		/***Then***/
		assertNotNull(selectLprodPagingList);

	}

}
