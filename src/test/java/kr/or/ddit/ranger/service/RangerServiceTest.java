package kr.or.ddit.ranger.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
/*
@RunWith(SpringJUnit4ClassRunner.class)
//servlet-context.xml, application-context.xml
@ContextConfiguration({ "classpath:kr/or/ddit/config/spring/application-context.xml" , "classpath:kr/or/ddit/config/spring/servlet-context.xml"})
*/
public class RangerServiceTest extends LogicTestConfig{

	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;
		
		/***When***/
		String ranger = rangerService.getRanger(index);

		/***Then***/
		assertEquals("brown", ranger);
	}
	
	@Test
	public void testGetRanger_overflowIndex() {
		/***Given***/
		int index = 5;
		
		/***When***/
		String ranger = rangerService.getRanger(index);

		/***Then***/
		assertEquals("james", ranger);
	}
	
	@Test
	public void testGetRanger() {
		/***Given***/
		int index = 2;
		
		/***When***/
		String ranger = rangerService.getRanger(index);

		/***Then***/
		assertEquals("sally", ranger);
	}

}
