package com.jinsu.jpaexs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        
        // EntityManagerFactory 생성
        System.out.println("EntityManagerFactory 생성 중...");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        System.out.println("EntityManager 생성 완료.");

        // 트랜잭션 시작
        EntityTransaction tx = em.getTransaction();
        System.out.println("트랜잭션 시작...");
        tx.begin(); // 트랜잭션 시작

        try {
            // 팀 생성 및 저장
            System.out.println("팀 객체 생성 중...");
            Team team = new Team();
            team.setName("team2");
            em.persist(team);
            System.out.println("팀이 데이터베이스에 저장되었습니다.");

            // 멤버 생성 및 저장
            System.out.println("회원 객체 생성 중...");
            Member member = new Member("kim");
            team.addMember(member);  // 편의 메서드 호출로 양방향 연관관계 설정
            em.persist(member);
            
            System.out.println("회원이 데이터베이스에 저장되었습니다. 회원 ID: " + member.getId());

            // 영속성 컨텍스트 초기화
            em.clear();
            System.out.println("flush 및 clear 완료. 영속성 컨텍스트 초기화.");

            // 멤버 다시 조회
            Member gotMember = em.find(Member.class, member.getId());
            
            Team gotTeam = gotMember.getTeam();
            Long teamID = gotTeam.getId();
            String teamName = gotTeam.getName();
            System.out.println("저장된 팀 ID: " + teamID);
            System.out.println("저장된 팀 이름: " + teamName);

            // 트랜잭션 커밋
            tx.commit();
            System.out.println("트랜잭션 커밋 완료.");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 에러 발생 시 트랜잭션 롤백
            System.out.println("트랜잭션 롤백 실행됨.");
        } finally {
            em.close();  // EntityManager 종료
            emf.close(); // EntityManagerFactory 종료
            System.out.println("EntityManager 및 EntityManagerFactory 종료.");
        }
    }

    // 멤버들을 저장하고 ID 리스트를 반환하는 메서드
    public static List<Long> saveMembersAndReturnMemberIds(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Long> memberIds = new ArrayList<>();  // Member ID를 저장할 리스트
        
        try {
            tx.begin();
            
            // 멤버 및 팀 생성
            Member member1 = new Member("회원1");
            Member member2 = new Member("회원2");
            Team team1 = new Team("팀1");
            Team team2 = new Team("팀2");
            
            // 팀과 멤버 저장
            em.persist(team1);
            em.persist(team2);
            team1.addMember(member1);
            team1.addMember(member2);
            
            em.persist(member1);
            em.persist(member2);
            
            // 멤버 ID 리스트에 추가
            memberIds.add(member1.getId());
            memberIds.add(member2.getId());
            
            em.flush();
            em.clear();
            
            // 멤버와 팀 정보 다시 조회
            Member mem = em.find(Member.class, memberIds.get(0));
            System.out.println("member의 팀 이름: " + mem.getTeam().getName());  // 팀 이름 출력
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        
        return memberIds;  // 멤버 ID 리스트 반환
    }
}