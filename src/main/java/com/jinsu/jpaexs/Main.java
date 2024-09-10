package com.jinsu.jpaexs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//import antlr.collections.List;

public class Main {

    public static void main(String[] args) {
        
        // EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        
        // 트랜잭션 시작
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin(); // 트랜잭션 시작
            
            // 새로운 Member 엔티티 생성
//            Member mem = new Member();
//            mem.setId(1L); // ID는 고유해야 함
//            mem.setUsername("Jinsu");
//            mem.setAge(27);
            
            // persist() 메서드를 사용해 엔티티 영속화 (데이터베이스에 저장 준비)
//            em.persist(mem);
//            
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> members = query.getResultList();
            Long id = 10L;
            
            Member mem10 = save(em, "ten",30, RoleType.Guest);
//            Member mem2 = save(em, "kim",20, RoleType.Guest);
//            Member mem3 = save(em, "jin",30, RoleType.Admin);
//            Member mem4 = save(em, "su",40, RoleType.Guest);
//            Member mem5 = save(em, "king",50, RoleType.Guest);

//            Member mem2 = save(em, "gung",10, RoleType.Admin);
            Long mem1ID = mem10.getId();

            
//            Member mem2 = save(em, id + 1, "kim", 20, RoleType.Admin);
            
            
          
            
            
            
            // 트랜잭션 커밋 (데이터베이스에 반영)
            tx.commit();
            
            System.out.println("Member가 데이터베이스에 저장되었습니다.");
            
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 에러 발생 시 트랜잭션 롤백
        } finally {
            em.close();  // EntityManager 종료
            emf.close(); // EntityManagerFactory 종료
        }
    }
	  private static Member save(final EntityManager em, String name, Integer age, RoleType roleType) {
			Member member = new Member();
//			member.setId(id); // Long이기 때문에 L붙여줌
			member.setUsername(name);
			member.setAge(age);
			member.setRoleType(roleType);
			// 현재 member 객체는 비영속상태
		    em.persist(member);
		   return member;
		  }

		  private static Member find(final EntityManager em, final Long id) {
		    return em.find(Member.class, id);
		  }

		  private static List findList(final EntityManager em, final String query) {
		    return (List) em.createQuery(query, Member.class).getResultList();
		  }

		  private static void update(final EntityManager em, final Long id, final Integer age) {
		    Member member1 = em.find(Member.class, id);
		    member1.setAge(age);
		  }

		  private static void delete(final EntityManager em, final Long id) {
		    Member member = em.find(Member.class, id);
		    if (member != null) em.remove(member);
		  }

}
