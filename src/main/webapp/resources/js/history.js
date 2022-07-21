const history_btn = document.querySelector('#history-btn');

window.addEventListener('load', (e) => { 
    history_btn.className += ' active';
});
window.addEventListener('onbeforeunload', (e) => {
	history_btn.classList.remove('active');
})