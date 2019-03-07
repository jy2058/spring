package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.exception.NoFileException;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.validator.userVoValidator;

@Controller
public class MvcController {
	private Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	
	/**
	* Method : view
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : part를 테스트 할 view 요청
	*/
	@RequestMapping("/mvc/view")
	public String view(){
		return "mvc/view";
	}
	
	/**
	* Method : fileupload
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : fileupload 처리 요청 테스트
	*/
	// 파라미터 : userId (text), profile (file)
	@RequestMapping("/mvc/fileupload")
	public String fileupload(@RequestParam("userId")String userId, @RequestPart("profile")MultipartFile multipartFile){
		
		
		logger.debug("userId : {}", userId );
		logger.debug("getOriginalFilename() : {}", multipartFile.getOriginalFilename());
		logger.debug("getName() : {}", multipartFile.getName());
		logger.debug("getSize() : {}", multipartFile.getSize());
		
		String filename = multipartFile.getOriginalFilename() + "_" + UUID.randomUUID().toString();
		
		// 불러온 파일 저장할 공간 생성
		File profile = new File("d:\\picture\\" + filename);
		try {
			multipartFile.transferTo(profile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "mvc/view";
	}
	
	@RequestMapping("/textView")
	public String textView(){
		
		return "mvc/textView";
	}
	
	// @RequestParam 어노테이션을 적용하지 않아도 인스턴스 명과 동일하면 바인딩
	// 파라미터 명과 인스턴스 명과 다를 경우 --> @RequestParam
	
	// BindingResult객체는 command객체(vo)에 바인딩 과정에서 발생한 결과를 담는 객체
	// 반드시 command 객체 메소드 인자 뒤에 위치 해야한다.
	// O : UserVo userVo, BindingResult result, Model model
	// X : UserVo userVo, Model model, BindingResult result
	@RequestMapping("/textReq")
	public String textReq(UserVo userVo, BindingResult result, Model model){
		new userVoValidator().validate(userVo, result);
		
		logger.debug("userId : {}", userVo.getUserId());
		logger.debug("pass : {}", userVo.getPass());
		
		if(result.hasErrors()){
			logger.debug("has Error");
			return "mvc/textView";
		}
		
		return "mvc/textView";
	}
	
	@RequestMapping("/textReqJsr303")
	public String textReqJsr303(@Valid UserVo userVo, BindingResult result){
		logger.debug("has errors(jsr303) : {} ", result.hasErrors());
		return "mvc/textView";
	}
	
	@RequestMapping("/textReqValJsr303")
	public String textReqValJsr303(@Valid UserVo userVo, BindingResult result){
		logger.debug("has errors(ValJsr303) : {} ", result.hasErrors());
		return "mvc/textView";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new userVoValidator());
	}
	
	/**
	* Method : throwArithmeticException
	* 작성자 : PC08
	* 변경이력 :
	* @return
	* Method 설명 : ArithmeticException 강제 발생
	*/
	@RequestMapping("/throwArith")
	public String throwArithmeticException(){
		if(1==1){
			throw new ArithmeticException();
		}
		return "mvc/textView";
	}
	
	// spring에서 제공하는 예외 처리는 잘 안 씀. jsp에서 했던 xml에서 등록해서 썼던 방식을 씀
	// controller마다 해야되기 떄문에 @ControllerAdvice 어노테이션을 이용해서 사용한다. (CommonExceptionHandler.java)에 만듦
	@ExceptionHandler({ArithmeticException.class})
	public String handleException(){
		logger.debug("arithmeticException");
		return "error/error";
	}
	
	@RequestMapping("/throwNoFileException")
	public String throwNoFileException() throws NoFileException{
		Resource resource = new ClassPathResource("kr/or/ddit/cofig/spring/no-exsits.xml");
		
		try {
			resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoFileException(); 	// 우리가 만든 예외 추가
		}
		return "mvc/textView";
	}
	
	@RequestMapping("/jsonResponse")
	public String jsonResponse(Model model){
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("cony");
		list.add("sally");
		list.add("james");
		list.add("moon");
		
		model.addAttribute("list", list);
		
		return "jsonView";
		// 우선순위에 따라 BeanNameViewResolver 를 먼저 요청. bean에 jsonView가 있기 때문에 InternalResourceViewResolver를 가지 않는다.
		// BeanNameViewResolver가 웹브라우저에 list 띄어 줌
	}
	
	@RequestMapping("/jsonResponseViewObj")
	public View jsonResponseViewObj(Model model){
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("cony");
		list.add("sally");
		list.add("james");
		list.add("moon");
		
		model.addAttribute("list", list);
		
		return new MappingJackson2JsonView();
		// 클래스를 리턴해서 위와 같은 효과
		// 하지만 요청 할 때마다 생성하기 때문에 비 효율적
	}
	
	@RequestMapping("/profileImgView")
	public String profileImgView(@RequestParam(name="userId", defaultValue="brown")String userId, Model model){
		model.addAttribute("userId", userId);
		
		return "profileImgView";
	}
	
	@RequestMapping("/helloTiles")
	public String helloTiles(){
		// 리턴 순서
		// 1. BeanNameViewResolver
		// 	helloTiles() 에서 리턴하는 문자열에 해당하는 bean id를 갖는 스프링 빈이 있는지 확인
		// 		있으면 -> 해당 스프링 객체를 사용하여 응답이 전달
		//		없으면 -> 다음 view Resolver에서 처리
		
		// 2. TilesViewResolver
		// 	helloTiles() 에서 리턴하는 문자열이 tilesConfigure에 설정 한 tiles 설정파일의 definition 이름(name)과 동일 한 선언이 있는지 확인
		// 		있으면 -> 해당 tiles 설정대로(layout extends) 응답 생성
		//		없으면 -> 다음 view Resolver에서 처리
		
		return "helloTiles";
	}
}
