/**
 * fruit.js
 */
console.log(document.querySelector('button'));
document.querySelector('#addBtn').addEventListener('click', function(){
    // 요소 생성(createElement)
    // 자식요소(appendChild)
    let li = document.createElement('li');
    let fruit = document.querySelector('input').value;
    let price = document.querySelector('input:nth-of-type(2)').value;

    li.innerText = fruit + '(' + price + ')';
    
    // 삭제버튼
    let btn = document.createElement('button');
    btn.innerText = '삭제';
    li.appendChild(btn);
    btn.addEventListener('click', function(){
		console.log(this); // <button>삭제</button>
		this.parentElement.remove();
	});
    
    let ul = document.querySelector('ul');
    ul.appendChild(li);
});