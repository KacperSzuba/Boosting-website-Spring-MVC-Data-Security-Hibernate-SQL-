const orderResume = document.querySelector(".division section.price")
const accountInfo = document.querySelector(".account-info");

let defaultOrderResumeScrollTop = orderResume.getBoundingClientRect().top + window.scrollY;
let accountInfoScrollBottom = accountInfo.getBoundingClientRect().bottom + window.scrollY;

let sticked = false;

//TODO Gdy dolna krawedź orderResume przekroczy dolną krawędź accountInfo to wtedy panel orderResume powinien przestać być fixed i zrównać się krawędziami.
if(window.screen.width >= 1024){
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
}