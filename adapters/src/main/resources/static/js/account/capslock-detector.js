let passwordInputs = document.querySelectorAll("input.password");
let capslockDetectedMessages = document.querySelectorAll(".capslockDetectedMessage");

passwordInputs.forEach((password) => {
    console.log(password.onfocus);
    capslockDetectedMessages.forEach((message) => {
        password.addEventListener("keyup", function(event) {
            if (event.getModifierState("CapsLock") && event.getModifierState("click")) {
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
            if (event.getModifierState("CapsLock") && event.getModifierState("click")) {
                message.style.display = "block";
            } else {
                message.style.display = "none"
            }
        });
    });
});