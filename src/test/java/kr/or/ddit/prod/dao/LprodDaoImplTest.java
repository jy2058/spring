package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodDaoImplTest extends LogicTestConfig{
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	private SqlSession sqlSession;
	
	@Before
	public void setUp(){
		//userDao = new UserDaoImpl();
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After
	public void tearDown(){
		sqlSession.close();
	}
	
	@Test
	public void testGetAllLprod(){
		/***Given***/
		
		/***When***/
		List<LprodVo> allLprod = lprodDao.getAllLprod(sqlSession);

		/***Then***/
		assertNotNull(allLprod);

	}
	
	@Test
	public void testSelectLprodPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		List<LprodVo> selectLprodPagingList = lprodDao.selectLprodPagingList(sqlSession, pageVo);

		/***Then***/
		assertNotNull(selectLprodPagingList);

	}

}
