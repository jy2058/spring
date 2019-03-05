package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodService {
	
	List<LprodVo> getAllLprod();
	LprodVo selectLprod(String lprod_gu);
	
	Map<String, Object> selectLprodPagingList(PageVo pageVo);

}
