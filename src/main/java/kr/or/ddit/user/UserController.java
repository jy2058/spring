package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

/**
 * UserController.java
 *
 * @author PC08
 * @version 1.0
 * @see
 *
 *      <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC08 최초 생성
 *
 *      </pre>
 */
@RequestMapping("/user")
@Controller
public class UserController {

	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * Method : userAllList 작성자 : PC08 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 전체 리스트 조회
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model) {

		List<UserVo> userList = userService.getAllUser();
		model.addAttribute("userList", userList);

		//return "user/userAllList";
		return "userAllListTiles";
	}

	/**
	 * Method : userPagingList 작성자 : PC08 변경이력 :
	 * 
	 * @param pageVo
	 * @param model
	 * @return Method 설명 : 사용자 페이징 리스트 조회
	 */
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVo pageVo, Model model) {

		// PageVo pageVo = new PageVo(page, pageSize);

		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);

		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());

		//return "user/userPagingList";
		return "userPagingListTiles";
	}

	/**
	 * Method : user 작성자 : PC08 변경이력 :
	 * 
	 * @param userId
	 * @param model
	 * @return Method 설명 : 사용자 상세 조회
	 */
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public String user(@RequestParam("userId") String userId, Model model) {
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);

		//return "user/user";
		return "userTiles";
	}

	/**
	 * Method : profileImg 작성자 : PC08 변경이력 :
	 * 
	 * @param req
	 * @param resp
	 * @param userId
	 * @throws IOException
	 *             Method 설명 : 사용자 사진
	 */
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletRequest req, HttpServletResponse resp, @RequestParam("userId") String userId)
			throws IOException {

		resp.setHeader("Content-Disposition", "attachment; filename=profile.png");
		resp.setContentType("image.png");

		// 1. 사용자 아이디 파라미터 확인
		// String userId = request.getParameter("userId");

		// 2. 해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVo userVo = userService.selectUser(userId);

		// 3-1. realFilename 존재 할 경우
		// 3-1-1. 해당 경로의 파일을 FileInputStream으로 읽는다.
		FileInputStream fis;
		if (userVo != null && userVo.getRealFilename() != null) {
			fis = new FileInputStream(new File(userVo.getRealFilename()));
		}
		// 3-2. realFilename 존재하지 않을 경우
		// 3-2-1. /upload/noimg.png(application.getRealPath())
		else {
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		// 4. FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = resp.getOutputStream();

		byte[] buff = new byte[512];
		int len = 0;
		while ((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}

		sos.close();
		fis.close();
	}

	/**
	 * Method : userForm 작성자 : PC08 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 등록 폼 요청
	 */
	@RequestMapping("/userForm")
	public String userForm() {
		return "user/userForm";
	}

	@RequestMapping(path = "/userForm", method = RequestMethod.POST)
	public String userForm(UserVo userVo, @RequestPart("profile") MultipartFile profile, HttpSession session,
			Model model) throws IllegalStateException, IOException {

		// 1. 사용자 아이디 중복체크
		UserVo duplicateUserVo = userService.selectUser(userVo.getUserId());

		// 중복체크 통과(신규등록)
		if (duplicateUserVo == null) {
			String filename = "";
			String realFilename = "";
			
			// 사용자 프로파일을 업로드 한 경우
			if (profile.getSize() > 0) {
				filename = profile.getOriginalFilename();
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();

				profile.transferTo(new File(realFilename));
			}
			
			userVo.setFilename(filename);
			userVo.setRealFilename(realFilename);
			
			// 사용자 비밀번호 암호화 로직
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			
			int insertCnt = 0;
			try {
				insertCnt = userService.insertUser(userVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 정상입력(성공)
			if (insertCnt == 1) {
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:/user/userPagingList"; // contextPath 작업 필요
				// redirect할 때 앞에 redirect 붙여준다.
			} else {
				return "user/userForm"; // 검증 필요
				// doGet 호출 할 때 앞에 forward 붙여준다.
			}

		}
		// 중복체크 실패
		else {
			model.addAttribute("msg", "중복 체크에 실패 하였습니다.");
			return "user/userForm";
		}
	}
	
	/**
	* Method : userModify
	* 작성자 : PC08
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 페이지(Get)
	*/
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId")String userId, Model model){
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "user/userModify";
	}
	
	/**
	* Method : userModify
	* 작성자 : PC08
	* 변경이력 :
	* @param userVo
	* @param profile
	* @return
	* @throws IllegalStateException
	* @throws IOException
	* Method 설명 : 사용자 수정 페이지(post)
	*/
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModify(UserVo userVo, @RequestPart("profile") MultipartFile profile, Model model, RedirectAttributes ra, HttpServletRequest req) throws IllegalStateException, IOException{
		
		//사진 수정 한 경우
		if(profile.getSize() > 0){
			
			String filename = profile.getOriginalFilename();
			String realFilename = "d:\\picture\\" + UUID.randomUUID().toString();

			profile.transferTo(new File(realFilename));
			
			userVo.setFilename(filename);
			userVo.setRealFilename(realFilename);
		}
		
		// 비밀번호 수정 요청 여부
		// 사용자가 값을 입력 하지 않은 경우 => 기존 비밀번호 유지
		if(userVo.getPass().equals("")){
			UserVo userVoForPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoForPass.getPass());
		}
		// 사용자가 비밀번호를 신규 등록 한 경우
		else{
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		}
		
		int updCnt = 0;
		try {
			updCnt = userService.updUser(userVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 정상적으로 수정이 된 경우  : 해당 사용자의 상세 페이지로 이동
		if(updCnt == 1){
			
			// redirect 파라미터를 보내는 방법
			// 1. url로 작성
			// 	"redirect:/user/user?userId=" + userVo.getUserId();
			
			// 2. model객체의 속성 : 저장된 속성을 자동으로 파라미터  변환
			//	model.addAttribute("userId", userVo.getUserId());
			//	return "redirect:/user/user";
			
			// 3. RedirectAttributes(ra) 객체를 이용
			//	ra.addAttribute("userId", userVo.getUserId());
			//	return "redirect:/user/user";
	
			// 2번 방법
			//model.addAttribute("userId", userVo.getUserId());
			//return "redirect:/user/user";
			
			// 3번 방법
			ra.addAttribute("userId", userVo.getUserId());
			ra.addFlashAttribute("msg", "정상 등록 되었습니다.");
			
			return "redirect:" + req.getContextPath() +"/user/user";
			
			// 1번 방법
			//return "redirect:/user/user?userId=" + userVo.getUserId();
		}
		
		// 정상적으로 수정이 안 된 경우 : 해당 사용자의 정보 수정 페이지로 이동
		else{
			return "user/userModifyForm";
		}
	}
}
