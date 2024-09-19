package com.jinsu;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jinsu.entity.Address;
import com.jinsu.entity.Member;
import com.jinsu.utilities.JpaBooks;

public class Main {

	static final int TEAM_NUMBERS = 10;
	
	static final int MEMBER_NUMBERS = 10;
	
	private static EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("jpabook");
	
//	public static void performTx(EntityManager ) {}
	
	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			List<Long> membersIds = JpaBooks.initMemberTeamSampleData(em,
					TEAM_NUMBERS,
					MEMBER_NUMBERS);
			
			Long memberId = insertFavoriteFood(em, membersIds);
			searchFavoriteFood(em, memberId);
			updateFavoriteFood(em, memberId);
			insertAddressAndAddressList(em, membersIds);
			
			tx.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}  finally {
			em.close();
			emf.close();
		}
	}
	
	public static Long insertFavoriteFood(EntityManager em, List<Long> memberIds) {
		Member member = em.find(Member.class, JpaBooks.generateRandomID(memberIds));
		
		member.getFavoriteFood().add("짬뽕");
		member.getFavoriteFood().add("떡볶이");
		member.getFavoriteFood().add("바닐라아이스라떼");
		
		em.flush();
		em.clear();
		
		return member.getId();
		
	}
	
	public static void searchFavoriteFood(EntityManager em, Long memberId) {
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		
		Member member = em.find(Member.class, memberId);
//		System.out.println("Member ID:" + member.getId());
		
		for (String str : member.getFavoriteFood()) {
			System.out.println("searchFavoriteFood, Food::" + str);
		}
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}
	
	public static void updateFavoriteFood(EntityManager em, Long memberId) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	
		
		Member member = em.find(Member.class, memberId);
		System.out.println("Member ID:" + member.getId());
		
		member.getFavoriteFood().remove("바닐라아이스라떼");
		
		member.getFavoriteFood().add("아이스아메리카노");
		
	}
	
	public static void insertAddressAndAddressList(EntityManager em, List<Long> memberIds) {
		Member member = em.find(Member.class, JpaBooks.generateRandomID(memberIds));
		
		member.setAddress(new Address("123 Main Street","Daegu", "12345"));
		
		member.getAddressList().add(new Address("456 Main Street","Daegu", "67890"));
		member.getAddressList().add(new Address("789 Main Street","Daegu", "54321"));
		
		em.flush();
		em.clear();
		
		Member foundMember = em.find(Member.class, member.getId());
		
		
		for (Address address : foundMember.getAddressList()) {
			System.out.printf("street:%s, city:%s, zipCode:%s \n", address.getStreet(), 
							address.getCity(), address.getZipCode());
		}
	}

}
