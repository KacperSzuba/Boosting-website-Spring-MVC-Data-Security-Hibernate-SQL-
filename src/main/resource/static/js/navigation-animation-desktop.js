const navigation = document.querySelector(".navigation");
const link = document.querySelectorAll(".header-aside nav ul li a");
const border = document.querySelectorAll(".border div");

const screenWidth = window.screen.width;

window.addEventListener('scroll', function () {
    if ((document.body.scrollTop > 10 || document.documentElement.scrollTop > 10) && screenWidth >= 1024) {
        navigation.classList.add("nav-animation");

        for (let i = 0; i < link.length; i++) {
            link[i].classList.add("color");
            link[i].classList.add("transparent");
            border[i].classList.add("white")
        }
    } else {
        navigation.classList.remove("nav-animation");

        for (let i = 0; i < link.length; i++) {
            link[i].classList.remove("color");
            link[i].classList.remove("transparent");
            border[i].classList.remove("white")
        }
    }
});