/**
 * jboard.js
 */

// 수정버튼
$('#modBtn').on('click', function() {
	document.forms.myFrm.action = "modBoardForm.do"; // 수정화면 호출
	document.forms.myFrm.submit();
});

// 삭제버튼
$('.btn-danger').on('click', function() {
	document.forms.myFrm.action = "removeBoardForm.do"; // 삭제화면 호출
	document.forms.myFrm.submit();
});

let page = 1;
showList();

function showList() {
	$('div.content ul li:gt(2)').remove();
	svc.replyList({bno: bno, page: page},
		result => {
			result.forEach(reply => {
				const row = makeRow(reply);
				row.appendTo('div.reply ul');
			})
			makePageInfo();
		},
		err => console.log(err)
	)
}

// 삭제버튼의 이벤트
function deleteRow(e) {
	const rno = $(e.target).parent().parent().data('rno');

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
$('#addReply').on('click', function() {
	let reply = $('#reply').val();
	
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
				$('#reply').val('');
			}
		},
		err => console.log(err)
	)
});

function makeRow(reply = {}) {
	let tmpl = $('div.reply li:eq(2)').clone();
	tmpl.css('display', 'block');
	tmpl.on('dblclick', function(e) {
		$('#myModal').css('display', 'block');
		$('input[name="modal_reply"]').val('');
		let replyNo = $(e.target).parent().children().eq(0).text();
		$('.modal-content p:eq(0)').text('댓글번호: ' + replyNo);
	});
	tmpl.attr('data-rno', reply.replyNo);
	tmpl.find('span:eq(0)').text(reply.replyNo);
	tmpl.find('span:eq(1)').text(reply.reply);
	tmpl.find('span:eq(2)').text(reply.replyer);
	return tmpl;
}

// 페이징 생성
let pagination = $('div.pagination');

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
	pagination.html('');
	// 이전 페이지 여부
	if(prev) {
		let aTag = $('<a>&laquo;</a>').attr('data-page', startPage - 1).attr('href', '#');
		aTag.on('click', function(e) {
			e.preventDefault(); // a 태그의 페이지 이동 기능 차단
			page = $(e.target).data('page');
			showList();
		})
		pagination.append(aTag);
	}
	for(let pg = startPage; pg <= endPage; pg++) {
		let aTag = $('<a />').html(pg).attr('data-page', pg).attr('href', pg);
		if(pg == page) {
			// aTag.attr('class', 'active');
			aTag.addClass('active');
		}
		aTag.on('click', function(e) {
			e.preventDefault(); // a 태그의 페이지 이동 기능 차단
			page = $(e.target).data('page');
			showList();
		})
		pagination.append(aTag);
	}
	// 이후 페이지 여부
	if(next) {
		let aTag = $('<a>&raquo;</a>').attr('data-page', endPage + 1).attr('href', '#');
		aTag.on('click', function(e) {
			e.preventDefault(); // a 태그의 페이지 이동 기능 차단
			page = $(e.target).data('page');
			showList();
		})
		pagination.append(aTag);
	}
}

// 수정기능 추가
$('.modal-content button').on('click', function() {
	let reply = $('input[name="modal_reply"]').val();
	let replyNo = $('.modal-content p:eq(0)').text().substr(6);
	
	fetch('editReply.do?rno=' + replyNo + '&reply=' + reply)
		.then(resolve => resolve.json())
		.then(result => {
			if(result.retCode == 'OK') {
				alert('수정완료');
				$('#myModal').css('display', 'none');
				showList();
			} else if(result.retCode == 'NG') {
				alert('수정실패');
			} else {
				alert('처리 중 에러');
			}
			
		})
		.catch(err => console.log(err));	
})

$('.close').on('click', function() {
	$('#myModal').css('display', 'none');
})