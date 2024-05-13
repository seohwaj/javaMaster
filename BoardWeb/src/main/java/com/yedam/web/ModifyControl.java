package com.yedam.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// bno, title, content 파라미터
		// 수정이 완료되면 목록으로 이동
		req.setCharacterEncoding("UTF-8");
		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String page = req.getParameter("page");
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		
		BoardService svc = new BoardServiceImpl();
		String encodeKW = URLEncoder.encode(kw, "UTF-8");
		BoardVO vo = svc.getBoard(Integer.parseInt(bno));
		vo.setTitle(title);
		vo.setContent(content);
		
		if(svc.modifyBoard(vo)) {
			resp.sendRedirect("main.do?page=" + page + "&searchCondition=" + sc + "&keyword=" + encodeKW); // 질의 문자열(query)
		} else {
			System.out.println("처리 중 에러");
		}

	}

}
