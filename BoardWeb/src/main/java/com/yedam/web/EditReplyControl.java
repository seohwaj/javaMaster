package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class EditReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String rno = req.getParameter("rno");
		String reply = req.getParameter("reply");
		
		ReplyVO rvo = new ReplyVO();
		rvo.setReplyNo(Integer.parseInt(rno));
		rvo.setReply(reply);
		
		ReplyService svc = new ReplyServiceImpl();
		if(svc.editReply(rvo)) {
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\": \"NG\"}");
		}
	}

}
