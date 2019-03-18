package com.ktds.ktrip.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.ktrip.domain.UserVO;
import com.ktds.ktrip.dao.UserDAO;

@WebServlet("/loginCheck")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("password"));

		UserVO vo = new UserVO();
		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("password"));

		System.out.println(vo.toString());

		UserDAO dao = new UserDAO();
		vo = dao.login(vo);

		if (vo == null) {
			request.setAttribute("success", "false");
			response.sendRedirect("/ktrip/loginFail.jsp");
		} else if (vo.getType() == 1) {
			session.setAttribute("user_id", vo.getNum_id());
			request.setAttribute("success", "true");
			response.sendRedirect("/ktrip/index.jsp");
		} else if (vo.getType() == 2) {
			session.setAttribute("user_id", vo.getNum_id());
			session.setAttribute("guide_id", vo.getNum_id());
			request.setAttribute("success", "true");
			response.sendRedirect("/ktrip/index.jsp");
		}
	}
}