/**
 * board.js
 */
// 수정버튼
document.querySelector('#modBtn').addEventListener('click', function() {
	document.forms.myFrm.action = "modBoardForm.do"; // 수정화면 호출
	document.forms.myFrm.submit();
});

// 삭제버튼
document.querySelector('.btn-danger').addEventListener('click', function() {
	document.forms.myFrm.action = "removeBoardForm.do"; // 삭제화면 호출
	document.forms.myFrm.submit();
});
