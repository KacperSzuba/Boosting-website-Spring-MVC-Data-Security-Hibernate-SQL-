let passwordInputs = document.querySelectorAll(".password");
let capslockDetectedMessages = document.querySelectorAll(".capslockDetectedMessage");

passwordInputs.forEach((password) => {
    capslockDetectedMessages.forEach((message) => {
        password.addEventListener("keyup", function(event) {
            if (event.getModifierState("CapsLock")) {
                message.style.display = "block";
            } else {
                message.style.display = "none"
            }
        });
    });
});

passwordInputs.forEach((password) => {
    capslockDetectedMessages.forEach((message) => {
        password.addEventListener("click", function(event) {
            if (event.getModifierState("CapsLock")) {
                message.style.display = "block";
            } else {
                message.style.display = "none"
            }
        });
    });
});