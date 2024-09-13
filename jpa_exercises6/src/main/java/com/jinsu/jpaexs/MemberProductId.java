package com.jinsu.jpaexs;

import java.io.Serializable;
import java.util.Objects;

public class MemberProductId implements Serializable {
    
    private Long member;  // Member 엔티티의 기본 키
    private Long product; // Product 엔티티의 기본 키

    // 기본 생성자
    public MemberProductId() {}

    // 매개변수가 있는 생성자
    public MemberProductId(Long member, Long product) {
        this.member = member;
        this.product = product;
    }

    // equals 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberProductId that = (MemberProductId) o;
        return Objects.equals(member, that.member) &&
               Objects.equals(product, that.product);
    }

    // hashCode 메서드
    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }
}
