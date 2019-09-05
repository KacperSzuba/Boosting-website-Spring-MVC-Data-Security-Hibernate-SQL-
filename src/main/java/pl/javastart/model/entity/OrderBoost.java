package pl.javastart.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class OrderBoost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderName;
    private LocalDateTime date;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean whetherPaid;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public OrderBoost() {}

    public OrderBoost(String orderName, LocalDateTime date, boolean whetherPaid,User user) {
        this.orderName = orderName;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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
