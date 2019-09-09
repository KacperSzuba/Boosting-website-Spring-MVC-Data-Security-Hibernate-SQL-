package pl.javastart.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class OrderBoost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currentTier; //should be in enum
    private String currentDivision; //should be in enum
    private String destinationTier; //should be in enum
    private String destinationDivision; //should be in enum
    private String summonerID;
    private String lolUsername;
    private String lolPassword;
    private String region; //should be in enum
    private String noteToBoosters;
    private LocalDateTime date;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean whetherPaid;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public OrderBoost() {}

    public OrderBoost(String currentTier, String currentDivision, String destinationTier,
                      String destinationDivision, String summonerID, String lolUsername,
                      String lolPassword, String region, String noteToBoosters, LocalDateTime date,
                      boolean whetherPaid, User user) {
        this.currentTier = currentTier;
        this.currentDivision = currentDivision;
        this.destinationTier = destinationTier;
        this.destinationDivision = destinationDivision;
        this.summonerID = summonerID;
        this.lolUsername = lolUsername;
        this.lolPassword = lolPassword;
        this.region = region;
        this.noteToBoosters = noteToBoosters;
        this.date = date;
        this.whetherPaid = whetherPaid;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(String currentTier) {
        this.currentTier = currentTier;
    }

    public String getCurrentDivision() {
        return currentDivision;
    }

    public void setCurrentDivision(String currentDivision) {
        this.currentDivision = currentDivision;
    }

    public String getDestinationTier() {
        return destinationTier;
    }

    public void setDestinationTier(String destinationTier) {
        this.destinationTier = destinationTier;
    }

    public String getDestinationDivision() {
        return destinationDivision;
    }

    public void setDestinationDivision(String destinationDivision) {
        this.destinationDivision = destinationDivision;
    }

    public String getSummonerID() {
        return summonerID;
    }

    public void setSummonerID(String summonerID) {
        this.summonerID = summonerID;
    }

    public String getLolUsername() {
        return lolUsername;
    }

    public void setLolUsername(String lolUsername) {
        this.lolUsername = lolUsername;
    }

    public String getLolPassword() {
        return lolPassword;
    }

    public void setLolPassword(String lolPassword) {
        this.lolPassword = lolPassword;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNoteToBoosters() {
        return noteToBoosters;
    }

    public void setNoteToBoosters(String noteToBoosters) {
        this.noteToBoosters = noteToBoosters;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isWhetherPaid() {
        return whetherPaid;
    }

    public void setWhetherPaid(boolean whetherPaid) {
        this.whetherPaid = whetherPaid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
