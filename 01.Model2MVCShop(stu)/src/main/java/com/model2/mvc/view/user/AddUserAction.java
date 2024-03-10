package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class AddUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		UserVO userVO=new UserVO();//도메인객체만듬->셋팅
		userVO.setUserId(request.getParameter("userId"));
		userVO.setPassword(request.getParameter("password"));
		userVO.setUserName(request.getParameter("userName"));
		userVO.setSsn(request.getParameter("ssn"));
		
		userVO.setAddr(request.getParameter("addr"));
		userVO.setPhone(request.getParameter("phone").replaceAll("-", ""));
		userVO.setEmail(request.getParameter("email"));
		
		System.out.println(userVO);
		
		UserService service=new UserServiceImpl();
		service.addUser(userVO); //서비스객체만들고 도메인 넣어줌
		
		return "redirect:/user/loginView.jsp";
	}
}