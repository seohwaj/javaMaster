<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>상세화면</h3>
<%
	BoardVO vo = (BoardVO) request.getAttribute("result");
%>
<c:choose>
  <c:when test="${empty result }">
    <p>조회된 결과가 없습니다.</p>
  </c:when>
  <c:otherwise>
    <form name="myFrm">
      <input type="hidden" name="bno" value="${result.boardNo }">
      <input type="hidden" name="page" value="${page }">
    </form>
    <table class="table">
      <tr>
        <th>게시글번호</th>
        <td>${result.boardNo }</td>
        <th>조회수</th>
        <td>${result.viewCnt }</td>
      </tr>
      <tr>
        <th>제목</th>
        <td>${result.title }</td>
        <th>작성자</th>
        <td>${result.writer }</td>
      </tr>
      <tr>
 	    <td colspan="4">${result.content }</td>
 	  </tr>
 	  <tr>
 	    <th>작성일시</th>
 	    <td colspan="3">
 	      <fmt:formatDate value="${result.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />
 	    </td>
 	  </tr>
 	  <tr align="center">
 	    <td colspan="4">
 	      <button class="btn btn-primary" id="modBtn">수정</button>
 	      <button class="btn btn-danger">삭제</button>
 	    </td>
 	  </tr>
	</table>
  </c:otherwise>
</c:choose>
<script src="js/board.js"></script>
<jsp:include page="../includes/footer.jsp"></jsp:include>