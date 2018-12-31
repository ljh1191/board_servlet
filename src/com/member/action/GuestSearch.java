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
 * Servlet implementation class GuestSearch
 */
@WebServlet("/guestTest/search")
public class GuestSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		String field = request.getParameter("field");
		String word = request.getParameter("word");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int endRow = currentPage*pageSize;
		int startRow = ((currentPage-1)* pageSize)+1 ;
		
		GuestDAO dao = GuestDAO.getInstance();
		ArrayList<GuestDTO> arr = dao.guestList(field,word,startRow,endRow);
		int count = dao.guestCount(field,word);
		int totpage = count/pageSize+(count%pageSize==0?0:1);
		int blockpage = 3;//[이전]123[다음]
		int startpage = ((currentPage-1)/blockpage)*blockpage+1;//4
		int endpage = (startpage+blockpage)-1;//6
		
		if(endpage>totpage) endpage =  totpage;
		
		request.setAttribute("totpage", totpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("blockpage", blockpage);
		
		request.setAttribute("lists", arr);
		request.setAttribute("count", count);
		request.setAttribute("field", field);
		request.setAttribute("word", word);
		
		RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
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
