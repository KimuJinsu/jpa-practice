package com.jinsu.jpaexs;

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
            Member member = new Member();
            member.setName("kim");
            em.persist(member);
            Long memID = member.getId();
            
            // 상품 생성 및 저장
            Product product = new Product();
            product.setName("seo");
            em.persist(product);
            Long productID = product.getId();
            
            // 양방향 관계 설정
            member.getProducts().add(product);
            product.getMember().add(member); // 이 부분 추가하여 양방향 관계를 동기화
            
            em.flush();
            em.clear();
            
            // 다시 회원을 조회하고 연관된 상품 정보 출력
            Member foundMember1 = em.find(Member.class, memID);
            List<Product> productList = foundMember1.getProducts();
            
            for (Product item : productList) {
                String name = item.getName();
                System.out.println("Product Name: " + name);
            }
            
            // 상품에 연관된 회원 정보 출력
            Product foundProduct = em.find(Product.class, productID);
            Member foundMember2 = foundProduct.getMember().get(0);
            System.out.println("Member Name: " + foundMember2.getName());
            
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