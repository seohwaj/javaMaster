package com.yedam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public interface BoardMapper {
	List<BoardVO> boardList(); // 목록
	List<BoardVO> boardListPaging(int page); // 페이징 목록
	int getTotalCnt(); // 전체 건수 계산
	int insertBoard(BoardVO board); // 등록
	BoardVO selectBoard(int boardNo);
	int updateViewCnt(int boardNo);
	int updateBoard(BoardVO board); // 수정
	int deleteBoard(int boardNo); // 삭제
	
	// 사용자 id, pw 확인
	MemberVO selectMember(@Param("id") String id, @Param("pw") String pw);
	
	MemberVO selectMember2(String id);
}
