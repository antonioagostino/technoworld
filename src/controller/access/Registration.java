package controller.access;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.users.User;
import technicalservices.MailUtility;
import technicalservices.persistence.DBManager;

public class Registration extends HttpServlet {
	
	static boolean validPassword(String password) {
		if(password.length() < 8)
			return false;
		
		boolean lower = false, upper = false, number = false;
		for(int i=0; i<password.length() && (!lower || !upper || !number); i++) {
			if(Character.isLowerCase(password.charAt(i)))
				lower = true;
			if(Character.isUpperCase(password.charAt(i)))
				upper = true;
			if(Character.isDigit(password.charAt(i))) 
				number = true;
		}
		if(!lower || !upper || !number) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
		req.getSession().removeAttribute("name");
		req.getSession().removeAttribute("surname");
		req.getSession().removeAttribute("date");
		req.getSession().removeAttribute("email");
		req.getSession().removeAttribute("username");
		req.getSession().removeAttribute("question");
		req.getSession().removeAttribute("answer");
		req.getSession().removeAttribute("sameUsername");
		req.getSession().removeAttribute("sameEmail");
		req.getSession().removeAttribute("invalidPassword");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DBManager db = DBManager.getInstance();
		
		RequestDispatcher rd = null;
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String date = req.getParameter("date");
		
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		User alreadyUser = db.getUserByUsername(username);
		User alreadyUserEmail = db.getUserByEmail(email);
		boolean validPassword = validPassword(password);
		Pattern p = Pattern.compile("^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])");
		Matcher m = p.matcher(email);
		boolean validEmail = m.matches();
		
		/*regex per controllo email*/
		
		if(alreadyUser == null && alreadyUserEmail == null && validPassword && validEmail) {
			User user = new User(username, password, name, surname, email, d, question, answer);
			
			db.registerUser(user);
			user.setId(db.getId(username));
			req.getSession().setAttribute("user", user);
			req.getSession().removeAttribute("name");
			req.getSession().removeAttribute("surname");
			req.getSession().removeAttribute("date");
			req.getSession().removeAttribute("email");
			req.getSession().removeAttribute("username");
			req.getSession().removeAttribute("question");
			req.getSession().removeAttribute("answer");
			req.getSession().removeAttribute("sameUsername");
			req.getSession().removeAttribute("sameEmail");
			req.getSession().removeAttribute("invalidPassword");
			try {
				MailUtility.sendRegistrationMail(email, username, password);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			rd = req.getRequestDispatcher("home.jsp");
		}
		
		else {
			
			if(alreadyUser != null)
				req.getSession().setAttribute("sameUsername", true);
			
			if(alreadyUserEmail != null)
				req.getSession().setAttribute("sameEmail", true);
			
			if(!validPassword)
				req.getSession().setAttribute("invalidPassword", true);
			
			if(!validEmail)
				req.getSession().setAttribute("invalidEmail", true);
			
			req.getSession().setAttribute("name", name);
			req.getSession().setAttribute("surname", surname);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("date", date);
			req.getSession().setAttribute("question", question);
			req.getSession().setAttribute("answer", answer);
			rd = req.getRequestDispatcher("registration.jsp");
		}
		
		rd.forward(req, resp);
	} 
}
