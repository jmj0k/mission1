const home_btn = document.querySelector('#home-btn');

const get_my_location_btn = document.querySelector('#get-my-location-btn');
const wifi_info_btn = document.querySelector('#get-Wifi-info-btn');
const lat_input = document.querySelector('#lat-input');
const lnt_input = document.querySelector('#lnt-input');
const draw_map_btn = document.querySelector('.draw-map-btn');

window.addEventListener('load', (e) => { 
    home_btn.className += ' active';
    const params = getUrlParams();
    if (Object.keys(params).length !== 0) {
		lat_input.value = params.lat;
		lnt_input.value = params.lnt;
	}
});
window.addEventListener('onbeforeunload', (e) => {
	home_btn.classList.remove('active');
})

get_my_location_btn.addEventListener('click', (e) => {
	console.log("test" + navigator.geolocation);
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(success, faild);
	} else {
		alert ("이 브라우저에서는 위치를 구할 수 없습니다")
	}
});

function success(pos) {
	console.log(pos);
	let lat = pos.coords.latitude;
	let lnt = pos.coords.longitude;
	lat_input.value = lat;
	lnt_input.value = lnt;
}

function faild (e) {
	if (e.code == 1) {
		alert("위치 정보 공유 권한을 제공해주셔야 위치를 가져올 수 있습니다.");
	} else if (e.code == 2) {
		alert("브라우저가 위치를 가져올 수 없습니다.");
	} else if (e.code == 3) {
		alert("타임아웃");
	}
	console.log(e.code + e.message);
}

wifi_info_btn.addEventListener('click', (e) => {
	let lat_val = lat_input.value;
	let lnt_val = lnt_input.value;
	if (lat_val == "" || lnt_val == "") {
		alert("위치를 입력한 후에 조회해주세요! 현재 값이 비어있습니다.");
	} else if (isNaN(lat_val) || isNaN(lnt_val)) {
		alert("값이 바르지 않습니다. 다시 입력해주세요.");
	} else window.location.assign('/Mission/wifi/get_nearby_wifi?lat=' + lat_val + "&lnt=" + lnt_val);
});
/*
draw_map_btn.addEventListener('click', (e) => {
	let lat_val = lat_input.value;
	let lnt_val = lnt_input.value;
	window.open('/Mission/wifi/address?lat=' + lat_val + "&lnt=" + lnt_val, '_blank'); 
})
*/

function getUrlParams() {     
   	let params = {};  
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, 
    	function(str, key, value) { 
        	params[key] = value; 
        }
    );     
    return params; 
}
