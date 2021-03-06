const burger = document.querySelector('.burger');

const iconBurger = document.querySelector(".fa-bars");
const iconX = document.querySelector(".fa-times");
const column = document.querySelector(".header-aside");
const header = document.querySelector(".order-header");

const accountLabel = document.querySelector("#account .arrow");
const boostingLabel = document.querySelector("#boosting .arrow");
const subMenuAccount = document.querySelector("#account .sub-menu-wrap");
const subMenuBoosting = document.querySelector("#boosting .sub-menu-wrap");
const backToMainMenu = document.querySelectorAll(".back-to-main-menu");

burger.addEventListener('click', function () {
    iconBurger.classList.toggle("show");
    iconX.classList.toggle("show");
    column.classList.toggle("show");
    header.classList.toggle("hide");

    hideSubMenu(subMenuAccount);
    hideSubMenu(subMenuBoosting);
});

if (backToMainMenu != null && window.screen.width < 1024) {
    backToMainMenu.forEach(subMenu => subMenu.addEventListener('click', function () {
        subMenuAccount.classList.remove("show-sub-menu");
        subMenuBoosting.classList.remove("show-sub-menu");
    }));
}

if (accountLabel != null && window.screen.width < 1024) {
    accountLabel.addEventListener('click', function () {
        subMenuAccount.classList.add("show-sub-menu");
    });
}

if (boostingLabel != null && window.screen.width < 1024) {
    boostingLabel.addEventListener('click', function () {
        subMenuBoosting.classList.add("show-sub-menu");
    });
}

function hideSubMenu(subMenu){
    if(subMenu != null){
        subMenu.classList.remove("show-sub-menu");
    }
}