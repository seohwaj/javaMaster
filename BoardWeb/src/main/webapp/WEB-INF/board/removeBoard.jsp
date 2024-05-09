<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>삭제화면</h3>
<%	
	String bno = request.getParameter("bno");
%>
<form action="deleteBoard.do">
  <table class="table">
    <tr>
      <th>삭제하시겠습니까?</th>
    </tr>
    <tr>
      <td><input type="submit" class="btn btn-danger" value="삭제"></td>
    </tr>
  </table>
  <input type="hidden" name="bno" value="<%=bno %>">
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>