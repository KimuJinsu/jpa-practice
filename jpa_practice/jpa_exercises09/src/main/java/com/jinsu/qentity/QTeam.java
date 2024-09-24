package com.jinsu.qentity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.jinsu.entity.Member;
import com.jinsu.entity.QMember;
import com.jinsu.entity.Team;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = -577110594L;

    public static final QTeam team = new QTeam("team");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Member, com.jinsu.entity.QMember> memberList = this.<Member, com.jinsu.entity.QMember>createList("memberList", Member.class, QMember.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata metadata) {
        super(Team.class, metadata);
    }

}

