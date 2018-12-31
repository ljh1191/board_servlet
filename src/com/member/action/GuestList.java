package com.member.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.GuestDAO;
import com.member.model.GuestDTO;

/**
 * Servlet implementation class GuestList
 */
@WebServlet("/guestTest/list")
public class GuestList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		GuestDAO dao = GuestDAO.getInstance();
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);//현재페이지
		int pageSize = 5;
		int endRow = currentPage*pageSize;
		int startRow = ((currentPage-1)* pageSize)+1 ;
		String field = "";
		String word = "";
		
		ArrayList<GuestDTO> arr = dao.guestList(startRow, endRow);
		int count = dao.guestCount();
		//총페이지수
		int totpage = count/pageSize+(count%pageSize==0?0:1);
		int blockpage = 3;//[이전]123[다음]
		int startpage = ((currentPage-1)/blockpage)*blockpage+1;//4
		int endpage = (startpage+blockpage)-1;//6
		/*
		 * 예) count = 23;
		 * toppage = 23/5+1=5 -> 123[다음],[이전]45[다음]
		 * startpage = 4
		 * endpage = 6 -> 5까지 나와야함.
		 * */
		if(endpage>totpage) endpage = totpage;
		
		request.setAttribute("totpage", totpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("blockpage", blockpage);
		
		request.setAttribute("lists", arr);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("listResult.jsp");
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
