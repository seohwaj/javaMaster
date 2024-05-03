/**
 * empFetch.js
 * Ajax 기능을 fetch 함수 활용
 * empSvc 객체에 기능을 구현, 메소드를 호출
 */

document.addEventListener("DOMContentLoaded", initForm);

function initForm() {
	// Ajax 호출, 목록 출력
	fetch('../empJson.json') // 반환 결과 값이 promise 객체
		.then(result => result.json()) // 출력스트림(json) -> 객체변환
		.then(data => {
			data.forEach(emp => {
				let tr = makeRow(emp);
				document.querySelector('#elist').appendChild(tr);
			})
		})
		.catch(err => console.log(err));
		
		// 등록 이벤트
		document.querySelector('#addBtn').addEventListener('click', addRow);
} // end of initForm

function makeRow(emp) {
	let props = ['empNo', 'empName', 'email', 'salary'];
	let tr = document.createElement('tr');
	tr.setAttribute('data-no', emp.empNo);
	tr.addEventListener('dblclick', modifyRow);
	props.forEach(prop => {
		let td = document.createElement('td');
		td.innerText = emp[prop];
		tr.appendChild(td);
	});
	
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRow);
	td.appendChild(btn);
	tr.appendChild(td);
	
	return tr;
} // end of makeRow

function deleteRow() {
	let eno = this.parentElement.parentElement.dataset.no;
	let tr = this.parentElement.parentElement;
	
	fetch('../empsave.json?job=delete&empNo=' + eno)
		.then(result => result.json())
		.then(data => {
			if(data.retCode == 'OK') {
				tr.remove();
			} else if(data.retCode == 'NG') {
				alert('처리 실패');
			}
		})
		.catch(err => console.log(err));
} // end of deleteRow

function addRow() {
	// DB insert & 화면 출력
	// 사원이름(ename), 연락처(phone), 급여(salary), 입사일자(hire), 이메일(email)
	let ename = document.querySelector('#ename').value;
	let ephone = document.querySelector('#ephone').value;
	let esalary = document.querySelector('#esalary').value;
	let ehire = document.querySelector('#ehire').value;
	let email = document.querySelector('#email').value;
	
	let param = 'job=add&name=' + ename + '&phone=' + ephone + '&salary=' + esalary + '&hire=' + ehire + '&email=' + email;

	fetch('../empsave.json', {
		method: 'post',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		body: param
	})
		.then(result => result.json())
		.then(data => {
			if(data.retCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#elist').appendChild(tr);
			}
		})
		.catch(console.log);
} // end of addRow

function modifyRow() {
	let originMail = this.children[2].innerText;
	let originSalary = this.children[3].innerText;
	
	let oldTr = this;
	let newTr = this.cloneNode(true); // 복제
	
	newTr.querySelector('td:nth-of-type(3)').innerHTML = '<input value="' + originMail + '">';
	newTr.querySelector('td:nth-of-type(4)').innerHTML = '<input value="' + originSalary + '">';
	newTr.querySelector('button').innerText = '수정';
	
	oldTr.parentElement.replaceChild(newTr, oldTr);
	
	newTr.querySelector('button').addEventListener('click', updateRow);
} // end of modifyRow

function updateRow() {
	let oldTr = this.parentElement.parentElement;
	let empNo = this.parentElement.parentElement.dataset.no; // data-no -> dataset.no
	let email = this.parentElement.parentElement.children[2].children[0].value;
	let esalary = this.parentElement.parentElement.children[3].children[0].value;
	
	let param = 'job=edit&empNo=' + empNo + '&email=' + email + '&salary=' + esalary;

	fetch('../empsave.json', {
		method: 'post',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		body: param
	})
		.then(result => result.json())
		.then(data => {
			if (data.retCode == 'OK') {
				let newTr = makeRow(data.retVal);
				oldTr.parentElement.replaceChild(newTr, oldTr);
			} else if (data.retCode == 'NG') {
				alert('처리 실패');
			}
		})
		.catch(console.log());
} // end of updateRow
