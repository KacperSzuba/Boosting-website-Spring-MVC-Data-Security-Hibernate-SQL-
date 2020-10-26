const burger = document.querySelector('.burger');

const iconBurger = document.querySelector(".fa-bars");
const iconX = document.querySelector(".fa-times");
const column = document.querySelector("aside");
const header = document.querySelector("header");

const accountDescription = document.querySelector("#account a");
const subMenu = document.querySelector(".sub-menu");
const hideSubMenu = document.querySelector(".back-to-main-menu");

burger.addEventListener('click', function () {
    iconBurger.classList.toggle("show");
    iconX.classList.toggle("show");
    column.classList.toggle("show");
    header.classList.toggle("hide");
});

if (accountDescription != null) {
    accountDescription.addEventListener('click', function () {
        subMenu.classList.add("show-sub-menu");
    });
}

if (hideSubMenu != null) {
    hideSubMenu.addEventListener('click', function () {
        subMenu.classList.remove("show-sub-menu");
    });
}