/**
 * portal.js
 */

const showTitles = ['id', 'centerName', 'address', 'sido', 'phoneNumber'];
let url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=1cLqHVDOdrfkLmviP5SOixvEaVdVD%2FTsdiBbanJKZTSAWJeVJLcZX2YtS4V3hoEFruaSpppJZqyG2Nh1KOQSOQ%3D%3D';
let totalData = [];

// API 호출
fetch(url)
	.then(result => result.json())
	.then(data => {
		console.log(data);
		totalData = data.data;
		/*data.data.forEach(center => {
			let tr = makeRow(center);
			document.querySelector('#list').appendChild(tr);
		})*/
		showPaging(1);
	})
	.catch(console.log);

// 링크 클릭하면 페이지 호출
/*
document.querySelectorAll('.pagination a').forEach(aTag => {
	aTag.addEventListener('click', function(e) {
		e.preventDefault(); // a 페이지 이동 차단
		let page = this.innerText;
		showPaging(page);
	})
})
*/

// pagingList: 전체건수를 계산해서 284건 29페이지
let totalCnt = 284;

function pagingList(page = 1){
	let pagination = document.querySelector('.pagination');
	pagination.innerHTML = '';
	
	let endPage, startPage;
	let prev, next;
	let realEnd = Math.ceil(totalCnt / 10); // 29page
	
	endPage = Math.ceil(page / 10) * 10;
	startPage = endPage - 9;
	endPage = endPage > realEnd ? realEnd : endPage;
	
	next = endPage < realEnd ? true : false;
	prev = startPage > 1;
	
	if(prev) {
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', startPage - 1);
		aTag.innerHTML = '&laquo;';
		
		aTag.addEventListener('click', function(e) {
			let page = e.target.dataset.page;
			showPaging(page);
		})
		
		pagination.appendChild(aTag);
	}
	
	// aTag 생성, 이벤트 추가
	for(let n = startPage; n <= endPage; n++) {
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.innerHTML = n;
		
		if(page == n) {
			aTag.className = 'active'; // class 속성 지정
		}
		
		aTag.addEventListener('click', function(e) {
			let page = e.target.innerHTML;
			showPaging(page);
		})
		
		pagination.appendChild(aTag);
	}
	
	if(next) {
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', endPage + 1);
		aTag.innerHTML = '&raquo;';
		
		aTag.addEventListener('click', function(e) {
			let page = e.target.dataset.page;
			showPaging(page);
		})
		
		pagination.appendChild(aTag);
	}
} // end of pagingList

// 페이지 -> 10개씩 출력
function showPaging(page = 1) { // 0 ~ 9: 1 page, 10 ~ 19: 2 page ...
	let startNo = (page - 1) * 10;
	let endNo = page * 10;
	let fAry = totalData.filter((center, idx) => {
		if(idx >= startNo && idx < endNo) {
			return true;
		}
	})
	
	// 목록 삭제
	document.querySelector('#list').innerHTML = '';
	
	fAry.forEach(center => {
		let tr = makeRow(center);
		document.querySelector('#list').appendChild(tr);
	})

	pagingList(page);
} // end of showPaging
	
// 데이터(센터) tr 함수
function makeRow(center = {}) {
	// 한 건에 대한 처리
	let tr = document.createElement('tr');
	tr.addEventListener('click', function(e) {
		console.log(center);
		window.open('daum.html?x=' + center.lat + '&y=' + center.lng + '&name=' + center.centerName);
		
	})
	
	showTitles.forEach(title => {
		let td = document.createElement('td');
		let name = center[title];
		td.innerText = (name + '').replace('코로나19 ', '');
		tr.appendChild(td);
	});
	
	return tr;
} // end of makeRow