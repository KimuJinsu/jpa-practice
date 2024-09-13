package com.jinsu.jpaexs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String... args) throws InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // 회원 생성 및 저장
            Member member1 = new Member();
            member1.setName("kim");
            em.persist(member1);
            Long memID1 = member1.getId();
            
            Member member2 = new Member();
            member2.setName("lee");
            em.persist(member2);
            Long memID2 = member2.getId();
            

//            Member member3 = new Member();
//            member3.setName("no");
//            em.persist(member3);
//            Long memID3 = member3.getId();
            
            // 상품 생성 및 저장
            Product product1 = new Product();
            product1.setName("coke");
            em.persist(product1);
            Long productID1 = product1.getId();
            
            Product product2 = new Product();
            product2.setName("shampoo");
            em.persist(product2);
            Long productID2 = product2.getId();
            
//            Product product3 = new Product();
//            product3.setName("shampoo");
//            em.persist(product3);
//            Long productID3 = product3.getId();
            
            MemberProduct memberProdcut1 = new MemberProduct();
            memberProdcut1.setMember(member1);
            memberProdcut1.setProduct(product1);
            memberProdcut1.setOrderAmound(10);
            em.persist(memberProdcut1);
            
            MemberProduct memberProdcut2 = new MemberProduct();
            memberProdcut2.setMember(member2);
            memberProdcut2.setProduct(product2);
            memberProdcut2.setOrderAmound(50);
            em.persist(memberProdcut2);
            
            MemberProduct memberProdcut3 = new MemberProduct();
            memberProdcut3.setMember(member1);
            memberProdcut3.setProduct(product2);
            memberProdcut3.setOrderAmound(10);
            em.persist(memberProdcut3);
            
            MemberProduct memberProdcut4 = new MemberProduct();
            memberProdcut4.setMember(member2);
            memberProdcut4.setProduct(product1);
            memberProdcut4.setOrderAmound(50);
            em.persist(memberProdcut4);
            
            em.flush();
            em.clear();
     
//            Member member = em.find(Member.class, memID1);
//            
//            List<MemberProduct> memberProducts = member.getMemberProducts();
//            for(MemberProduct mp : memberProducts) {
//            	Product product = mp.getProduct();
//            	System.out.printf("상품 id: %d, 상품 name:%s \n", product.getId(), product.getName());
//            }
            
            Product product = em.find(Product.class, productID1);
            List<MemberProduct> memberProducts2 = product.getMemberProducts();
            for(MemberProduct mp : memberProducts2) {
            	Product member = mp.getProduct();
            	System.out.printf("멤버 id: %d,멤버 name:%s \n", member.getId(), member.getName());
            }
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}