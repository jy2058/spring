package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.dao.ILprodDao;
import kr.or.ddit.prod.dao.LprodDaoImpl;
import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;


@Service("lprodService")
public class LprodServiceImpl implements ILprodService{
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	public LprodServiceImpl() {
		//lprodDao = new LprodDaoImpl();
	}
	
	public List<LprodVo> getAllLprod(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		return lprodDao.getAllLprod(sqlSession);
	}
	
	public LprodVo selectLprod(String lprod_gu){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return lprodDao.selectLprod(sqlSession, lprod_gu);
	}

	@Override
	public Map<String, Object> selectLprodPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("lprodList", lprodDao.selectLprodPagingList(sqlSession, pageVo));
		resultMap.put("lprodCnt", lprodDao.getLprodCnt(sqlSession));
		
		return resultMap;
	}

}
