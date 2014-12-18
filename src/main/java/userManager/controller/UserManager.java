package userManager.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import userManager.auth.UserToken;
import userManager.dao.UserDao;
import userManager.entity.User;
import userManager.msg.Login;

@Controller
@RequestMapping("/rest")
public class UserManager {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public void welcomeName(HttpServletRequest req, HttpServletResponse rsp) {
		try {
			String name = (String) req.getParameter("name");

			rsp.setContentType("text/plain");
			PrintWriter out = rsp.getWriter();
			out.println("name=" + name);

		} catch (Exception ex) {

		}
		return;

	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public void welcomeName(@RequestParam/*
										 * (value="cc", required=false,
										 * defaultValue="World")
										 */String name, HttpServletResponse rsp) {
		try {
			rsp.setContentType("text/plain");
			PrintWriter out = rsp.getWriter();
			out.println("name=" + name);

		} catch (Exception ex) {

		}
		return;

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getUsers() {
		return userDao.list();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Content-Type=application/json;charset=UTF-8")
	public ResponseEntity<String> createUser(@RequestHeader("token") String token, @RequestBody User user) {

		if (token == null) {
			return new ResponseEntity<String>("not authed!", HttpStatus.FORBIDDEN);
		}
		
		if (StringUtils.isEmpty(user.getUserName())
				|| StringUtils.isEmpty(user.getPassword())
				|| user.getRole() == null) {
			return new ResponseEntity<String>("Error:username or password or role is empty", HttpStatus.BAD_REQUEST);
		}
		
		User existUser = userDao.getUser(user.getUserName());
		if (existUser != null)  {
			return new ResponseEntity<String>("Error:username already exists", HttpStatus.BAD_REQUEST);
		}
		
		userDao.create(user);
		user = userDao.getUser(user.getUserName());		

		return new ResponseEntity<String>("ok.userid=" + user.getId(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST, headers = "Content-Type=application/json;charset=UTF-8")
	public ResponseEntity<String> login(@RequestBody Login login) {

		if (login.getUserName() == null
				|| StringUtils.isEmpty(login.getUserName())
				|| login.getPassword() == null) {
			return new ResponseEntity<String>(
					"Error:username or password is empty!",
					HttpStatus.BAD_REQUEST);
		}

		User user = userDao.getUser(login.getUserName());
		if (user == null || !user.getPassword().equals(login.getPassword())) {
			return new ResponseEntity<String>(
					"Error:username or password mismatch!",
					HttpStatus.BAD_REQUEST);
		}

		String token = UserToken.makeEncodeToken(user.getId());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("token", token);

		return new ResponseEntity<String>("ok", responseHeaders,
				HttpStatus.CREATED);
	}

}
