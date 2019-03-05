package kr.or.ddit.prod.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;

public class ProdServiceImplTest extends LogicTestConfig {
	
	@Resource(name="prodService")
	private IProdService prodService;

	@Test
	public void testSelectProd(){
		/***Given***/
		String lprod_gu="P101";
		
		/***When***/
		List<ProdVo> selectProd = prodService.selectProd(lprod_gu);

		/***Then***/
		assertNotNull(selectProd);

	}
}
