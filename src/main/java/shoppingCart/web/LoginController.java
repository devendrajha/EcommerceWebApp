package shoppingCart.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoppingCart.dao.LoginDao;
import shoppingCart.model.LoginBean;


@RestController
@RequestMapping(value = "/dologin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	
	Map<Integer, LoginBean> LoginDetails = new HashMap<Integer, LoginBean>();

	public void init() {
		loginDao = new LoginDao();
	}
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public @ResponseBody LoginBean getLoginDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.sendRedirect("login/login.jsp");
			LoginBean loginBean = new LoginBean();
			return loginBean;	
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody LoginBean createUser(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginBean loginBean) throws ServletException, IOException {
		
		
		return loginBean;
	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.PUT)
	public @ResponseBody LoginBean updateUser(@PathVariable("id") int LoginId, HttpServletRequest request, HttpServletResponse response, @RequestBody LoginBean loginBean) throws ServletException, IOException {
		LoginBean loginBean1 = LoginDetails.get(LoginId);
		LoginDetails.remove(LoginId);
		return loginBean1;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	public @ResponseBody LoginBean deleteUser(@PathVariable("id") int LoginId, HttpServletRequest request, HttpServletResponse response, @RequestBody LoginBean loginBean) throws ServletException, IOException {
		LoginBean loginBean1 = LoginDetails.get(LoginId);
		LoginDetails.remove(LoginId);
		return loginBean1;
	}
	
	
}
