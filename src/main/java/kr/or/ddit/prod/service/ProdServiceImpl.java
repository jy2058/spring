package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.model.ProdVo;

@Service("prodService")
public class ProdServiceImpl implements IProdService{
	
	@Resource(name="prodDao")
	private IProdDao prodDao;
	
	public ProdServiceImpl() {
		//prodDao = new ProdDaoImpl();
	}
	
	public List<ProdVo> getAllProd(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return prodDao.getAllProd(sqlSession);
	}
	
	public List<ProdVo> selectProd(String lprod_gu){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return prodDao.selectProd(sqlSession, lprod_gu);
	}

}
