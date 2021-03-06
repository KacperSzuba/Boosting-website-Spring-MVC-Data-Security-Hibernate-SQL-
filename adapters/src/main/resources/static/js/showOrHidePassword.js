const input = document.querySelectorAll('input.password');
const spanList = document.querySelectorAll('span.password');

for (let i = 0; i < spanList.length; i++) {
    spanList[i].addEventListener("click", function () {
    spanList[i].classList.toggle('fa-eye-slash');
    spanList[i].classList.toggle('fa-eye');

    if (this.classList.contains('fa-eye-slash')) {
        input[i].type = 'password';
    } else {
        input[i].type = 'text';
    }

    });
}