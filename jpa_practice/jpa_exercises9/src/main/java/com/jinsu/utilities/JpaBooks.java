package com.jinsu.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.persistence.*;

import com.jinsu.entity.Member;
import com.jinsu.entity.Team;

public class JpaBooks {
    
	
    static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static final Long minAge = 5L;
	static final Long maxAge = 50L;
    
    public static List<Long> initMemberTeamSampleData(EntityManagerFactory emf, int teamNumbers, int memberNumbers) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Long> teamIds = new ArrayList<>();
        List<Long> memberIds = new ArrayList<>();
        
        try {
            // 트랜잭션 시작
            tx.begin();
            
            // 팀 데이터 생성
            for (int i = 0; i < teamNumbers; i++) {
                Team team = new Team();
                team.setName("team:" + i);
                em.persist(team);
                teamIds.add(team.getId());
            }
            
            Long minIdValue = Collections.min(teamIds);
            Long maxIdValue = Collections.max(teamIds);

            // 멤버 데이터 생성
            for (int i = 0; i < memberNumbers; i++) {
                Member member = new Member();
                member.setName("kim:" + i);
                
           
                Long lAge = generateRandomNumber(minAge, maxAge);
//                long rAge = 1L;
                Integer randomAge = (int) lAge.longValue();
                member.setAge(randomAge);

                Long targetTeamId = generateRandomNumber(minIdValue, maxIdValue);
                Team team = em.find(Team.class, targetTeamId);
                team.addMember(member);
                em.persist(member);
                memberIds.add(member.getId());
            }
            
            // 트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            // 오류 발생 시 롤백
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // EntityManager 종료
            em.close();
        }
        
        return memberIds;
    }
    
    public static Long generateRandomNumber(Long min, Long max) {
        Random random = new Random();
        return random.nextLong(max - min + 1) + min;
    }
    
    public static Long generateRandomID(List<Long> ids) {
        Long minIdValue = Collections.min(ids);
        Long maxIdValue = Collections.max(ids);
        return generateRandomNumber(minIdValue, maxIdValue);
    }
}