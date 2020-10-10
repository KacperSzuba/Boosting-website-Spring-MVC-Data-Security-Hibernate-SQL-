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
    disableWrongTiersOrDivisions();
});

desireTier.addEventListener('change', () => {
    desireTierImg.src = imagePath(desireTier.value, desireDivision.value);
    setResumeText();
    disableWrongTiersOrDivisions();
});

desireDivision.addEventListener('change', () => {
    desireTierImg.src = imagePath(desireTier.value, desireDivision.value);
    setResumeText();
});

currentTier.addEventListener('change', () => {
    currentTierImg.src = imagePath(currentTier.value, currentDivision.value);
    setResumeText();
    disableWrongTiersOrDivisions();
});

currentDivision.addEventListener('change', () => {
    currentTierImg.src = imagePath(currentTier.value, currentDivision.value);
    setResumeText();
    disableWrongTiersOrDivisions();
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

function disableWrongTiersOrDivisions() {
    disableWrongTiers();

    if(isTiersEquals()){
        disableWrongDivisions();
    }
}

function disableWrongTiers() {
    for(let i = 0; i < currentTier.length; i++){
        desireTier[i].disabled = i < currentTier.selectedIndex;
    }

    if((isTiersAndDivisonAreEquals() || isDivisionEquals1())){
        desireTier[currentTier.selectedIndex].disabled = true;
        if(maxDivision()){
            currentDivision[0].disabled = true;
            currentDivision[1].selected = true;

            desireTier[currentTier.selectedIndex + 1].selected = true;
            desireDivision[3].selected = true;
        }
        else {
            desireTier[currentTier.selectedIndex + 1].selected = true;
            desireDivision[3].selected = true;
            desireTierImg.src = imagePath(desireTier[currentTier.selectedIndex + 1].value, desireDivision[3].value);
        }
    }
    else if(isCurrentTierIsHigherThanDesired()){
        desireDivision[desireDivision.selectedIndex + 1].selected = true;
        desireTierImg.src = imagePath(desireTier[desireTier.selectedIndex].value, desireDivision[desireDivision.selectedIndex + 1].value);
        desireDivision[desireDivision.selectedIndex].disabled = true;
    }
}

function disableWrongDivisions() {
    for(let i = 0; i < currentDivision.length; i++){
        desireDivision[i].disabled = i >= currentDivision.selectedIndex;
        desireDivision[currentDivision.selectedIndex - 1].selected = true;
    }
}

function maxDivision() {
    return currentTier.selectedIndex === currentTier.length - 1 && currentDivision.selectedIndex === 0;
}

function isCurrentTierIsHigherThanDesired() {
    return currentTier.selectedIndex > desireTier.selectedIndex;
}

function isDivisionEquals1() {
    return currentDivision.value === currentDivision[0].value;
}

function isTiersAndDivisonAreEquals() {
    return isTiersEquals() && isDivisionsEquals();
}

function isTiersEquals() {
    return currentTier.value === desireTier.value;
}

function isDivisionsEquals() {
    return splitDivision(currentDivision.value) === splitDivision(desireDivision.value);
}