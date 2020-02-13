package controller.access;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import technicalservices.persistence.DBManager;

public class PasswordRecovery extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String usernameQuestion = req.getParameter("usernameForQuestion");
		String usernamePassword = req.getParameter("usernameForPassword");
		req.removeAttribute("invalidAnswer");
		
		String question = null;
		String password = null;
		
		if(usernameQuestion != null) {
			question = DBManager.getInstance().getSecurityQuestion(usernameQuestion);
			if(question == null) 
				question = "invalid";
			resp.getOutputStream().print(""+question);
		}
		
		else if(usernamePassword != null) {
			String answer = req.getParameter("answer");
			password = DBManager.getInstance().getPassword(usernamePassword, answer);
			if(password == null) 
				password = "invalid";
			resp.getOutputStream().print(""+password);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String newPassword = req.getParameter("newPassword");
		
		boolean validNewPassword = Registration.validPassword(newPassword);
		
		if(username != null && validNewPassword) {
			DBManager.getInstance().updatePassword(username, newPassword);
		}
		
		else if(username != null && !validNewPassword) {
			resp.getOutputStream().print("invalidNewPassword");
		}
	}
}
