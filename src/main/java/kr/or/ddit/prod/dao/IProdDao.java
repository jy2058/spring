package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IProdDao {
	List<ProdVo> getAllProd(SqlSession sqlSession);
	List<ProdVo> selectProd(SqlSession sqlSession, String lprod_gu);

}
