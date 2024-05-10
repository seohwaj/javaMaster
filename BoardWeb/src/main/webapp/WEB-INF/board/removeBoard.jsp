<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>삭제화면</h3>
<form name="myFrm" action="deleteBoard.do">
  <table class="table">
    <tr>
      <th>삭제하시겠습니까?</th>
    </tr>
    <tr>
      <td><input type="submit" class="btn btn-danger" value="삭제"></td>
    </tr>
  </table>
  <input type="hidden" name="bno" value="${bno.boardNo }">
</form>
<script>
	const logid = "${logId }";
	const writer = "${bno.writer}";
	
	document.forms.myFrm.addEventListener('submit', function(e) {
		e.preventDefault();
		if(logid != writer) {
			alert("권한이 없습니다.");
			return;
		}
		this.submit();
	})
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>