/**
 * array2.js
 */
let genderAry = []; // gender를 종류별로 한가지만 담기

empList.reduce((acc, curVal) => {
	if(acc.indexOf(curVal.gender) == -1) {
		acc.push(curVal.gender);
	}
	return acc;
}, genderAry);

let genderList = document.querySelector('#genderList');

genderAry.forEach(gender => {
	let opt = document.createElement('option');
	opt.innerText = gender;
	genderList.appendChild(opt);
});

// 함수(배열)
function makeList(ary = []) {
	let props = ['id', 'first_name', 'email', 'salary'];
	
	ary.forEach(emp => {
		let tr = document.createElement('tr');
	
		props.forEach(prop => {
			let td = document.createElement('td');
			td.innerText = emp[prop];
			tr.appendChild(td);
		});
		document.querySelector('#show tbody').appendChild(tr);
	});
}

genderList.addEventListener('change', function() {
	let gender = document.querySelector('select option:checked').innerText;
	let ary = [];
	empList.forEach(emp => {
		if(emp.gender == gender) {
			ary.push(emp);
		}
	});
	makeList(ary);
});

