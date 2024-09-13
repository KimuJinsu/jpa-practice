package com.jinsu.jpaexs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
@IdClass(MemberProductId.class)  // 복합키 클래스 지정
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmound;

    // 기본 생성자
    public MemberProduct() {}

    // getter, setter
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderAmound() {
        return orderAmound;
    }

    public void setOrderAmound(int orderAmound) {
        this.orderAmound = orderAmound;
    }
}
