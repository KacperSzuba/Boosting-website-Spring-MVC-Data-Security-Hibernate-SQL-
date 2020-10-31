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
            if(border[i] !== undefined){
                border[i].classList.add("white")
            }
        }
    } else {
        navigation.classList.remove("nav-animation");

        for (let i = 0; i < link.length; i++) {
            link[i].classList.remove("color");
            link[i].classList.remove("transparent");
            if(border[i] !== undefined) {
                border[i].classList.remove("white")
            }
        }
    }
});

let timer;

let onMouseOverSubmenu = function(){
    clearTimeout(timer);
    subMenu.classList.add("show-sub-menu-for-desktop");
};

let onMouseOutSubmenu = function(){
    timer = setTimeout(function(){
        subMenu.classList.remove("show-sub-menu-for-desktop");
    }, 300);
};

let onMouseOverAccount = function(){
    clearTimeout(timer);
    subMenu.classList.add("show-sub-menu-for-desktop");
};

let onMouseOutAccount = function(){
    timer = setTimeout(function(){
        subMenu.classList.remove("show-sub-menu-for-desktop");
    }, 300);
};

if(accountDescription != null){
    accountDescription.addEventListener('mouseenter', onMouseOverAccount);

    accountDescription.addEventListener('mouseleave', onMouseOutAccount);
}

if(subMenu != null){
    subMenu.addEventListener('mouseenter', onMouseOverSubmenu);
    
    subMenu.addEventListener('mouseleave', onMouseOutSubmenu);
}