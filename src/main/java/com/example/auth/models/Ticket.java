package com.example.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

// Модель билета в океанариум
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id")
    @JsonIgnore
    private User visitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private Show show;

    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    private BigDecimal price;

    public Ticket() {}

    public Ticket(User visitor, Show show, Timestamp purchaseDate, BigDecimal price) {
        this.visitor = visitor;
        this.show = show;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getVisitor() {
        return visitor;
    }

    public void setVisitor(User visitor) {
        this.visitor = visitor;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    // Добавим toString для отладки
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", visitorId=" + (visitor != null ? visitor.getId() : null) +
                ", showId=" + (show != null ? show.getId() : null) +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                '}';
    }
}