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
    subMenuAccount.classList.add("show-sub-menu-for-desktop");
};

let onMouseOutSubmenu = function(){
    timer = setTimeout(function(){
        subMenuAccount.classList.remove("show-sub-menu-for-desktop");
    }, 300);
};

let onMouseOverAccount = function(){
    clearTimeout(timer);
    subMenuAccount.classList.add("show-sub-menu-for-desktop");
};

let onMouseOutAccount = function(){
    timer = setTimeout(function(){
        subMenuAccount.classList.remove("show-sub-menu-for-desktop");
    }, 300);
};

let boostingTimer;

let onMouseOverSubmenuBoosting = function(){
    clearTimeout(boostingTimer);
    subMenuBoosting.classList.add("show-sub-menu-for-desktop");
};

let onMouseOutSubmenuBoosting = function(){
    boostingTimer = setTimeout(function(){
        subMenuBoosting.classList.remove("show-sub-menu-for-desktop");
    }, 300);
};

let onMouseOverBoosting= function(){
    clearTimeout(boostingTimer);
    subMenuBoosting.classList.add("show-sub-menu-for-desktop");
};

let onMouseOutBoosting = function(){
    boostingTimer = setTimeout(function(){
        subMenuBoosting.classList.remove("show-sub-menu-for-desktop");
    }, 300);
};

if(accountDescription != null){
    accountDescription.addEventListener('mouseenter', onMouseOverAccount);

    accountDescription.addEventListener('mouseleave', onMouseOutAccount);
}

if(boostingDescription != null){
    boostingDescription.addEventListener('mouseenter', onMouseOverBoosting);

    boostingDescription.addEventListener('mouseleave', onMouseOutBoosting);
}

if(subMenuAccount != null){
    subMenuAccount.addEventListener('mouseenter', onMouseOverSubmenu);

    subMenuAccount.addEventListener('mouseleave', onMouseOutSubmenu);
}

if(subMenuBoosting != null){
    subMenuBoosting.addEventListener('mouseenter', onMouseOverSubmenuBoosting);

    subMenuBoosting.addEventListener('mouseleave', onMouseOutSubmenuBoosting);
}