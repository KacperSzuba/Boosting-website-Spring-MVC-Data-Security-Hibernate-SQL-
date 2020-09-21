const desireTierImg = document.querySelector('.desired .wrap-image img');
const desireTier = document.querySelector('.desired #destinationTier');
const desireDivision = document.querySelector('.desired #destinationDivision');

const currentTierImg = document.querySelector('.current .wrap-image img');
const currentTier = document.querySelector('.current #currentTier');
const currentDivision = document.querySelector('.current #currentDivision');

const homeIMGURL = "http://localhost:8091/images";
const resumeText = document.querySelector('.resume-text');

window.addEventListener('load', () => {
    let goldTier = desireTier[3];
    let silverTier = currentTier[2];

    goldTier.selected = true;
    silverTier.selected = true;

    desireTierImg.src = imagePath(goldTier.value, desireDivision.value);
    currentTierImg.src = imagePath(silverTier.value, currentDivision.value);
    setResumeText();
});

desireTier.addEventListener('change', () => {
    desireTierImg.src = imagePath(desireTier.value, desireDivision.value);
    setResumeText();
});

desireDivision.addEventListener('change', () => {
    desireTierImg.src = imagePath(desireTier.value, desireDivision.value);
    setResumeText();
});

currentTier.addEventListener('change', () => {
    currentTierImg.src = imagePath(currentTier.value, currentDivision.value);
    setResumeText();
});

currentDivision.addEventListener('change', () => {
    currentTierImg.src = imagePath(currentTier.value, currentDivision.value);
    setResumeText();
});

function setResumeText() {
    resumeText.innerHTML = `${currentTier.value.toLowerCase().capitalize()} ${splitDivision(currentDivision.value)} to
                            ${desireTier.value.toLowerCase().capitalize()} ${splitDivision(desireDivision.value)}`;
}

function imagePath(tier, division) {
    return `${homeIMGURL}/${tier}/${tier}${splitDivision(division)}.png`;
}

function splitDivision(division) {
    return division.split('_')[1];
}

String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
};