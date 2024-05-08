/**
 * calendar.js
 */
document.addEventListener('DOMContentLoaded', initForm);

function initForm(){
	let show = document.querySelector('#show');
	show.appendChild(svc.makeTable());
	document.querySelector('#show>table').appendChild(svc.makeHeader2());
	document.querySelector('#show>table').appendChild(svc.makeBody(6));
}

const svc = {
	makeTable: function() {
		let tbl = document.createElement('table');
		tbl.setAttribute('border', "2");
		
		return tbl;
	},
	makeHeader: function() {
		const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'];
		let thd = document.createElement('thead');
		let tr = document.createElement('tr');
		
		days.forEach((day) => {
			let th = document.createElement('th');
			th.innerText = day;
			tr.appendChild(th);			
		})
		
		thd.appendChild(tr);
		
		return thd;
	},
	makeHeader2: function() {
		const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'];
		let thd = document.createElement('thead'); 
		let tr = days.reduce((acc, curVal) => {
			let th = document.createElement('th');
			th.innerText = curVal;
			acc.appendChild(th);
			return acc;
		}, document.createElement('tr'));
		thd.appendChild(tr);
		return thd;
	},
	makeBody: function(month = 4) {
		let tbd = document.createElement('tbody');
		let tr = document.createElement('tr');
		
		let spaces = this.getFirstDate(month); // getFirstDate(month) -> 1일의 위치
		for(let i = 0; i < spaces; i++) {
			let td = document.createElement('td');
			td.innerText = ' ';
			tr.appendChild(td);
		}
		
		for(let d = 1; d <= this.getLastDate(month); d++) { // getLastDate(month) -> 월의 마지막 날을 반환
			td = document.createElement('td');
			td.innerText = d;
			tr.appendChild(td);
			
			if((d + spaces) % 7 == 0) {
				tbd.appendChild(tr);
				tr = document.createElement('tr');
			}
		}
		tbd.appendChild(tr);			
		
		return tbd;
	},
	getFirstDate(month) {
		let date = new Date(2024, month - 1, 1);
		return date.getDay();
	},
	getLastDate(month) {
		let date = new Date(2024, month, 0);
		return date.getDate();
	}
}