/**
 * jquery1.js
 */

// document.addEventListener("DOMContentLoaded", initForm);
// $(document).ready(function() {
$(function() {
	// 삭제버튼
	$('tbody button').on('click', delRow);
	
	$('#addBtn').on('click', function() {
		// 2개 값을 td 생성, tr 생성, tbody 하위요소 추가
		let inputName = $('input[name="name"]');
		let inputScore = $('input[name="score"]');
		
		if(!inputName.val()) {
			alert('값을 입력하세요');
			return;
		}
		if(!inputScore.val()) {
			alert('값을 입력하세요');
			return;
		}
		
		let tr = $('<tr />').append(
			$('<td />').append($('<input />').attr('type', 'checkbox')),
			$('<td />').text(inputName.val()),
			$('<td />').text(inputScore.val()),
			$('<td />').append($('<button>삭제</button>').on('click', delRow))
		);
		
		$('#list tbody').append(tr);
		
		inputName.val('');
		inputScore.val('');
	})
});

function delRow(e) {
	// javascript
	// e.target.parentElement.parentElement.remove();
	
	// jquery
	$(e.target).parent().parent().remove();
}