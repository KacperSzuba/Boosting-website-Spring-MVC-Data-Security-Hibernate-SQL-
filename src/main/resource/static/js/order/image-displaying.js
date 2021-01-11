const startedLeagueImg = document.querySelector('.started-tier img');
const currentLeagueImg = document.querySelector('.current-tier img');
const desireLeagueImg = document.querySelector('.desire-tier img');
const startedLeague = document.querySelector('.started-tier .tier-division');
const currentLeague = document.querySelector('.current-tier .tier-division');
const desireLeague = document.querySelector('.desire-tier .tier-division');

window.addEventListener('load', () => {
    prepareImage(startedLeague, startedLeagueImg);
    prepareImage(currentLeague, currentLeagueImg);
    prepareImage(desireLeague, desireLeagueImg);
});

function prepareImage(league, imageSrc){
    let tier = league.innerText.split(' ')[0];
    let division = league.innerText.split(' ')[1];
    imageSrc.src = getImagePath(tier, division);
}

function getImagePath(tier, division) {
    return `${homeIMGURL}/${tier}/${tier}${convertDivision(division)}.png`;
}