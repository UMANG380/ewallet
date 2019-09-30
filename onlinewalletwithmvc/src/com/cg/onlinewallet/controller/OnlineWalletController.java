package com.cg.onlinewallet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;
import com.cg.onlinewallet.service.WalletUserService;

@Controller
public class OnlineWalletController {
	
	@Autowired
	private WalletUserService service;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String homePage() {
		return "home";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	@RequestMapping(value="/userPage",method=RequestMethod.POST)
	public String userPage(@RequestParam("userName") Integer userId,
			@RequestParam("userPassword") String password,
			@RequestParam("userType") String userType,HttpServletRequest req,HttpServletResponse res
			,Map<String,Object> model) {
		
		if(userType.equals("customer")) {
			if(service.validateLoginCredentials(userId, password)) {
				Cookie cookie = new Cookie(userId+"","loggedin");
				res.addCookie(cookie);
				WalletUser user;
				try {
					user = service.getUser(userId);
					HttpSession session = req.getSession();
					session.setAttribute("user",user);
					model.put("user",user);
					return "UserFunctionalitiesPage";
				} catch (MyException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				return "login";
			}
		}
		else{
			if(userId==1&&password.equals("venkatesh")) {
				Cookie cookie = new Cookie("status","loggedin");
				res.addCookie(cookie);
				model.put("name", "Venkatesh");
				return "AdminFunctionalitiesPage";
			}
		}
		return "login";
	}
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String userRegistrationPage(@ModelAttribute("registrationForm") WalletUser user) {
		return "registration";
	}
	@RequestMapping(value="/getRegistrationDetails",method = RequestMethod.POST)
	public String getRegistrationDetails(@ModelAttribute("registrationForm") WalletUser user
			,@RequestParam("confirmpassword") String confirmpassword) {
		
		System.out.println(user);
		if(confirmpassword.equals(user.getUserPassword())) {
			try {
				user.setAccount(new WalletAccount());;
				service.addWalletUser(user);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else {
			return "registration";
		}
		return "login";
	}
	@RequestMapping(value="/viewAccountsToBeApproved",method = RequestMethod.GET)
	public String viewAccountsToBeApproved(Map<String,Object> model) {
		List<WalletAccount> accounts = service.accountsToBeApproved();
		model.put("accounts", accounts);
		return "accountsToBeApprovedPage";
	}
	@RequestMapping(value="/approveAccount",method=RequestMethod.GET)
	public String approveAccountPage() {
		return "approveAccount";
	}
	@RequestMapping(value="/getApproveAccountNo",method=RequestMethod.POST)
	public String getApproveAccountNo(@RequestParam("accountNo") Integer accountNo) {
		try {
			service.approveAccount(accountNo);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return "AdminFunctionalitiesPage";
	}
	@RequestMapping(value="/signOut",method=RequestMethod.GET)
	public String signOut(HttpServletRequest req,HttpServletResponse res) {
		Cookie cookies[] = req.getCookies();
		for(Cookie c:cookies) {
			if(c.getName().equals("status")){
				System.out.println("out");
				System.out.println(c.getValue());
			}
		}
	    Cookie c = new Cookie("status","");
	    c.setMaxAge(0);
	    res.addCookie(c);
		return "home";
	}
	@RequestMapping(value="/addAmount",method=RequestMethod.GET)
	public String addAmount(HttpServletResponse res,HttpServletRequest request,Map<String,Object> model) {
		WalletUser user;
			HttpSession seesion = request.getSession();
			user = (WalletUser)seesion.getAttribute("user");
			System.out.println(user.getPhoneNo());
		    model.put("user",user);
		return "addAmount";
	}
	@RequestMapping(value="/getAmount",method=RequestMethod.POST)
	public String getAmount(@RequestParam("amount") Double amount,HttpServletRequest request
			,Map<String,Object> model) {
		WalletUser user;
		try {
			HttpSession seesion = request.getSession();
			user =(WalletUser)seesion.getAttribute("user");
			System.out.println(user.getPhoneNo());
			if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
				service.addAmount(user.getUserId(), amount);
				model.put("user",service.getUser(user.getUserId()));
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "UserFunctionalitiesPage";
	}
}
