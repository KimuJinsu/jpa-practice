package com.jinsu.jpaexs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MemberProduct {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	private int orderAmound;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
