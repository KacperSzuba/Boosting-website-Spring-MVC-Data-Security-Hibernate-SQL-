package com.BoostingWebsite.boosterApplication;

class BoosterApplication {
    private Long id;
    private String skype;
    private String discord;
    //@NotEmpty(message = "E-mail cannot be empty")
    private String email;
    //@NotEmpty(message = "Opgg link cannot be empty")
    private String opgg;
    private Region region;
    private String leagueRank;
    private String averageNumberOfHoursPlayedDaily;
    //@NotNull(message = "Age cannot be empty")
    private Integer age;
    //@NotEmpty(message = "Country cannot be empty")
    private String country;
    //@NotEmpty(message = "Best form of contact cannot be empty")
    private String bestFormOfContact;
    //@NotEmpty(message = "Your motivation cannot be empty")
   //@Column(length = 2000)
    private String motivation;

    public BoosterApplication() {
    }

    private BoosterApplication(Long id,
                       String skype,
                       String discord,
                       String email,
                       String opgg,
                       Region region,
                       String leagueRank,
                       String averageNumberOfHoursPlayedDaily,
                       Integer age,
                       String country,
                       String bestFormOfContact,
                       String motivation) {
        this.id = id;
        this.skype = skype;
        this.discord = discord;
        this.email = email;
        this.opgg = opgg;
        this.region = region;
        this.leagueRank = leagueRank;
        this.averageNumberOfHoursPlayedDaily = averageNumberOfHoursPlayedDaily;
        this.age = age;
        this.country = country;
        this.bestFormOfContact = bestFormOfContact;
        this.motivation = motivation;
    }

    static BoosterApplication restore(BoosterApplicationSnapshot snapshot){
        return new BoosterApplication(
                snapshot.getId(),
                snapshot.getSkype(),
                snapshot.getDiscord(),
                snapshot.getEmail(),
                snapshot.getOpgg(),
                snapshot.getRegion(),
                snapshot.getLeagueRank(),
                snapshot.getAverageNumberOfHoursPlayedDaily(),
                snapshot.getAge(),
                snapshot.getCountry(),
                snapshot.getBestFormOfContact(),
                snapshot.getMotivation());
    }

    BoosterApplicationSnapshot getSnapshot(){
        return new BoosterApplicationSnapshot(id, skype, discord, email, opgg, region, leagueRank, averageNumberOfHoursPlayedDaily, age, country, bestFormOfContact, motivation);
    }
}
