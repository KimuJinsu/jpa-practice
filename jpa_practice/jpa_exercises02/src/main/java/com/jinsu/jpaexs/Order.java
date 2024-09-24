package com.jinsu.jpaexs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "`Order`")
public class Order {
    // 엔티티 필드 및 메서드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    
    private String description;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
        item.setOrder(this);
    }

    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
        item.setOrder(null);
    }
}