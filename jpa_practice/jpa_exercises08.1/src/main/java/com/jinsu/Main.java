package com.jinsu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // EntityManagerFactory를 이용해 JPA Persistence Unit을 초기화
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            // 데이터 삽입
            Long personId = createPerson(em);

            // 트랜잭션 커밋
            tx.commit();

            // 조회 및 출력
            tx.begin();
            Person person = em.find(Person.class, personId);
            printPersonDetails(person);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 오류 발생 시 롤백
        } finally {
            em.close();
            emf.close();
        }
    }

    // 새로운 Person 엔티티를 생성하고 데이터베이스에 저장
    private static Long createPerson(EntityManager em) {
        // 임베디드 값 타입: 주소 정보 생성
        Address address = new Address("Daegu", "Duryu", "12345");

        // 컬렉션 값 타입: 전화번호 목록 생성
        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        phoneNumbers.add(new PhoneNumber("+82", "010-1234-5678"));
        phoneNumbers.add(new PhoneNumber("+82", "010-9876-5432"));

        // Person 객체 생성 및 정보 설정
        Person person = new Person();
        person.setName("John Doe");
        person.setHomeAddress(address);
        person.setPhoneNumbers(phoneNumbers);
        person.getHobbies().add("Reading");
        person.getHobbies().add("Cycling");

        // 엔티티 저장
        em.persist(person);

        return person.getId(); // 생성된 Person ID 반환
    }

    // Person 정보 출력
    private static void printPersonDetails(Person person) {
        System.out.println("Person ID: " + person.getId());
        System.out.println("Name: " + person.getName());
        System.out.println("Address: " + person.getHomeAddress().getCity() + ", " + person.getHomeAddress().getStreet());
        
        System.out.println("Phone Numbers:");
        for (PhoneNumber phoneNumber : person.getPhoneNumbers()) {
            System.out.println(phoneNumber.getCountryCode() + " " + phoneNumber.getLocalNumber());
        }

        System.out.println("Hobbies:");
        for (String hobby : person.getHobbies()) {
            System.out.println(hobby);
        }
    }
}