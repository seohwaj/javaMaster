/**
 * jsondata.js
 */

 const employees = `[{"id":1,"first_name":"Mable","last_name":"O'Nowlan","email":"monowlan0@intel.com","gender":"Agender","salary":4859},
{"id":2,"first_name":"Xenos","last_name":"Kenworthey","email":"xkenworthey1@multiply.com","gender":"Male","salary":4199},
{"id":3,"first_name":"Elias","last_name":"MacCumeskey","email":"emaccumeskey2@hp.com","gender":"Male","salary":4135},
{"id":4,"first_name":"Ealasaid","last_name":"Guye","email":"eguye3@friendfeed.com","gender":"Genderfluid","salary":4648},
{"id":5,"first_name":"Dame","last_name":"Cundy","email":"dcundy4@joomla.org","gender":"Male","salary":4340},
{"id":6,"first_name":"Margery","last_name":"Kik","email":"mkik5@ihg.com","gender":"Female","salary":3557},
{"id":7,"first_name":"Amii","last_name":"Hallawell","email":"ahallawell6@hexun.com","gender":"Female","salary":3042},
{"id":8,"first_name":"Nikolia","last_name":"Percival","email":"npercival7@scribd.com","gender":"Female","salary":3116},
{"id":9,"first_name":"Teresita","last_name":"Vassie","email":"tvassie8@washingtonpost.com","gender":"Female","salary":3137},
{"id":10,"first_name":"Whitby","last_name":"Piercey","email":"wpiercey9@sfgate.com","gender":"Male","salary":3874},
{"id":11,"first_name":"Alf","last_name":"Ostick","email":"aosticka@vk.com","gender":"Male","salary":4792},
{"id":12,"first_name":"Wallache","last_name":"Dionisii","email":"wdionisiib@shop-pro.jp","gender":"Male","salary":4397},
{"id":13,"first_name":"Berte","last_name":"Yes","email":"byesc@indiatimes.com","gender":"Female","salary":3026},
{"id":14,"first_name":"Des","last_name":"Cleyne","email":"dcleyned@macromedia.com","gender":"Male","salary":4021},
{"id":15,"first_name":"Torin","last_name":"Brailsford","email":"tbrailsforde@accuweather.com","gender":"Male","salary":4868},
{"id":16,"first_name":"Lewes","last_name":"Bonin","email":"lboninf@gnu.org","gender":"Male","salary":4446},
{"id":17,"first_name":"Allis","last_name":"Glaisner","email":"aglaisnerg@csmonitor.com","gender":"Female","salary":4865},
{"id":18,"first_name":"Kienan","last_name":"Tittletross","email":"ktittletrossh@yandex.ru","gender":"Male","salary":4359},
{"id":19,"first_name":"Karylin","last_name":"Verey","email":"kvereyi@reverbnation.com","gender":"Female","salary":4174},
{"id":20,"first_name":"Marketa","last_name":"Vankov","email":"mvankovj@rambler.ru","gender":"Female","salary":4860}]`;

// console.log(employees);
const empList = JSON.parse(employees); // 문자열 -> 객체
// console.log(empList);