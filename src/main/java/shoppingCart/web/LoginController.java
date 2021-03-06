package shoppingCart.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shoppingCart.dao.LoginDao;
import shoppingCart.model.LoginBean;
import shoppingCart.model.User;

@WebServlet("/login")

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	Map<Integer, LoginBean> LoginDetails = new HashMap<Integer, LoginBean>();

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		

		// response.sendRedirect("login/login.jsp");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(name);
		loginBean.setPassword(pwd);
		LoginDao dao = new LoginDao();
		boolean str = false;
		try {
			str = dao.validate(loginBean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (request.getParameter("username")==null && request.getParameter("password") ==null) {
			out.println("Please Enter UserName and Password as a query String ");
		}else
		out.println(str);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer jb = new StringBuffer();
		String line = null;
		Gson gson = new Gson();
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */ 
		}
		
		User u=gson.fromJson(jb.toString(), User.class);
		String res=null;
		try {
			res=loginDao.createUser(u);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(res);

	}
	/*
	 * @RequestMapping(value = "/signup", method = RequestMethod.POST)
	 * public @ResponseBody LoginBean createUser(HttpServletRequest request,
	 * HttpServletResponse response, @RequestBody LoginBean loginBean) throws
	 * ServletException, IOException {
	 * 
	 * 
	 * return loginBean; }
	 * 
	 * @RequestMapping(value = "/updatepassword", method = RequestMethod.PUT)
	 * public @ResponseBody LoginBean updateUser(@PathVariable("id") int LoginId,
	 * HttpServletRequest request, HttpServletResponse response, @RequestBody
	 * LoginBean loginBean) throws ServletException, IOException { LoginBean
	 * loginBean1 = LoginDetails.get(LoginId); LoginDetails.remove(LoginId); return
	 * loginBean1; }
	 * 
	 * @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	 * public @ResponseBody LoginBean deleteUser(@PathVariable("id") int LoginId,
	 * HttpServletRequest request, HttpServletResponse response, @RequestBody
	 * LoginBean loginBean) throws ServletException, IOException { LoginBean
	 * loginBean1 = LoginDetails.get(LoginId); LoginDetails.remove(LoginId); return
	 * loginBean1; }
	 * 
	 */
}
