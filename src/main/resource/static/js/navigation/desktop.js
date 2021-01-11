const navigation = document.querySelector(".navigation");
const linkLabel = document.querySelectorAll(".main-menu-link");
const screenWidth = window.screen.width;

window.addEventListener('scroll', function () {
    if (document.documentElement.scrollTop > 5 && screenWidth >= 1024) {
        navigation.classList.add("nav-animation");
        setPropertyToPseudoElements(linkLabel, "after", "background-color", "white");
        setPropertyToElements(linkLabel, "color", "white");
    } else {
        navigation.classList.remove("nav-animation");
        setPropertyToPseudoElements(linkLabel, "after", "background-color", "#14a5eb");
    }
});

if(screenWidth >= 1024) {
    let timer;

    let onMouseOverSubmenu = function () {
        clearTimeout(timer);
        subMenuAccount.classList.add("show-sub-menu-for-desktop");
    };

    let onMouseOutSubmenu = function () {
        timer = setTimeout(function () {
            subMenuAccount.classList.remove("show-sub-menu-for-desktop");
        }, 300);
    };

    let onMouseOverAccount = function () {
        clearTimeout(timer);
        subMenuAccount.classList.add("show-sub-menu-for-desktop");
    };

    let onMouseOutAccount = function () {
        timer = setTimeout(function () {
            subMenuAccount.classList.remove("show-sub-menu-for-desktop");
        }, 300);
    };

    if (accountLabel != null) {
        accountLabel.addEventListener('mouseenter', onMouseOverAccount);
        accountLabel.addEventListener('mouseleave', onMouseOutAccount);
    }

    if (subMenuAccount != null) {
        subMenuAccount.addEventListener('mouseenter', onMouseOverSubmenu);
        subMenuAccount.addEventListener('mouseleave', onMouseOutSubmenu);
    }
}

if(screenWidth >= 1024 && subMenuBoosting != null) {
    let timer;

    let onMouseOverSubmenuBoosting = function () {
        clearTimeout(timer);
        subMenuBoosting.classList.add("show-sub-menu-for-desktop");
    };

    let onMouseOutSubmenuBoosting = function () {
        timer = setTimeout(function () {
            subMenuBoosting.classList.remove("show-sub-menu-for-desktop");
        }, 300);
    };

    let onMouseOverBoosting = function () {
        clearTimeout(timer);
        subMenuBoosting.classList.add("show-sub-menu-for-desktop");
    };

    let onMouseOutBoosting = function () {
        timer = setTimeout(function () {
            subMenuBoosting.classList.remove("show-sub-menu-for-desktop");
        }, 300);
    };

    if (boostingLabel != null) {
        boostingLabel.addEventListener('mouseenter', onMouseOverBoosting);
        boostingLabel.addEventListener('mouseleave', onMouseOutBoosting);
    }

    if (subMenuBoosting != null) {
        subMenuBoosting.addEventListener('mouseenter', onMouseOverSubmenuBoosting);
        subMenuBoosting.addEventListener('mouseleave', onMouseOutSubmenuBoosting);
    }
}

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

function setPropertyToElements(elements, property, value){
    for (let i = 0; i < elements.length; i++) {
        elements[i].style.color = value;
    }
}