package kr.or.ddit.ranger.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	@Resource(name="rangerService")
	private IRangerService rangerService;

	/**
	* Method : getRangers
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : 전체 레인저스 리스트를 조회
	*/
	
	// localhost/ranger/getRangers라고 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		List<String> rangers = rangerService.getRangers();

		//request.setAttribute("rangers", rangers); ==>
		model.addAttribute("rangers", rangers);
		
		return "ranger/rangerList";
	}
	
	
	// localhost/ranger/getRanger?listIndex=2 라고 요청시 밑의 메소드에서 요청을 처리
	// vo객체에 파라미터 명과 동일한 이름 필드가 존재하면 파라미터를 해당 필드에 바인딩 시켜준다.
	@RequestMapping("/getRanger")
	public String getRanger(RangerVo rangerVo, Model model) {
		/*
		// 내부적으로 이런 식으로 돌아 감
		RangerVo rangerVo = new RangerVo();
		rangerVo.setListIndex(request.getParameter(listIndex));
		getRanger(rangerVo, model);
		*/
		
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		
		model.addAttribute("ranger", ranger);
		
		return "ranger/ranger";
	}
	
	/*// localhost/ranger/getRanger?listIndex=2 라고 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRanger")
	public String getRanger(HttpServletRequest req, Model model) {
		//
		int listIndex = Integer.parseInt(req.getParameter("listIndex"));
		String ranger = rangerService.getRanger(listIndex);
		
		model.addAttribute("ranger", ranger);
		
		return "ranger/ranger";
	}*/
}
