const loginPageURL = window.location.href;
const errorBanner = document.querySelector('#login-error');

window.addEventListener('load', () => {
    if(loginPageURL.includes('?error=true')){
        errorBanner.classList.add('show-login-error');
    }
    else{
        errorBanner.classList.remove('show-login-error');
    }
});