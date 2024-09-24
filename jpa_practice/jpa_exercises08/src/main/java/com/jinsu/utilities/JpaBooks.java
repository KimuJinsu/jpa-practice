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

	public static List<Long> initMemberTeamSampleData(EntityManager em,
			int teamNumbers,
			int memberNumbers){
		
		List<Long> teamIds = new ArrayList<>();
		
		for (int i=0; i<teamNumbers; i++) {
			Team team = new Team();
			team.setName("team:" + i);
			em.persist(team);
			teamIds.add(team.getId());
			
		}
		
		Long minIdValue = Collections.min(teamIds);
		
		Long maxIdValue = Collections.max(teamIds);
		
		List<Long> memberIds = new ArrayList<>();
		for (int i=0; i<memberNumbers; i++) {
			Member member = new Member();
			member.setName("kim:" + i);
			Long targetTeamId = generateRandomNumber(minIdValue, maxIdValue);
			
			Team team = em.find(Team.class, targetTeamId);
			team.addMember(member);
			em.persist(member);
			memberIds.add(member.getId());
			
			
		}
		em.flush();
		em.clear();
		
		
		return memberIds; 
	}
	
	public static Long generateRandomNumber(Long min, Long max) {
		Random random = new Random();
		return random.nextLong(max - min + 1) + min;
		
	}
	
	
	public static Long generateRandomID(List<Long> ids){
		Long minIdValue = Collections.min(ids);
		
		Long maxIdValue = Collections.max(ids);
		
		return generateRandomNumber(minIdValue, maxIdValue);
	}
	
	
	
	
	
	
}
