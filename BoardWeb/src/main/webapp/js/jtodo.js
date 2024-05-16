/**
 * jtodo.js
 */
// 화면의 li 요소에 닫기 버튼 달기
$('li').append($('<span />')
				.addClass('close')
				.text('\u00D7')
				.on('click', function(e) {
					$(this).parent().css('display', 'none');
				})
);

// ul의 하위에 있는 li 요소에 클릭 이벤트
$('ul').on('click', function(e) {
	if ($(e.target).prop('tagName') == 'LI') {
		$(e.target).toggleClass('checked');
	}
})

// 신규 요소 등록하기
function newElement() {
	let inputValue = $('#myInput').val();
	let li = $('<li />').text(inputValue);

	if (inputValue == '') {
		alert("You must write something!");
	} else {
		li.appendTo('#myUL');
	}
	$('#myInput').val('');

	li.append($('<span />')
				.text('\u00D7')
				.addClass('close')
				.on('click', function(e) {
					$(this).parent().css('display', 'none');
				})
	);
}