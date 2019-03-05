package kr.or.ddit.prod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.ILprodService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.util.model.PageVo;

@Controller
public class ProdController {
	
	@Resource(name="prodService")
	private IProdService prodService;
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	@RequestMapping("/lprodList")
	public String lprodAllList(Model model){
		List<LprodVo> lprodList = lprodService.getAllLprod();
		
		model.addAttribute("lprodList", lprodList);
		
		return "prod/lprodAllList";
	}
	
	@RequestMapping("/prod")
	public String prod(@RequestParam("lprod_gu")String lprod_gu, Model model){
		List<ProdVo> prodVo = prodService.selectProd(lprod_gu);
		model.addAttribute("prodVo", prodVo);
		
		return "prod/prod";
	}
	
	@RequestMapping("/lprodPagingList")
	public String lprodPagingList(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "prod/lprodPagingList";
	}
	
}

