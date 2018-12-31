package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/member/login.do")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberDAO dao = MemberDAO.getInstance();
		HashMap<String, String> hm = dao.memberLogin(id,pass);
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(hm.size()==3) {//회원
			if(hm.get("id").equals("admin")) {//관리자
				HttpSession sesstion = request.getSession();
				sesstion.setAttribute("name", hm.get("name"));
				sesstion.setAttribute("id", hm.get("id"));
				out.println(2);
			}else {//일반회원
				HttpSession sesstion = request.getSession();
				sesstion.setAttribute("name", hm.get("name"));
				out.println(1);
			}
		}else if(hm.size()==2) {//비밀번호 틀림.
			out.println(0);
		}else {//회원아님.
			out.println(-1);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
