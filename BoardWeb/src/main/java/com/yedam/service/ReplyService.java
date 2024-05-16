package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(SearchVO search); // 목록
	boolean removeReply(int replyNo); // 삭제
	boolean addReply(ReplyVO rvo); // 등록
	int totalReply(int boardNo); // 카운트
	boolean editReply(ReplyVO rvo); // 수정
}
