const get_wifi_info_btn = document.querySelector('#get-wifi-info-btn');
const pop_up = document.querySelector('.pop-up');


pop_up.addEventListener('click', (e) => {
	window.location.replace('/Mission/wifi/main');
});

function deleteMenu(element) {
	element.remove();
}

deleteMenu(get_wifi_info_btn);
