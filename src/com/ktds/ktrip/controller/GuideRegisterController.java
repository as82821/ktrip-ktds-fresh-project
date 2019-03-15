package com.ktds.ktrip.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.ktrip.dao.UserDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/guideRegisterCheck")
public class GuideRegisterController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//������ ����� ������ ���
		String portfolio_savePath = request.getSession().getServletContext().getRealPath("portfolio_img");
		String id_documentsavePath = request.getSession().getServletContext().getRealPath("portfolio_img");
		String defaultPhotoPath=request.getSession().getServletContext().getRealPath("portfolio_img\\default.jpg");		
		
		//���� ũ�� 15MB�� ����
		int sizeLimit = 1024*1024*15;

		//�� request ��ü,               �� ����� ���� ���,       �� ���� �ִ� ũ��,    �� ���ڵ� ���,       �� ���� �̸��� ���ϸ� ���� ó��
		//(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
		//�Ʒ��� ���� MultipartRequest�� ������ ���ָ� ������ ���ε� �ȴ�.(���� ��ü�� ���ε� �Ϸ�)
		MultipartRequest multi = new MultipartRequest(request, portfolio_savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		int user_id=Integer.parseInt(multi.getParameter("user_id"));
		String second_lang=multi.getParameter("second_lang");
		String stay_duration=multi.getParameter("stay_duration");
		String introduction=multi.getParameter("introduction");
		String portfolio=portfolio_savePath+"\\"+user_id+"\\"+multi.getFilesystemName("portfolio");
		String id_document=id_documentsavePath+"\\"+user_id+"\\"+multi.getFilesystemName("id_document");
		System.out.println("���� ���ε� �Ϸ�");
		
		System.out.println("�������̵� "+user_id);
		System.out.println("��2�ܱ��� "+second_lang);
		System.out.println("�Ⱓ "+stay_duration);
		System.out.println("��Ʈ������ "+portfolio);
		System.out.println("�����ڷ� "+id_document);
		
		if(multi.getFilesystemName("photo")==null) {
			portfolio=defaultPhotoPath;
			id_document=defaultPhotoPath;
		}else {
			portfolio=portfolio_savePath+"\\"+multi.getParameter("id")+"\\"+multi.getFilesystemName("portfolio");
			id_document=id_documentsavePath+"\\"+multi.getParameter("id")+"\\"+multi.getFilesystemName("id_document");
		}
		UserDAO dao=new UserDAO();
		int check = dao.registerGuide(user_id, second_lang, stay_duration, introduction, portfolio, id_document);
		System.out.println("���̵� ��û ����� "+check);
		if(check==-1) {
			resp.sendRedirect("/ktrip/guide_failAction.jsp");
		}else {
			resp.sendRedirect("/ktrip/guide_successAction.jsp");
		}
	}

}
