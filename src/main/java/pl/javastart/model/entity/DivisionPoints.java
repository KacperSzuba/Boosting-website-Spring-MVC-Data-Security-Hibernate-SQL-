package pl.javastart.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "divisions_points")
public class DivisionPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String points;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "division_points_id")
    private List<Price> prices;

    public DivisionPoints() {
    }

    public DivisionPoints(String points) {
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}


