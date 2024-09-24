package com.jinsu;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.jinsu.entity.Member;
import com.jinsu.entity.Address;
import com.jinsu.entity.AddressEntity;
import com.jinsu.entity.FavoriteFood;
import com.jinsu.utilities.JpaBooks;

public class Main {

    static final int TEAM_NUMBERS = 10;
    static final int MEMBER_NUMBERS = 10;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String... args) throws InterruptedException {
        // EntityManager 생성
        EntityManager em = emf.createEntityManager();
        List<Long> membersIds = JpaBooks.initMemberTeamSampleData(em, TEAM_NUMBERS, MEMBER_NUMBERS);

        Long memberId = insertFavoriteFood(em, membersIds); // 수정: em을 넘겨줌
        updateAddressList(em, memberId); // 수정: em을 넘겨줌

        em.close();
        emf.close();
    }

    public static Long insertFavoriteFood(EntityManager em, List<Long> memberIds) {
        EntityTransaction tx = em.getTransaction();
        Long id = -1L;
        try {
            tx.begin();

            Member member = em.find(Member.class, JpaBooks.generateRandomID(memberIds));
            id = member.getId();

            FavoriteFood pizza = FavoriteFood.builder().foodName("피자").member(member).build();
            FavoriteFood donkatsu = FavoriteFood.builder().foodName("돈까스").member(member).build();
            FavoriteFood jajangmyeon = FavoriteFood.builder().foodName("짜장면").member(member).build();

            member.getFavoriteFoods().add(pizza);
            member.getFavoriteFoods().add(donkatsu);
            member.getFavoriteFoods().add(jajangmyeon);

            AddressEntity address1 = AddressEntity.builder()
                    .address(new Address("456 Elm st", "Gwangju", "12345"))
                    .member(member)
                    .build();

            AddressEntity address2 = AddressEntity.builder()
                    .address(new Address("789 Eldo st", "Daejeon", "67890"))
                    .member(member)
                    .build();

            em.persist(member);

            em.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } 
        return id;
    }

    public static void updateAddressList(EntityManager em, Long memberId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Member member = em.find(Member.class, memberId);

            List<AddressEntity> addrList = member.getAddressList(); // 확인: addressList는 AddressEntity가 아니라 Address
            if (!addrList.isEmpty()) {
                AddressEntity addressToRemove = addrList.get(0);
                addrList.remove(addressToRemove);
                em.remove(addressToRemove);  // 실제로 삭제 처리
            }

            AddressEntity newAddress = AddressEntity.builder()
                    .address(new Address("St Free", "Newyork", "23231"))
                    .member(member)
                    .build();

            addrList.add(newAddress);
            em.persist(newAddress);

            em.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }
}