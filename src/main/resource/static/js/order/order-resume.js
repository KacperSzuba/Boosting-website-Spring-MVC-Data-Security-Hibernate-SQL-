const orderResume = document.querySelector(".division section.price")

let defaultOrderResumeScrollTop = orderResume.getBoundingClientRect().top + window.scrollY;
let sticked = false;

if(window)
window.addEventListener("scroll", function (){
    if(document.documentElement.scrollTop > orderResume.getBoundingClientRect().top+window.scrollY && !sticked){
        orderResume.classList.add("stickToTopOrderResume");
        sticked = true;
    }
    else if(document.documentElement.scrollTop < defaultOrderResumeScrollTop && sticked){
        orderResume.classList.remove("stickToTopOrderResume");
        sticked = false;
    }
});