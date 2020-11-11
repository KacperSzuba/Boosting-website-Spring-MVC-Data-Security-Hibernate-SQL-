let loginNotification = document.querySelector("#login-notification");
let loginButton = document.querySelector("#login-button");
let closeButton = document.querySelector('.close-button');

if(loginButton != null) {
    loginButton.addEventListener('click', function () {
        localStorage.setItem('login-notification', 'login-notification');
    });
}

if(localStorage.getItem('login-notification')){
    let timer;

    let onMouseOutAccount = function () {
        timer = setTimeout(function () {
            loginNotification.classList.remove("show");
        }, 10000);
    };

    loginNotification.classList.add('show');

    onMouseOutAccount();

    localStorage.removeItem('local-notification');
}
closeButton.addEventListener('click', function () {
    loginNotification.classList.remove("show");
});
