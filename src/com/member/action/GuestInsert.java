package com.member.action;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.GuestDAO;
import com.member.model.GuestDTO;

/**
 * Servlet implementation class GuestInsert
 */
@WebServlet("/guestTest/create")
public class GuestInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String grade = request.getParameter("grade");
		GuestDTO dto = new GuestDTO();
		GuestDAO dao = GuestDAO.getInstance();
		dto.setName(name);
		dto.setContent(content);
		dto.setGrade(grade);
		InetAddress local = InetAddress.getLocalHost();
		dto.setIpaddr(local.getHostAddress());
		
		dao.guestInsert(dto);
		response.sendRedirect("list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
