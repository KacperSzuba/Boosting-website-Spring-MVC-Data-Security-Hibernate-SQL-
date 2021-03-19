package com.BoostingWebsite.boosterApplication;

class BoosterApplicationFactory {

    BoosterApplication from(BoosterApplicationDto boosterApplicationDto){
        return BoosterApplication.restore(
                new BoosterApplicationSnapshot(
                        boosterApplicationDto.getId(),
                        boosterApplicationDto.getSkype(),
                        boosterApplicationDto.getDiscord(),
                        boosterApplicationDto.getEmail(),
                        boosterApplicationDto.getOpgg(),
                        boosterApplicationDto.getRegion(),
                        boosterApplicationDto.getLeagueRank(),
                        boosterApplicationDto.getAverageNumberOfHoursPlayedDaily(),
                        boosterApplicationDto.getAge(),
                        boosterApplicationDto.getCountry(),
                        boosterApplicationDto.getBestFormOfContact(),
                        boosterApplicationDto.getMotivation()
                )
        );
    }
}
