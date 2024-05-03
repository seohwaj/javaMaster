/**
 * empService.js
 * 목록, 추가, 수정, 삭제 기능 객체
 */

const svc = {
	// 목록
	empList: function(successCall, errorCall) {
		fetch('../empJson.json')
		.then(result => result.json())
		.then(successCall)
		.catch(errorCall);
	},
	// 등록
	addEmp: function(param = {}, successCall, errorCall) {
		fetch('../empsave.json', {
			method: 'post',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			body: 'job=add&name=' + param.name + '&phone=' + param.phone + '&salary=' + param.salary + '&hire=' + param.hire + '&email=' + param.mail
		})
		.then(result => result.json())
		.then(successCall)
		.catch(errorCall)
	},
	// 수정
	editEmp: function(param = {}, successCall, errorCall) {
		fetch('../empsave.json', {
			method: 'post',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			body: 'job=edit&empNo=' + param.empNo + '&email=' + param.mail + '&salary=' + param.salary
		})
		.then(result => result.json())
		.then(successCall)
		.catch(errorCall);
	},
	// 삭제
	delEmp: function(eno, successCall, errorCall) {
		fetch('../empsave.json?job=delete&empNo=' + eno)
		.then(result => result.json())
		.then(successCall)
		.catch(errorCall);
	}
}