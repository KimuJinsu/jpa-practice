package com.jinsu.jpaexs;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

//@Entity
//@DynamicUpdate
//@Table(name = "MEMBER") // 테이블명을 명시적으로 설정
@Entity
@TableGenerator(
  name = "MEMBER_SEQ_GENERATOR",
  table = "MY_SEQUENCES",
  pkColumnValue = "MEMBER_SEQ", allocationSize = 50)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="MEMBER_SEQ_GENERATOR")
    private Long id; // Integer(X), String(X):자동증가...

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 엔티티가 처음 저장될 때 createdDate 자동 설정
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    // 엔티티가 업데이트될 때 lastModifiedDate 자동 설정
    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = new Date();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
