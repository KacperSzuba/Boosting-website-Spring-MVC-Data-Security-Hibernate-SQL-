const desireTierImg = document.querySelector('.desired .wrap-image img');
const desireTier = document.querySelector('.desired #destinationTier');
const desireDivision = document.querySelector('.desired #destinationDivision');

const currentTierImg = document.querySelector('.current .wrap-image img');
const currentTier = document.querySelector('.current #currentTier');
const currentDivision = document.querySelector('.current #currentDivision');

const homeIMGURL = `${window.location.protocol}//${window.location.host}/images`;

window.addEventListener('load', () => {
    let goldTier = desireTier[3];
    let silverTier = currentTier[2];

    goldTier.selected = true;
    silverTier.selected = true;
    currentDivision[0].selected = true;
    desireDivision[0].selected = true;

    disableWrongTiersOrDivisions();
    setImages();
});

desireTier.addEventListener('change', () => {
    disableWrongTiersOrDivisions();
    setImages();
});

desireDivision.addEventListener('change', () => {
    setImages();
});

currentTier.addEventListener('change', () => {
    disableWrongTiersOrDivisions();
    setImages();
});

currentDivision.addEventListener('change', () => {
    disableWrongTiersOrDivisions();
    setImages();
});

function setImages() {
    console.log(desireDivision.options[desireDivision.selectedIndex].text)
    desireTierImg.src = imagePath(desireTier.selectedIndex, desireDivision.options[desireDivision.selectedIndex].text);
    currentTierImg.src = imagePath(currentTier.selectedIndex, currentDivision.options[currentDivision.selectedIndex].text);
}

function imagePath(tierIndex, division) {
    let tier = currentTier[tierIndex].value;
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