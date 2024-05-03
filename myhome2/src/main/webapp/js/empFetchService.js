/**
 * empFetchService.js
 * Ajax 기능을 fetch 함수 활용
 * empSvc 객체에 기능을 구현, 메소드를 호출
 */

document.addEventListener("DOMContentLoaded", initForm);

function initForm() {
	// Ajax 호출, 목록 출력
	svc.empList(result => {
		result.forEach(emp => {
			let tr = makeRow(emp);
			document.querySelector('#elist').appendChild(tr);
		}) // end of forEach
	}, // successCall
	err => console.log(err) // errorCall
	);
		
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
	
	svc.delEmp(eno,
		data => {
			if(data.retCode == 'OK') {
				tr.remove();
			}
		},
		err => console.log(err)
	);
} // end of deleteRow

function addRow() {
	// DB insert & 화면 출력
	// 사원이름(ename), 연락처(phone), 급여(salary), 입사일자(hire), 이메일(email)
	let paramObj = {
		job: 'add',
		name: document.querySelector('#ename').value,
		phone: document.querySelector('#ephone').value,
		salary: document.querySelector('#esalary').value,
		hire: document.querySelector('#ehire').value,
		mail: document.querySelector('#email').value
	}
	
	svc.addEmp(paramObj,
		data => {
			if(data.retCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#elist').appendChild(tr);
			}
		},
		err => console.log(err)
	);
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
	
	let empNo = this.parentElement.parentElement.dataset.no;
	let mail = this.parentElement.parentElement.children[2].children[0].value;
	let salary = this.parentElement.parentElement.children[3].children[0].value;
	
	let paramObj = {
		empNo,
		mail, 
		salary  
	}
	
	svc.editEmp(paramObj,
		data => {
			if (data.retCode == 'OK') {
				let newTr = makeRow(data.retVal);
				oldTr.parentElement.replaceChild(newTr, oldTr);
			}
		}, // successCall
		err => console.log(err) // errorCall
	);
} // end of updateRow
