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

let page = 1;
showList();

// 댓글목록 출력
function showList() {
	// 댓글목록 초기화
	document.querySelectorAll('div.content ul li').forEach((li, idx) => {
		if (idx >= 3) {
			li.remove();
		}	
	})
	svc.replyList({bno: bno, page: page}, // param
		result => {
			result.forEach(reply => {
				const row = makeRow(reply);
				document.querySelector('div.reply ul').appendChild(row);
			})
			makePageInfo();
		}, // successCall
		err => console.log(err) // errorCall
	) // end of replyList
} // end of showList

// 삭제버튼의 이벤트
function deleteRow(e) {
	const rno = e.target.parentElement.parentElement.dataset.rno;

	svc.removeReply(rno, 
		result => {
			if(result.retCode == 'OK') {
				alert('삭제 완료');
				showList();
			} else if(result.retCode == 'NG') {
				alert('삭제 실패');
			} else {
				alert('알 수 없는 반환값');
			}
		}, 
		err => console.log(err)
	)
}

// 댓글등록버튼의 이벤트
document.querySelector('#addReply').addEventListener('click', function(e) {
	let reply = document.querySelector('#reply').value;
	
	if(!reply) {
		alert('댓글을 입력해주세요.');
		return;
	}
	if(!writer) {
		alert('로그인해주세요.');
		return;
	}
	
	svc.addReply({bno: bno, writer: writer, reply: reply},
		result => {
			if(result.retCode == 'OK') {
				page = 1;
				showList();
				// 댓글초기화
				document.getElementById('reply').value = '';
			}
		},
		err => console.log(err)
	)
});

function makeRow(reply = {}) {
	let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
	tmpl.style.display = 'block';
	tmpl.setAttribute('data-rno', reply.replyNo);
	tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
	tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
	tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
	return tmpl;
}

// 페이징 생성
let pagination = document.querySelector('div.pagination');

function makePageInfo() {
	svc.getTotalCount(bno,
	createPageList, // successCall
	err => console.log(err) // errorCall
	)
}

function createPageList(result) {	
	let totalCnt = result.totalCount;
	let startPage, endPage, realEnd;
	let prev, next;
	
	realEnd = Math.ceil(totalCnt / 5);
	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	endPage = endPage > realEnd ? realEnd : endPage;
	
	prev = startPage > 1;
	next = endPage < realEnd;
	
	// a 태그 생성
	pagination.innerHTML = '';
	// 이전 페이지 여부
	if(prev) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', startPage - 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&laquo;";
		aTag.addEventListener('click', function(e) {
			e.preventDefault(); // a 태그의 페이지 이동 기능 차단
			page = e.target.dataset.page;
			showList();
		})
		pagination.appendChild(aTag);
	}
	for(let pg = startPage; pg <= endPage; pg++) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', pg);
		aTag.setAttribute('href', pg);
		if(pg == page) {
			aTag.className = 'active';
		}
		aTag.innerHTML = pg;
		aTag.addEventListener('click', function(e) {
			e.preventDefault(); // a 태그의 페이지 이동 기능 차단
			page = e.target.dataset.page;
			showList();
		})
		pagination.appendChild(aTag);
	}
	// 이후 페이지 여부
	if(next) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', endPage + 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&raquo;";
		aTag.addEventListener('click', function(e) {
			e.preventDefault(); // a 태그의 페이지 이동 기능 차단
			page = e.target.dataset.page;
			showList();
		})
		pagination.appendChild(aTag);
	}
}
