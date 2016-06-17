package net.musicrecommend.www.mobile;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.musicrecommend.www.user.UserService;
import net.musicrecommend.www.user.UserVO;

@Controller
@RequestMapping("m")
public class MobileController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="user/login",method = RequestMethod.POST,produces="Application/json")
	@ResponseBody
	public String mLogin(UserVO userVO,HttpResponse response) throws Exception{
		
		UserVO checkedUserVO = userService.checkUser(userVO);
		if(checkedUserVO!=null) return "success";
		else return "fail";
	}
}
