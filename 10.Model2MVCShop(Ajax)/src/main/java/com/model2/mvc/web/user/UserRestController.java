package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value = "json/addUser" , method = RequestMethod.GET)
	public void addUser() throws Exception{
		
		System.out.println("/user/addUser : GET");
	}
	
	@RequestMapping( value = "json/addUser" , method = RequestMethod.POST)
	public void addUser(@RequestBody User user) throws Exception{
		
		System.out.println("/user/addUser : POST");
		
		userService.addUser(user);
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser = userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		//나중에 쓰실거라고 함 세션.. client에선 없는걸..?
		
		
		return dbUser;
	}
	
	@RequestMapping( value = "json/updateUser/{userId}", method=RequestMethod.GET)
	public User updateUser( @PathVariable String userId) throws Exception{
		
		System.out.println("/user/json/updateUser");
		
		return userService.getUser(userId);
		
	}
	
	@RequestMapping( value = "json/updateUser", method = RequestMethod.POST)
	public User updateUser( @RequestBody User user , HttpSession session) throws Exception{
		
		System.out.println("/user/updateUser");
		
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		return user;
	}
	

	
	@RequestMapping( value = "json/checkDuplication/{userId}", method = RequestMethod.POST)
	public Map checkDuplicaition (@PathVariable String userId) throws Exception {
	
		boolean result = userService.checkDuplication(userId);
		Map map = new HashMap();
		map.put("result", new Boolean(result));
		map.put("userId", userId);
		
		
	return map;
	}
	
	@RequestMapping( value = "json/listUser", method = RequestMethod.POST)
	public Map listUserPost (@RequestBody Search search) throws Exception{
		
		System.out.println("/user/json/listUser");
		if(search.getCurrentPage() == 0 ) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = userService.getUserList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		
		map.put("list", map.get("list"));
		map.put("resultPage",resultPage);
		map.put("search", search);
		
		return map;
	}
	
	@RequestMapping( value = "json/listUser", method = RequestMethod.GET)
	public Map listUserGet (@ModelAttribute("search") Search search) throws Exception{
		
		System.out.println("/user/json/listUser");
		if(search.getCurrentPage() == 0 ) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = userService.getUserList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		
		map.put("list", map.get("list"));
		map.put("resultPage",resultPage);
		map.put("search", search);
		
		return map;
	}
	
	@RequestMapping ( value = "json/logout" ,method = RequestMethod.GET)
	public void logout(HttpSession session )throws Exception{
		
		System.out.println("/user/json/logout");
		
		session.invalidate();
	}
	
	
}