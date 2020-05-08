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

import shoppingCart.dao.LoginDao;
import shoppingCart.model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	
	Map<Integer, LoginBean> LoginDetails = new HashMap<Integer, LoginBean>();

	public void init() {
		loginDao = new LoginDao();
	}
	@RequestMapping(value = "/getLoginDetails", method = RequestMethod.GET)
	public @ResponseBody LoginBean getLoginDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.sendRedirect("login/login.jsp");
			LoginBean loginBean = new LoginBean();
			return loginBean;	
	}
	
	@RequestMapping(value = "/postLoginDetails", method = RequestMethod.POST)
	public @ResponseBody LoginBean createLoginDetails(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginBean loginBean) throws ServletException, IOException {
		authenticate(request, response);
		return loginBean;
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
				dispatcher.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				// session.setAttribute("user", username);
				// response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/postLoginDetails", method = RequestMethod.PUT)
	public @ResponseBody LoginBean updateLoginDetails(@PathVariable("id") int LoginId, HttpServletRequest request, HttpServletResponse response, @RequestBody LoginBean loginBean) throws ServletException, IOException {
		LoginBean loginBean1 = LoginDetails.get(LoginId);
		LoginDetails.remove(LoginId);
		return loginBean1;
	}
	
	
}
