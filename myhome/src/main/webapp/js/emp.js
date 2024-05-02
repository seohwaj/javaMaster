/**
 * emp.js
 */

document.addEventListener("DOMContentLoaded", initForm);

// 화면 로딩 후 처음 실행할 함수
function initForm() {
	// Ajax 호출
	const xhtp = new XMLHttpRequest();
	xhtp.open('get', '../empJson.json');
	xhtp.send();
	xhtp.onload = function() {
		let data = JSON.parse(xhtp.responseText);
		console.log(data);
	}
} // end of initForm

function makeRow() {
	let props = ['empNo', 'empName', 'email', 'salary'];
	let tr = document.createElement('tr');
	props.forEach(prop => {
		let td = document.createElement('td');
		td.innerText = emp[prop];
		tr.appendChild(td);
	});
	return tr;
}