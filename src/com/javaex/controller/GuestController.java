package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guestbook")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("list".equals(action)) {
			GuestbookDao bookDao = new GuestbookDao();
			List<GuestbookVo> list = bookDao.bookList();

			request.setAttribute("guestList", list);

			WebUtil.forword(request, response, "/WEB-INF/addList.jsp");
		} else if ("insert".equals(action)) {
			String name = request.getParameter("name");
			String pw = request.getParameter("pw");
			String content = request.getParameter("content");

			GuestbookVo guest = new GuestbookVo(name, pw, content);
			GuestbookDao bookDao = new GuestbookDao();

			bookDao.bookInsert(guest);

			WebUtil.redirect(request, response, "/guestbook2/guestbook?action=list");
		} else if ("dPage".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("id", id);

			WebUtil.forword(request, response, "/WEB-INF/deleteForm.jsp");
		} else if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("_id"));
			String pw = request.getParameter("pw");

			GuestbookDao bookDao = new GuestbookDao();

			bookDao.delectBook(id, pw);

			WebUtil.redirect(request, response, "/guestbook2/guestbook?action=list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}