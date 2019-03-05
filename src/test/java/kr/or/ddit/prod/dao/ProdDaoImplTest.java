package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;

public class ProdDaoImplTest extends LogicTestConfig{
	@Resource(name="prodDao")
	private IProdDao prodDao;
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
	public void testGetAllProd(){
		/***Given***/
		
		/***When***/
		List<ProdVo> prodList = prodDao.getAllProd(sqlSession);
		
		/***Then***/
		assertNotNull(prodList);
	}
	
	@Test
	public void testSelectProd(){
		/***Given***/
		String lprod_gu ="P101";
		
		/***When***/
		List<ProdVo> selectProd = prodDao.selectProd(sqlSession, lprod_gu);

		/***Then***/
		assertNotNull(selectProd);
	}

}
