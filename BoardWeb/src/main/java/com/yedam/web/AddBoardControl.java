package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// title, content, writer 파라미터
		// multipart 요청 처리를 위한 처리
		// 1.request 정보 2.저장 경로 3.max size 4.encoding 5.rename 정책
		String savePath = req.getServletContext().getRealPath("images");
		int maxSize = 5 * 1024 * 1024;
		
		MultipartRequest mr = new MultipartRequest(req, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("myImg");
		
		BoardService svc = new BoardServiceImpl();
		
		MemberVO mvo = svc.checkMember(writer);
		
		if(mvo == null) {
			req.setAttribute("message", "권한이 없습니다.");
			req.getRequestDispatcher("WEB-INF/board/addBoard.jsp").forward(req, resp);
			
			return;
		}
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setImg(img);
		
		if(svc.addBoard(vo)) {
			System.out.println("등록성공");
			// 페이지 이동: 목록 페이지
			resp.sendRedirect("main.do");
		} else {
			System.out.println("등록실패");
			resp.sendRedirect("addForm.do");
		}
	}

}
