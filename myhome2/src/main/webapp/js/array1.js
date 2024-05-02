/**
 * array1.js
 */
empList.forEach((item, idx) => {
	if(item.first_name.indexOf('b') >= 0 ) {
		console.log(item);
	}
});

let newAry = empList.filter((item, idx, ary) => {
	return (idx + 1) == ary.length;
});

newAry = empList.map((item, idx, ary) => {
	const obj = {
		no: item.id,
		name: item.first_name + " " + item.last_name,
		email: item.email
	}
	return obj;
});

console.log(newAry);

/*
const array1 = [1, 2, 3, 4];
// 0 + 1 + 2 + 3 + 4
const initVal = 0;
const sumWithInit = array1.reduce((acc, curVal) => {
	return acc + curVal;
}, initVal);
console.log(sumWithInit);
*/

const array1 = [1, 7, 2, 9, 3, 4];
const initVal = 0;
const sumWithInit = array1.reduce((acc, curVal) => {
	console.log(`acc => ${acc}, curVal => ${curVal}`);
	return acc > curVal ? curVal : acc;
});
console.log(`최소값: ${sumWithInit}`);

let result = empList.reduce((acc, curVal) => {
	if(curVal.gender == 'Male') {
		acc.push(curVal);
	}
	return acc;
}, []);
console.log(result);

// String.prototype.indexOf('') -> 찾는 값의 인덱스를 반환
// Array.prototype.indexOf('') -> 찾는 값의 인덱스를 반환
console.log("abcde".indexOf('c'));
console.log([1, 2, 3, 4, 5].indexOf(3));

let genderAry = []; // gender를 종류별로 한가지만 담기

empList.reduce((acc, curVal) => {
	if(acc.indexOf(curVal.gender) == -1) {
		acc.push(curVal.gender);
	}
	return acc;
}, genderAry)

console.log(genderAry);

