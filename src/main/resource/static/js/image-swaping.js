const desireTierImg = document.querySelector('.desired .wrap-image img');
const desireTier = document.querySelector('.desired #destinationTier');
const desireDivision = document.querySelector('.desired #destinationDivision');

const currentTierImg = document.querySelector('.current .wrap-image img');
const currentTier = document.querySelector('.current #currentTier');
const currentDivision = document.querySelector('.current #currentDivision');

const homeIMGURL = `${window.location.protocol}//${window.location.host}/images`;
const resumeText = document.querySelector('.resume-text');

window.addEventListener('load', () => {
    let goldTier = desireTier[3];
    let silverTier = currentTier[2];

    goldTier.selected = true;
    silverTier.selected = true;
    currentDivision[0].selected = true;
    desireDivision[0].selected = true;

    setResumeText();
    disableWrongTiersOrDivisions();
    setImages();
});

desireTier.addEventListener('change', () => {
    setResumeText();
    disableWrongTiersOrDivisions();
    setImages();
});

desireDivision.addEventListener('change', () => {
    setResumeText();
    setImages();
});

currentTier.addEventListener('change', () => {
    setResumeText();
    disableWrongTiersOrDivisions();
    setImages();
});

currentDivision.addEventListener('change', () => {
    setResumeText();
    disableWrongTiersOrDivisions();
    setImages();
});

function setResumeText() {
    resumeText.innerHTML = `${currentTier.value.toLowerCase().capitalize()} ${convertDivision(currentDivision.value)} to
                            ${desireTier.value.toLowerCase().capitalize()} ${convertDivision(desireDivision.value)}`;
}

function setImages() {
    desireTierImg.src = imagePath(desireTier.selectedIndex, desireDivision.selectedIndex);
    currentTierImg.src = imagePath(currentTier.selectedIndex, currentDivision.selectedIndex);
}

function imagePath(tierIndex, divisionIndex) {
    let tier = currentTier[tierIndex].value;
    let division = currentDivision[divisionIndex].value;
    return `${homeIMGURL}/${tier}/${tier}${convertDivision(division)}.png`;
}

function convertDivision(division) {
    switch (division) {
        case 'I':
            return 1;
        case 'II':
            return 2;
        case 'III':
            return 3;
        case 'IV':
            return 4;
    }
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
};

function disableWrongTiersOrDivisions() {
    disableWrongTiers();

    if (isTiersEquals()) {
        disableWrongDivisions();
    }
}

function disableWrongTiers() {
    for (let i = 0; i < currentTier.length; i++) {
        if (whetherCurrentDivisionIsMaximum()) {
            desireTier[i].disabled = i <= currentTier.selectedIndex;
        } else {
            desireTier[i].disabled = i < currentTier.selectedIndex;
        }
    }

    if (maxDivision()) {
        desireTier[currentTier.selectedIndex].selected = true;
        desireDivision[currentDivision.selectedIndex].selected = true;

        currentDivision[currentDivision.selectedIndex].disabled = true;
        currentDivision[currentDivision.selectedIndex + 1].selected = true;
    } else if (isTiersAndDivisionAreEquals()) {
        setSelectedTiersAndDivisions(true);
    } else if (isCurrentTierIsHigherThanDesired()) {
        setSelectedTiersAndDivisions(false);
    }
}

function disableWrongDivisions() {
    for (let i = 0; i < currentDivision.length; i++) {
        desireDivision[i].disabled = i >= currentDivision.selectedIndex;
    }
    desireDivision[currentDivision.selectedIndex - 1].selected = true;
}

function setSelectedTiersAndDivisions(disableDesireTier) {
    if (whetherCurrentDivisionIsMaximum()) {
        desireTier[currentTier.selectedIndex].disabled = disableDesireTier;
        setDesireRankHigherAOneDivisionThanCurrent();
    } else {
        desireTier[currentTier.selectedIndex].selected = true;
        desireDivision[currentDivision.selectedIndex - 1].selected = true;
    }
}

function setDesireRankHigherAOneDivisionThanCurrent() {
    desireTier[currentTier.selectedIndex + 1].selected = true;
    desireDivision[3].selected = true;
}

function maxDivision() {
    return currentTier.selectedIndex === currentTier.length - 1 && currentDivision.selectedIndex === 0;
}

function isCurrentTierIsHigherThanDesired() {
    return currentTier.selectedIndex > desireTier.selectedIndex;
}

function whetherCurrentDivisionIsMaximum() {
    return currentDivision.selectedIndex === 0;
}

function isTiersAndDivisionAreEquals() {
    return isTiersEquals() && isDivisionsEquals();
}

function isTiersEquals() {
    return currentTier.selectedIndex === desireTier.selectedIndex;
}

function isDivisionsEquals() {
    return currentDivision.selectedIndex === desireDivision.selectedIndex;
}