package com.cg.onlinewallet.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.excelview.TransactionsExcel;
import com.cg.onlinewallet.exception.MyException;
import com.cg.onlinewallet.service.WalletUserService;


//to do 2 bugs in user functionalities
//get transactions functionality
//validation
//excel sheet upload and download

@Controller
public class OnlineWalletController {
	
	@Autowired
	private WalletUserService service;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String homePage() {
		return "home";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(Map<String,Object> model) {
		model.put("InvalidLoginCredentials","");
		return "login";
	}
	
	@RequestMapping(value="/userPage",method=RequestMethod.POST)
	public String userPage(@RequestParam("userName") Integer userId,
			@RequestParam("userPassword") String password,
			@RequestParam("userType") String userType,HttpServletRequest req,HttpServletResponse res
			,Map<String,Object> model) {
		model.put("InvalidLoginCredentials","");
		if(userType.equals("customer")) {
			if(userId==null) {
				model.put("error", "id can not be null");
				return "login";
			}
			if(service.validateLoginCredentials(userId, password)) {
				Cookie cookie = new Cookie("status","loggedin");
				res.addCookie(cookie);
				WalletUser user;
				try {
					user = service.getUser(userId);
					HttpSession session = req.getSession();
					session.setAttribute("user",user);
					model.put("user",user);
					model.put("error", "");
					return "UserFunctionalitiesPage";
				} catch (MyException e) {
					// TODO Auto-generated catch block
					model.put("InvalidLoginCredentials","Invalid login credentials");
					System.out.println(e.getMessage());
				}
				return "login";
			}
			else {
				model.put("InvalidLoginCredentials","Invalid Login Credentials");
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
			else {
				model.put("InvalidLoginCredentials","Invalid login credentials");
			}
		}
		return "login";
	}
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String userRegistrationPage(@ModelAttribute("registrationForm") WalletUser user,Map<String,Object> model) {
		model.put("error", "");
		return "registration";
	}
	@RequestMapping(value="/getRegistrationDetails",method = RequestMethod.POST)
	public String getRegistrationDetails(@ModelAttribute("registrationForm") WalletUser user
			,@RequestParam("confirmpassword") String confirmpassword,Map<String,Object> model) {
		
		System.out.println(user);
		if(confirmpassword.equals(user.getUserPassword())) {
			try {
				user.setAccount(new WalletAccount());;
				service.addWalletUser(user);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				model.put("error", e.getMessage());
				return "registration";
			}
		}
		else {
			model.put("error", "passwords are not matching");
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
	public String approveAccountPage(Map<String,Object> model) {
		model.put("error", "");
		return "approveAccount";
	}
	@RequestMapping(value="/getApproveAccountNo",method=RequestMethod.POST)
	public String getApproveAccountNo(@RequestParam("accountNo") Integer accountNo,Map<String,Object> model) {
		try {
			service.approveAccount(accountNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.put("error", "Enter a Valid account No");
			return "approveAccount";
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
		    model.put("error","");
		return "addAmount";
	}
	@RequestMapping(value="/redirectAfterTransaction",method=RequestMethod.GET)
	public String redirectAfterTransaction(HttpServletRequest req,Map<String,Object> model) {
		HttpSession session = req.getSession();
		WalletUser user=(WalletUser)session.getAttribute("user");
		model.put("error", model.get("error"));
		try {
			req.setAttribute("user",service.getUser(user.getUserId()));
		} catch (MyException e) {
			// TODO Auto-generated catch block
		   
		}
		return "UserFunctionalitiesPage";
	}
	@RequestMapping(value="/getAmount",method=RequestMethod.POST)
	public String getAmount(@RequestParam("amount") Double amount,HttpServletRequest request
			,Map<String,Object> model) {
		HttpSession seesion = request.getSession();
		WalletUser user=(WalletUser)seesion.getAttribute("user");
		try {
			System.out.println(user.getPhoneNo());
			if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
				service.addAmount(user.getUserId(), amount);
				model.put("user",service.getUser(user.getUserId()));
			}
			else {
				model.put("error", "Your account is waiting for approval");
    			return "UserFunctionalitiesPage";
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			model.put("error", "Enter a valid amount");
			System.out.println("inside got it");
			 model.put("user",user);
			return "addAmount";
		}
		System.out.println("hii");
		return "redirect:/redirectAfterTransaction";
	}
	@RequestMapping(value="/transferAmount",method=RequestMethod.POST)
	public String transferAmount(HttpServletResponse res,HttpServletRequest request,Map<String,Object> model,
			@RequestParam("accountType") String accountType,@RequestParam("phoneNo") String phoneNo
			,@RequestParam("amount") Double amount) {
		WalletUser user;
		HttpSession seesion = request.getSession();
		user = (WalletUser)seesion.getAttribute("user");
		System.out.println(user.getPhoneNo());
	    System.out.println("good u entered");
	    if(accountType.equals("same")) {
	    	try {
	    		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
	    			service.transferAmount(user.getUserId(), phoneNo, amount);
	    		}
	    		else {
	    			model.put("error", "Your account is waiting for approval");
	    			return "UserFunctionalitiesPage";
	    		}
				
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.out.println("hii u r there");
				model.put("error", e.getMessage());
				return "UserFunctionalitiesPage";
			}
	    }
	    else {
	    	try {
	    		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
	    			service.transferAmount(user.getUserId(), Integer.parseInt(phoneNo), amount);
	    		}
	    		else {
	    			model.put("error", "Your account is waiting for approval");
	    			return "UserFunctionalitiesPage";
	    		}
				
			} catch (NumberFormatException | MyException e) {
				// TODO Auto-generated catch block
				model.put("error", e.getMessage());
			}
	    }
	    return "redirect:/redirectAfterTransaction";
	}
	@RequestMapping(value="/getTransactionsPage",method=RequestMethod.POST)
	public ModelAndView getTransactionsPage(@RequestParam("getExcel") boolean getExcel,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate")Date toDate,
	                        HttpServletRequest req,Map<String,Object> model){
		
		WalletUser user;
		HttpSession seesion = req.getSession();
		user = (WalletUser)seesion.getAttribute("user");
		model.put("user",user);
		System.out.println(user.getPhoneNo());
	    System.out.println("U are in transactions page");
		
		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")){
			try {
				user=service.getUser(user.getUserId());
			} catch (MyException e1) {
				// TODO Auto-generated catch block
				model.put("error", e1.getMessage());
			}
			LocalDate date1 = fromDate.toLocalDate();
			LocalDate date2 = toDate.toLocalDate();
			LocalDateTime fDate=LocalDateTime.of(date1, LocalTime.of(00, 00));
			LocalDateTime tDate=LocalDateTime.now();
			try {
				List<TransactionHistory> transactions =service.myTransactions(user.getUserId(), fDate, tDate);
			    if(getExcel) {
			    	System.out.println("excel downloading");
			    	return new ModelAndView(new TransactionsExcel(), "transactionList", transactions);
			    }
			    else {
			    	return new ModelAndView("showTransactions","transactions",transactions);
			    }
			} catch (MyException e) {
				// TODO Auto-generated catch block
				model.put("error", e.getMessage());
			}
		}
		else {
			model.put("error", "account waiting for approval");
		}
	    return null;
    }

}
