package com.yedam.common;

import java.util.List;

import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardTest {
	public static void main(String[] args) {
		BoardService svc = new BoardServiceImpl();
		// 등록
//		BoardVO vo = new BoardVO();
//		vo.setTitle("등록제목");
//		vo.setContent("등록내용");
//		vo.setWriter("user03");
//		
//		if(svc.addBoard(vo)) {
//			System.out.println("등록성공");
//		} else {
//			System.out.println("등록실패");
//		}
		
		// 조회
		BoardVO vo = svc.getBoard(1);
		if(vo != null) {
			System.out.println(vo.toString());
		} else {
			System.out.println("조회결과 없음");
		}
		
		// 목록
//		List<BoardVO> list = svc.boardList();
//		for(BoardVO board : list) {
//			System.out.println(board.toString());
//		}		
	}
}
