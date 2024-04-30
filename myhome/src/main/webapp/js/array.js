/**
 * array.js
 */
const ary = []; // new Array();
ary.push('apple');
ary.push(['banana', 'cherry']);
ary.push({name: "홍길동", age: 20});

console.log(ary);

const fruits = [];
fruits.push({name: "사과", price: 1000});
fruits.push({name: "수박", price: 5000});
fruits.pop();
fruits.unshift({name: "수박", price: 5000});
fruits.shift();
fruits.push({name: "수박", price: 5000});
// [사과, 수박]
fruits.splice(1, 0, {name: "참외", price: 3000}); // 추가
// [사과, 참외, 수박]
fruits.splice(1, 1, {name: "참외", price: 5000}); // 수정
// [사과, 참외, 수박]
fruits.splice(2, 1); // 삭제
// [사과, 참외]


console.log(fruits);