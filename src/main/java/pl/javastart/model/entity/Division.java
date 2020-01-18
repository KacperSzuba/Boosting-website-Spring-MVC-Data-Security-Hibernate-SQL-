package pl.javastart.model.entity;

import pl.javastart.model.enums.Tier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Tier tier;
    private Integer division;
    private String points;
    private String imgSource;
    private Integer price;

    public Division(){}

    public Division(Tier tier, Integer division, String points, String imgSource, Integer price) {
        this.tier = tier;
        this.division = division;
        this.points = points;
        this.imgSource = imgSource;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return Objects.equals(id, division.id) &&
                tier == division.tier &&
                Objects.equals(this.division, division.division) &&
                Objects.equals(points, division.points) &&
                Objects.equals(imgSource, division.imgSource) &&
                Objects.equals(price, division.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tier, division, points, imgSource, price);
    }
}
