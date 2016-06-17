package net.musicrecommend.www.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.musicrecommend.www.recommend.RecommendService;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.FriendVO;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Value("${upload_path}")
	private String uploadUrl;

	@Inject
	UserService userService;
	
	@Autowired
	RecommendService recommendService;

	private void createUploadDir() {
		File dir = new File(uploadUrl);
		if (!dir.exists()) {// 존재 하지 않으면
			dir.mkdirs(); // 폴더를 만들어 준다.

		}
	}

	private String getUniqueFileName(String fileName) {
		String uniqueFileName = null;
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

		int fileIndex = fileName.lastIndexOf(".");

		if (fileIndex != -1) {
			String extension = fileName.substring(fileIndex + 1);
			uniqueFileName = uuid + "." + extension;
		} else {
			logger.info("getUniqueFileName() Error!");
		}

		return uniqueFileName;

	}

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String userJoin() {
		return "redirect:login";
	}

	@RequestMapping(value="intro",method = RequestMethod.GET)
	public void intro(){
	}
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public void userJoinAction(@ModelAttribute UserVO userVO) throws IllegalStateException, IOException {
		System.out.println("※"+userVO.toString());
		if(userVO.getUser_img_file() != null){
			CommonsMultipartFile cmf = userVO.getUser_img_file();

			String originalFilename = cmf.getOriginalFilename();
			String contentType = cmf.getContentType();

			boolean isImage = contentType.substring(0, 6).equals("image/");
				
			String ext = "." + contentType.substring(6, contentType.length());

			String uniqueFileName = getUniqueFileName(originalFilename);
			File file = new File(uploadUrl, uniqueFileName);// 원하는 경로로 이동하기 위해

			userVO.setUser_uuid(uniqueFileName);
			try {
				cmf.transferTo(file);
			} catch (FileNotFoundException e1) {
				// 폴더가 없을경우 파일낫파운드 익셉션
				createUploadDir(); // 없을경우 폴더를 만들어준다.
				cmf.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
				// 대부분의 에러는 권한 ,하드 용량, 파일이 없거나
			}
		} 

		try {
			userService.insertUser(userVO);
			logger.info("회원가입 정보 확인" + userVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("list")
	public String userList(Model model, @PathVariable int pg) {
		int total_article_count = -1;
		List<UserVO> userList = null;
		try {
			total_article_count = userService.getUserTotalCount();
			PageNation pageNation = new PageNation(pg, total_article_count);

			userList = userService.selectUserList(pageNation);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("userList", userList);

		return "user/list";
	}

	// 아이디 중복체크 확인\\
	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	@ResponseBody
	public int id_check(@ModelAttribute UserVO userVO, Model model) throws Exception {

		int check_id = userService.idCheck(userVO.getUser_id());

		logger.info("아이디 수신 체크 : " + userVO);
		logger.info("중복 아이디 갯수 확인 :" + check_id);
		return check_id;

	}
	
	@RequestMapping(value="add_follow", method = RequestMethod.POST,  produces = "Application/text; charset=UTF-8")
	@ResponseBody
	public String addFollow(@RequestParam String target_user_id, HttpSession session){
		String login_user_id = session.getAttribute("user_id").toString();
		String msg = "";
		if(login_user_id.equals(target_user_id)){
			msg = "자신은 팔로우 할 수 없습니다.";
		} else {
			try {
				long user_no = userService.getUserNo(login_user_id);
				long friend_user_no = userService.getUserNo(target_user_id);
				System.out.println("★ " + user_no+ ", "+ friend_user_no);
				Map<String,Long> mapData = new HashMap<String,Long>();
				mapData.put("user_no", user_no);
				mapData.put("friend_user_no", friend_user_no);
				msg = userService.addFollow(mapData);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "SYSTEM ERROR!";
			}
		}
	
		
		return msg;
	}

	// 로그인\\
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView cacaoLogin(@ModelAttribute UserVO userVO, Model mode, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		//session.invalidate();
		if (userVO.getUser_id() != null) {

			UserVO cacaoUser = new UserVO();

			cacaoUser.setUser_id(userVO.getUser_id());
			cacaoUser.setUser_nick(userVO.getUser_nick());
			cacaoUser.setUser_img(userVO.getUser_img());

			logger.info("카카오 계정세팅" + cacaoUser.toString());
			
			int result = userService.checkCacaoUser(cacaoUser);
			logger.info("카카오계정 중복 갯수 : " + result);
			if (result == 0) {
				userService.insertCacaoUser(userVO);
				logger.info("카카오계정 추가완료");

			} else if (result == 1) {
				logger.info("카카오계정 이미 존재");
				session.setAttribute("user_id", cacaoUser.getUser_id());
			}

			mav.setViewName("/result");
			mav.addObject("msg", "접속을 환영합니다.");
			mav.addObject("user_id", cacaoUser.getUser_id() + "님!");
			
			long user_no = userService.getUserNo(cacaoUser.getUser_id());
			long recommend_count = recommendService.getRecommendCount(user_no);
			
			if(recommend_count > 15){
				mav.addObject("url", "/www/main");
			} else {
				mav.addObject("url", "/www/starPoint/main/");
			}
			
			mav.addObject("user_nick", cacaoUser.getUser_nick());
			mav.addObject("user_img", cacaoUser.getUser_img());

		}

		return mav;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView userLoginAction(Model mode, HttpSession session, HttpServletRequest request) throws Exception {

		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");

		session.setAttribute("user_id", id);
		session.setMaxInactiveInterval(60*60*24*31);

		logger.info("현재 로그인 계정 : " + session.getAttribute("user_id"));

		ModelAndView mav = new ModelAndView();

		UserVO user = new UserVO();

		user.setUser_id(id);
		user.setUser_pw(pw);

		logger.info("로그인 정보 :" + user.toString());

		user = userService.checkUser(user);

		logger.info("로그인 정보 일치여부 :" + user);

		if (user == null) {
			mav.setViewName("/result");
			mav.addObject("msg", "로그인 정보가 일치하지 않습니다.");
			mav.addObject("user_id", "");
			mav.addObject("url", "/www/user/login");
		} else if (user != null) {

			mav.setViewName("/result");
			mav.addObject("msg", "환영합니다.");
			mav.addObject("user_id", id + "님!");
			
			long user_no = userService.getUserNo(id);
			long recommend_count = recommendService.getRecommendCount(user_no);
			
			if(recommend_count > 15){
				mav.addObject("url", "/www/main");
			} else {
				mav.addObject("url", "/www/starPoint/main/");
			}
			

			session.setAttribute("user_nick", user.getUser_nick());
			session.setAttribute("user_img", user.getUser_img());

		}

		return mav;

	}

	@RequestMapping(value="get_follower_list", method=RequestMethod.POST, produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> getFollowerList(@RequestParam String user_id){
		
		List<Map<String,Object>> mapData = new ArrayList<Map<String,Object>>();
		
		try {
			long user_no = userService.getUserNo(user_id);
			mapData = userService.getFollowerList(user_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	
	@RequestMapping(value="get_following_list", method=RequestMethod.POST, produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> getFollowingList(@RequestParam String user_id){
		
		List<Map<String,Object>> mapData = new ArrayList<Map<String,Object>>();
		
		try {
			long user_no = userService.getUserNo(user_id);
			mapData = userService.getFollowingList(user_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	
	@RequestMapping(value="unfollow", method=RequestMethod.POST, produces="Application/text; charset=UTF-8")
	@ResponseBody
	public String unFollow(@RequestParam String target_user_id, HttpSession session){
		
		String msg ="";
		try {
			
			long user_no = userService.getUserNo(session.getAttribute("user_id").toString());
			long friend_user_no =  userService.getUserNo(target_user_id);
			Map<String,Object> mapData = new HashMap<String,Object>();
			mapData.put("user_no", user_no);
			mapData.put("friend_user_no", friend_user_no);
			
			msg = userService.unFollow(mapData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET, produces="Application/json; charset=UTF-8")
	public String logout(HttpSession session){
		
		if(session.getAttribute("user_id") != null){
			session.invalidate();
		} 
		
		return "user/login";
		
	}
	
	@RequestMapping(value="searchfollow", method=RequestMethod.POST, produces="Application/json; charset=UTF-8")
	@ResponseBody
	public List<UserVO> searchFollow(@RequestParam String search_keyword){
		List<UserVO> userVO = null;
		try {
			userVO = userService.getSearchFollowList(search_keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}
}
