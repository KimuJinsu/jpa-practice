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
		    // EntityManager를 사용하여 주어진 id로 Member 엔티티를 데이터베이스에서 조회.
		    // em.find()는 영속성 컨텍스트(1차 캐시)에서 먼저 찾고, 없으면 데이터베이스에서 조회.
		    return em.find(Member.class, id);
		}

		private static List findList(final EntityManager em, final String query) {
		    // JPQL(Java Persistence Query Language) 쿼리를 사용하여 Member 엔티티 목록을 조회.
		    // em.createQuery()는 주어진 JPQL 쿼리를 실행하고, getResultList()로 결과를 리스트 형태로 반환.
		    // 쿼리는 Member.class 타입에 맞추어 실행됨.
		    return (List) em.createQuery(query, Member.class).getResultList();
		}

		private static void update(final EntityManager em, final Long id, final Integer age) {
		    // 주어진 id로 Member 엔티티를 조회한 후, 해당 엔티티의 나이(age) 필드를 업데이트.
		    // em.find()로 영속 상태의 엔티티를 조회하고, setter로 값을 변경하면 트랜잭션이 커밋될 때 데이터베이스에 반영됨.
		    Member member1 = em.find(Member.class, id);
		    member1.setAge(age); // 나이 값을 변경.
		}

		private static void delete(final EntityManager em, final Long id) {
		    // 주어진 id로 Member 엔티티를 조회한 후, 데이터베이스에서 해당 엔티티를 삭제.
		    // em.find()로 영속성 컨텍스트에서 엔티티를 찾은 뒤, em.remove()로 해당 엔티티를 제거.
		    // em.remove()는 영속성 컨텍스트에서 엔티티를 삭제하고, 트랜잭션 커밋 시 데이터베이스에서 삭제됨.
		    Member member = em.find(Member.class, id);
		    if (member != null) em.remove(member); // 엔티티가 존재할 경우에만 삭제.
		}
}
