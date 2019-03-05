package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao{
	
	
	public List<LprodVo> getAllLprod(SqlSession sqlSession){

		List<LprodVo> lprodList = sqlSession.selectList("lprod.getAllLprod");
	
		
		return lprodList;
		
	}
	
	public LprodVo selectLprod(SqlSession sqlSession, String lprod_gu){

		LprodVo lprodVo = sqlSession.selectOne("lprod.selectLprod", lprod_gu);

		
		return lprodVo;
	}

	@Override
	public List<LprodVo> selectLprodPagingList(SqlSession sqlSession, PageVo pageVo) {

		List<LprodVo> lprodList = sqlSession.selectList("lprod.selectLprodPagingList", pageVo);

		
		return lprodList;
	}

	@Override
	public int getLprodCnt(SqlSession sqlSession) {

		int lprodCnt = sqlSession.selectOne("lprod.getLprodCnt");
	
		
		return lprodCnt;
	}
	

}
