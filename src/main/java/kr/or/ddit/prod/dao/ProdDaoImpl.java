package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.ProdVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository("prodDao")
public class ProdDaoImpl implements IProdDao{
	
	public List<ProdVo> getAllProd(SqlSession sqlSession){

		List<ProdVo> prodList = sqlSession.selectList("prod.getAllProd");
		
		return prodList;
		
	}
	
	public List<ProdVo> selectProd(SqlSession sqlSession, String lprod_gu){

		List<ProdVo> prodVo = sqlSession.selectList("prod.selectProd", lprod_gu);
		
		return prodVo;
	}

}
