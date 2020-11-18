const startedLeagueImg = document.querySelector('.started-tier img');
const desireLeagueImg = document.querySelector('.desire-tier img');
const startedLeague = document.querySelector('.started-tier .tier-division');
const desireLeague = document.querySelector('.desire-tier .tier-division');

window.addEventListener('load', () => {
    let startedTier = startedLeague.innerText.split(' ')[0];
    let startedDivision = startedLeague.innerText.split(' ')[1];

    let desireTier = desireLeague.innerText.split(' ')[0];
    let desireDivision = desireLeague.innerText.split(' ')[1];

    startedLeagueImg.src = imagePath(startedTier, startedDivision);
    desireLeagueImg.src = imagePath(desireTier, desireDivision);
});

function imagePath(tier, division) {
    return `${homeIMGURL}/${tier}/${tier}${convertDivision(division)}.png`;
}