package com.ktds.ktrip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.ktrip.domain.ApplyVO;
import com.ktds.ktrip.domain.ItemVO;
import com.ktds.ktrip.dao.ItemDAO;

//Leeyong coding guide_item list & CRUD

@WebServlet("/itemServlet")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		myFunction(request, response);
	}

	protected void myFunction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
//		
		HttpSession session = request.getSession();
		session.setAttribute("guide_id", 2); // 로그인에서 session 전달 구현

		String actionMode = request.getParameter("actionMode");

		if (actionMode.equals(null)) {
			response.sendRedirect("/ktrip/itemServlet?actionMode=LIST");

		} else if (actionMode.equals("INS")) {
			// item을 추가한 경우

			ItemVO item = new ItemVO();

			item.setTitle(request.getParameter("title"));
			item.setConcept(request.getParameter("concept"));
			item.setContents(request.getParameter("contents"));
			item.setDestination(request.getParameter("destination"));
			item.setDuration(Integer.parseInt(request.getParameter("duration")));
			item.setGuide_id((int) session.getAttribute("guide_id"));
			item.setItem_status(request.getParameter("item_status"));
			item.setNum_max(Integer.parseInt(request.getParameter("num_max")));
			item.setNum_min(Integer.parseInt(request.getParameter("num_min")));
			item.setPrice(Integer.parseInt(request.getParameter("price")));
			item.setThumbnail(request.getParameter("thumbnail"));

			ItemDAO idao = new ItemDAO();
			idao.addProduct(item);

			RequestDispatcher rd = request.getRequestDispatcher("/itemServlet?actionMode=LIST");
			rd.forward(request, response);

		} else if (actionMode.equals("UPDATE")) {
			// item을 수정한 경우 fin

			ItemVO item = new ItemVO();
			// List<ItemVO> list = new ArrayList<ItemVO>();
			ItemDAO idao = new ItemDAO();

			int guide_id = (int) session.getAttribute("guide_id");

			item.setItem_id(Integer.parseInt(request.getParameter("item_id")));
			item.setTitle(request.getParameter("title"));
			item.setConcept(request.getParameter("concept"));
			item.setContents(request.getParameter("contents"));
			item.setDuration(Integer.parseInt(request.getParameter("duration")));
			item.setNum_max(Integer.parseInt(request.getParameter("num_max")));
			item.setNum_min(Integer.parseInt(request.getParameter("num_min")));
			item.setPrice(Integer.parseInt(request.getParameter("price")));
			item.setThumbnail(request.getParameter("thumbnail"));

			idao.updateItem(item, guide_id);

			RequestDispatcher rd = request.getRequestDispatcher("/itemServlet?actionMode=LIST");
			rd.forward(request, response);

		} else if (actionMode.equals("DEL")) {
			// 기존의 내용을 삭제한경우 Del fin

			ItemVO item = new ItemVO();
			ItemDAO idao = new ItemDAO();

			int item_id = Integer.parseInt(request.getParameter("item_id"));
			// user_id를 세션에서 받아옴

			// list를 넘겨줌

			idao.delProduct(item_id);
			System.out.println("DEL 완료");
			RequestDispatcher rd = request.getRequestDispatcher("/itemServlet?actionMode=LIST");
			rd.forward(request, response);

		} else if (actionMode.equals("STATCHANGE")) {
			// 완
			ItemDAO idao = new ItemDAO();
			ItemVO item = new ItemVO();

			int apply_id = Integer.parseInt(request.getParameter("apply_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));// 신청자id
			int item_status = Integer.parseInt(request.getParameter("item_status"));
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			idao.changeStatus(user_id, item_status, apply_id);
			// int guide_id = (int) session.getAttribute("guide_id");

			RequestDispatcher rd = request.getRequestDispatcher("/itemServlet?actionMode=SELECT");
			rd.forward(request, response);
		} else if (actionMode.equals("SELECT")) {
			
			// 상품을 선택했을 경우 fin
			System.out.println("호출받음>>>");
			ItemVO item = new ItemVO();

			ItemDAO idao = new ItemDAO();
			List<ApplyVO> applyList = new ArrayList<ApplyVO>();
			int guide_id = (int) session.getAttribute("guide_id");// (request.getParameter("user_id"));
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			// list = idao.showAll(user_id);
			System.out.println("item_ID>>>" + item_id);

			item = idao.showSelect(item_id, guide_id);
			applyList = idao.showApply(item_id);

			request.setAttribute("applyList", applyList);
			request.setAttribute("item", item);
			for (int i = 0; i < applyList.size(); i++) {
				System.out.println("SEL>>>" + applyList.get(i).getName());
				System.out.println("SEL>>>" + applyList.get(i).getStatusToString());
			}
			System.out.println("SEL>>>" + item.getTitle());
			System.out.println("SEL>>>" + item.getNum_min());
			System.out.println("SEL>>" + item.getPrice());
			System.out.println("SEL>>>" + item.getNum_max());
			RequestDispatcher rd = request.getRequestDispatcher("/guide_popup.jsp");
			rd.forward(request, response);
			// 현재 페이지가 받은 데이터를 다음페이지로 넘겨준다.

		} else if (actionMode.equals("LIST")) {

			ItemDAO idao = new ItemDAO();
			List<ItemVO> list = new ArrayList<ItemVO>();
			// db에 있는 모든 리스트 보내기

			int guide_id = (int) session.getAttribute("guide_id");
			list = idao.showAll(guide_id);
			request.setAttribute("list", list);
			for (int i = 0; i < list.size(); i++) {
				System.out.println("LIST>>>" + list.get(i).getTitle());
				System.out.println("LIST>>>" + list.get(i).getDuration());
			}
			RequestDispatcher rd = request.getRequestDispatcher("/guide_item_list.jsp");
			rd.forward(request, response);

		}

	}
}