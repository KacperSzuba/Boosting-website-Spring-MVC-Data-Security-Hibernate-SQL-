const burger = document.querySelector('.burger');

const iconBurger = document.querySelector(".fa-bars");
const iconX = document.querySelector(".fa-times");
const column = document.querySelector(".header-aside");
const header = document.querySelector(".order-header");

const accountDescription = document.querySelector("#account a");
const boostingDescription = document.querySelector("#boosting a");
const subMenuAccount = document.querySelector("#account .sub-menu-wrap");
const subMenuBoosting = document.querySelector("#boosting .sub-menu-wrap");
const hideSubMenu = document.querySelectorAll(".back-to-main-menu");

burger.addEventListener('click', function () {
    iconBurger.classList.toggle("show");
    iconX.classList.toggle("show");
    column.classList.toggle("show");
    header.classList.toggle("hide");
    
    if(subMenuAccount != null){
        subMenuAccount.classList.remove("show-sub-menu");
    }

    if(subMenuBoosting != null){
        subMenuBoosting.classList.remove("show-sub-menu");
    }
});

if (accountDescription != null && window.screen.width < 1024) {
    accountDescription.addEventListener('click', function () {
        subMenuAccount.classList.add("show-sub-menu");
    });
}

if (boostingDescription != null && window.screen.width < 1024) {
    boostingDescription.addEventListener('click', function () {
        subMenuBoosting.classList.add("show-sub-menu");
    });
}

if (hideSubMenu != null && window.screen.width < 1024) {
    hideSubMenu.forEach(subMenu => subMenu.addEventListener('click', function () {
        console.log(subMenu);
        subMenuAccount.classList.remove("show-sub-menu");
        subMenuBoosting.classList.remove("show-sub-menu");
    }));
}