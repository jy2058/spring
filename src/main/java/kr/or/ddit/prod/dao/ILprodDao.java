package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodDao {
	List<LprodVo> getAllLprod(SqlSession sqlSession);
	LprodVo selectLprod(SqlSession sqlSession, String lprod_gu);
	List<LprodVo> selectLprodPagingList(SqlSession sqlSession, PageVo pageVo);
	int getLprodCnt(SqlSession sqlSession);
}
