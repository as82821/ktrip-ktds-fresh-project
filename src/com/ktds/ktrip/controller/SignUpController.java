package com.ktds.ktrip.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.ktrip.dao.UserDAO;
import com.ktds.ktrip.domain.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/signupCheck")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		//������ ����� ������ ���
		String savePath = request.getSession().getServletContext().getRealPath("user_img");
		String defaultPhotoPath=request.getSession().getServletContext().getRealPath("user_img\\default.jpg");
		String picturePath;
		
		//���� ũ�� 15MB�� ����
		int sizeLimit = 1024*1024*15;

		//�� request ��ü,               �� ����� ���� ���,       �� ���� �ִ� ũ��,    �� ���ڵ� ���,       �� ���� �̸��� ���ϸ� ���� ó��
		//(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
		//�Ʒ��� ���� MultipartRequest�� ������ ���ָ� ������ ���ε� �ȴ�.(���� ��ü�� ���ε� �Ϸ�)
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		UserVO vo = new UserVO();
		vo.setId(multi.getParameter("id"));
		vo.setPwd(multi.getParameter("pwd"));
		vo.setEmail(multi.getParameter("email"));
		vo.setName(multi.getParameter("name"));
		vo.setSex(multi.getParameter("sex"));
		vo.setPhone_num(multi.getParameter("phone_num"));
		vo.setNational(multi.getParameter("country"));
		vo.setResidential_contry(multi.getParameter("residential_country"));
		if(multi.getFilesystemName("photo")==null) {
			picturePath=defaultPhotoPath;
		}else {
			picturePath=savePath+"\\"+multi.getParameter("id")+"\\"+multi.getFilesystemName("photo");
		}
		
		System.out.println(vo.toString());
		System.out.println(picturePath);

		UserDAO dao = new UserDAO();
		int cheackvalue = dao.insertUser(vo,picturePath);

		if (cheackvalue == -1) {
			response.sendRedirect("/ktrip/signupFailAction.jsp");
		}else {
			request.setAttribute("success", "true");
			response.sendRedirect("/ktrip/login2.jsp");
		}
	}
}
