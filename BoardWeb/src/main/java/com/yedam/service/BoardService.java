package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public interface BoardService {
	// 목록
	List<BoardVO> boardList(SearchVO search);
	// 전체 건수
	int getTotal(SearchVO search);
	
	boolean addBoard(BoardVO board);
	BoardVO getBoard(int boardNo);
	int addViewCnt(int boardNo);
	// 수정
	boolean modifyBoard(BoardVO board);
	// 삭제
	boolean removeBoard(int boardNo);
	
	// 로그인
	MemberVO login(String id, String pw);
	MemberVO checkMember(String id);
}
