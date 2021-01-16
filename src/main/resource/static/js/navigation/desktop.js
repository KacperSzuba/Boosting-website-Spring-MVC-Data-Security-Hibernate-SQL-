const navigation = document.querySelector(".navigation");
const linkLabel = document.querySelectorAll(".main-menu-link");
const accountDesktopLabel = document.querySelector("#account .account-wrap div");
const boostingDesktopLabel = document.querySelector("#boosting .account-wrap div");
const screenWidth = window.screen.width;

window.addEventListener('scroll', function () {
    if (document.documentElement.scrollTop > 5 && screenWidth >= 1024) {
        navigation.classList.add("nav-animation");
        setPropertyToPseudoElements(linkLabel, "after", "background-color", "white");
        addClassToElements(linkLabel, "white");
    } else {
        navigation.classList.remove("nav-animation");
        setPropertyToPseudoElements(linkLabel, "after", "background-color", "#14a5eb");
        removeClassFromElements(linkLabel, "white");
    }
});

function subMenu(subMenu, label) {
    if (screenWidth >= 1024) {
        let timer;

        let onMouseOverSubmenu = function () {
            clearTimeout(timer);
            subMenu.classList.add("show-sub-menu-for-desktop");
        };

        let onMouseOutSubmenu = function () {
            timer = setTimeout(function () {
                subMenu.classList.remove("show-sub-menu-for-desktop");
            }, 300);
        };

        let onMouseOverAccount = function () {
            clearTimeout(timer);
            subMenu.classList.add("show-sub-menu-for-desktop");
        };

        let onMouseOutAccount = function () {
            timer = setTimeout(function () {
                subMenu.classList.remove("show-sub-menu-for-desktop");
            }, 300);
        };

        if (label != null) {
            label.addEventListener('mouseenter', onMouseOverAccount);
            label.addEventListener('mouseleave', onMouseOutAccount);
        }

        if (subMenu != null) {
            subMenu.addEventListener('mouseenter', onMouseOverSubmenu);
            subMenu.addEventListener('mouseleave', onMouseOutSubmenu);
        }
    }
}

subMenu(subMenuAccount, accountDesktopLabel);
subMenu(subMenuBoosting, boostingDesktopLabel);

HTMLElement.prototype.pseudoStyle = function(element, prop, value){
    let _sheetId = "pseudoStyles";
    let _head = document.head || document.getElementsByTagName('head')[0];
    let _sheet = document.getElementById(_sheetId) || document.createElement('style');
    _sheet.id = _sheetId;
    let className = "main-menu-link";

    _sheet.innerHTML = `.${className}:${element}{${prop}:${value}}`;
    _head.appendChild(_sheet);
    return this;
};

function setPropertyToPseudoElements(elements, pseudoElement, property, value){
    for (let i = 0; i < elements.length; i++) {
        elements[i].pseudoStyle(pseudoElement, property, value);
    }
}

function addClassToElements(elements, value){
    for (let i = 0; i < elements.length; i++) {
        elements[i].classList.add(value);
    }
}

function removeClassFromElements(elements, value){
    for (let i = 0; i < elements.length; i++) {
        elements[i].classList.remove(value);
    }
}