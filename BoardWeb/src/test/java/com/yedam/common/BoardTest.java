package com.yedam.common;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class BoardTest {
	public static void main(String[] args) {
		SqlSession session = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		SearchVO search = new SearchVO();
		search.setBoardNo(200);
		search.setRpage(2);
		
		mapper.replyListPaging(search).forEach(reply -> System.out.println(reply));
		
	}
}
