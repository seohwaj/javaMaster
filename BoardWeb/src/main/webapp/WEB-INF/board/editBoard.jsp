<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>수정화면</h3>
<form action="updateBoard.do">
<table class="table">
  <tr>
    <th>글번호</th>
    <td>${bno.boardNo }</td>
  </tr>
  <tr>
    <th>제목</th>
    <td><input type="text" name="title" value="${bno.title }"></td>
  </tr>
  <tr>
    <th>내용</th>
    <td><textarea name="content" cols="30" rows="4">${bno.content }</textarea></td>
  </tr>
  <tr>
    <th>작성자</th>
    <td>${bno.writer }</td>
  </tr>
  <tr align="center">
    <td colspan="2">
      <c:choose>
        <c:when test="${bno.writer eq logId }">
          <input type="submit" class="btn btn-primary">    
        </c:when>
        <c:otherwise>
          <input type="submit" disabled class="btn btn-primary">    
        </c:otherwise>
      </c:choose>
    </td>
  </tr>
</table>
  <input type="hidden" name="bno" value="${bno.boardNo }">
  <input type="hidden" name="page" value="${page }">
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>