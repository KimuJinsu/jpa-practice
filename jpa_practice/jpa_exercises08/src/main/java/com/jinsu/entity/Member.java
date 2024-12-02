package com.jinsu.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
public class Member extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MEMBER_ID")
	private Long id;
	
	private String name;
	
	@Embedded private Address address; // JPA는 스냅샷으로 address 참조 필드의 "주소" 값 변경을 체크!!!
									   // address의 필드[street/city/zipCode] 변경 사항은 체크하지 않음
	
	@ElementCollection
	@CollectionTable(name="FAVORITE_FOOD",
				joinColumns = @JoinColumn(name="MEMBER_ID"))
	private Set<String> favoriteFood = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name="ADDRESS",
				joinColumns = @JoinColumn(name="MEMBER_ID"))
	private List<Address> addressList = new ArrayList<>();
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id")
	private Team team;
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + "]";
	}
	
	

}