package kr.or.ddit.mvc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@ModelAttribute("rangerList")
	public List<String> getRangers(){
		return rangerService.getRangers();
	}
	
	// beanNameViewResolver
	// 1. viewName : jsonVIew
	// 2. ModelAndView("jsonView")
	
	@RequestMapping("/ajaxView")
	public String ajaxView(){
		return "mvc/ajaxView";
	}

	// 1. viewName 이용
	@RequestMapping("/jsonView")
	public String jsonView(){
		return "jsonView";
	}
	
	// 2. ModelAndView 이용 // 비추천
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView(){
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping(path="/responseBody", produces={"application/json"})
	@ResponseBody	// 응답처리 // ajax쓸 때 씀
	public List<String> responsBody(){
		return rangerService.getRangers();	
	}
										 // 요청받는 데이터 타입
	@RequestMapping(path="/requestBody", produces={"application/json"})	
	@ResponseBody
	//public List<String> requestBody(@RequestBody RangerVo rangerVo){
	public RangerVo requestBody(@RequestBody RangerVo rangerVo){
		logger.debug("rangerVo : {}", rangerVo);
		rangerVo.setUserId(rangerVo.getUserId()+"_server");
		rangerVo.setBirth(new Date());
		
		//return rangerService.getRangers();
		return rangerVo;
	}

}
