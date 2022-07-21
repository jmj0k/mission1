const history_btn = document.querySelector('#history-btn');
const delete_btns = document.querySelectorAll('.delete-btn');
const search_idx_btns = document.querySelectorAll('.search-idx-btn');

window.addEventListener('load', (e) => { 
    history_btn.className += ' active';
});
window.addEventListener('onbeforeunload', (e) => {
	history_btn.classList.remove('active');
})

delete_btns.forEach((delete_btn, i) => {
	delete_btn.addEventListener('click', (e) => {
		let answer = confirm('기록을 정말 삭제하시겠습니까?');
		if (answer) {
			let idx = delete_btn.getAttribute('data-idx');
			window.location.assign('/Mission/wifi/delete_history?idx=' + idx);
		} 
	});
});
search_idx_btns.forEach((search_idx_btn, i) => {
	search_idx_btn.addEventListener('click', (e) => {
		let answer = confirm('해당 위치로 다시 검색하시겠습니까?');
		if (answer) {
			let lat = search_idx_btn.getAttribute('data-lat');
			let lnt = search_idx_btn.getAttribute('data-lnt');
			
			window.location.assign('/Mission/wifi/get_nearby_wifi?lat=' + lat + "&lnt=" + lnt);
		}
  });

});