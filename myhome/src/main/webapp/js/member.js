/**
 * member.js
 */
// 추가 클릭 이벤트 등록
// 사용자의 입력값 3개 -> tr > td * 3 -> tbody 하위요소 추가

let addBtn = document.querySelector('#addMember').addEventListener('click', function(){
	let memberNo = document.querySelector('#memberNo').value;
	let memberName = document.querySelector('#memberName').value;
	let memberPoint = document.querySelector('#memberPoint').value;
	const member = {memberNo, memberName, memberPoint};
	
	let tr = makeRow(member);
	
	document.querySelector('table#tlist tbody').appendChild(tr);
});

// member 정보를 활용 tr 반환
function makeRow(member={memNo, memName, memPnt}) {
	let tr = document.createElement('tr');

	// tr에 click 이벤트 등록
	tr.addEventListener('click', function(e){
		e.stopPropagation();
		// tr(this)의 자식요소 children
		document.querySelector('#memberNo').value = this.children[0].innerText;
		document.querySelector('#memberName').value = this.children[1].innerText;
		document.querySelector('#memberPoint').value = this.children[2].innerText;	
	}, true)
	for(let prop in member) {
		let td = document.createElement('td');
		td.innerText = member[prop];
		tr.appendChild(td);
	}
	// <td><button>삭제</button></td>
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerText = '삭제';
    btn.addEventListener('click', deleteRow, true);
	td.appendChild(btn);
	tr.appendChild(td);
	// 체크박스
	td = document.createElement('td');
	let chk = document.createElement('input');
	chk.setAttribute('type', 'checkbox');
	td.appendChild(chk);
	tr.appendChild(td);
	
	return tr;
} // end of makeRow

function deleteRow(evnt) {
	// evnt.stopPropagation(); // 상/하위 요소로 이벤트 전파 차단
	evnt.target.parentElement.parentElement.remove();
}

// members 배열의 갯수만큼 tr 생성
members.forEach(function(item) {
	let tr = makeRow(item);
	document.querySelector('table#tlist tbody').appendChild(tr);
});

// thead input[type="checkbox"]
document.querySelector('thead input[type="checkbox"]').addEventListener('change', function() {
	// thead -> tbody 적용
	document.querySelectorAll('tbody input[type="checkbox"]').forEach((item) => {
		item.checked = this.checked;
	})
})

document.querySelector('#editMember').addEventListener('click', function(){
	let memberNo = document.querySelector('#memberNo').value;
	let memberName = document.querySelector('#memberName').value;
	let memberPoint = document.querySelector('#memberPoint').value;
	
	document.querySelectorAll('table#tlist tbody tr').forEach(function(item) {
		if(item.children[0].innerText == memberNo) {
			item.children[1].innerText = memberName;
			item.children[2].innerText = memberPoint;
		}
	})
})
