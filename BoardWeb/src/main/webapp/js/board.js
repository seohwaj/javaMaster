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

// 댓글목록 출력
fetch('replyList.do?bno=' + bno)
	.then(resolve => resolve.json()) // json -> 객체
	.then(result => {
		console.log(result);
		result.forEach(reply => {
			let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
			console.log(tmpl);
			tmpl.style.display = 'block';
			tmpl.setAttribute('data-rno', reply.replyNo);
			tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
			tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
			tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
			document.querySelector('div.reply ul').appendChild(tmpl);
		})
	})
	.catch(err => {
		console.log(err);
	});

// 삭제버튼의 이벤트
function deleteRow(e) {
	const rno = e.target.parentElement.parentElement.dataset.rno;
	console.log(rno);
	// fetch 삭제 기능 구현
	fetch('removeReply.do?rno=' + rno)
		.then(resolve => resolve.json())
		.then(result => {
			if(result.retCode == 'OK') {
				alert('삭제 완료');
				e.target.parentElement.parentElement.remove();
			} else if(result.retCode == 'NG') {
				alert('삭제 실패');
			} else {
				alert('알 수 없는 반환값');
			}
		})
		.catch(err => console.log(err));
}

// 댓글등록버튼의 이벤트
document.querySelector('#addReply').addEventListener('click', function(e) {
	// fetch 등록 기능 구현
	let reply = document.querySelector('#reply').value;
	if(reply == '') {
		alert('댓글을 입력해주세요.');
	} else if(writer == '') {
		alert('로그인해주세요.');
	} else {
		fetch('addReply.do?bno=' + bno + '&replyer=' + writer + '&reply=' + reply)
			.then(resolve => resolve.json()) // json -> 객체
			.then(result => {
				if(result.retCode == 'OK') {
					location.reload();
				}
			})
			.catch(err => console.log(err));	
	}
});