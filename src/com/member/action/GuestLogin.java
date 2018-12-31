package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.member.model.GuestDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class GuestLogin
 */
@WebServlet("/guestTest/login")
public class GuestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		GuestDAO dao = GuestDAO.getInstance();
		int check = dao.guestLogin(id,pwd);
		String msg = "";
		String url = "";
		if(check == 1) {//ȸ������
			HttpSession sesstion = request.getSession();
			MemberDTO mdto = dao.getMember(id);
			//name,id
			sesstion.setAttribute("mdto", mdto);
//			sesstion.setAttribute("userid", id);
			//msg = "ȸ���� �½��ϴ�.";
			url = "guestInsert.jsp";
		}else if(check == 0) {//��й�ȣ Ʋ��
			msg = "��й�ȣ�� Ʋ���ϴ�.";
			url = "login.jsp";
		}else {//ȸ���ƴ�
			msg = "ȸ���� �ƴմϴ�.";
			url = "login.jsp";
		}
		request.setAttribute("msg", msg);
		response.setContentType("text/html;charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
