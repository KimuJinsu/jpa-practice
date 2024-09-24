package com.jinsu.qentity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.jinsu.entity.*;
import com.jinsu.entity.QAddress;
import com.jinsu.entity.QAddressEntity;
import com.jinsu.entity.QBaseEntity;
import com.jinsu.entity.QFavoriteFood;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -752553541L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.jinsu.entity.QBaseEntity _super = new QBaseEntity(this);

    public final com.jinsu.entity.QAddress address;

    public final ListPath<AddressEntity, com.jinsu.entity.QAddressEntity> addressList = this.<AddressEntity, com.jinsu.entity.QAddressEntity>createList("addressList", AddressEntity.class, QAddressEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> creationDate = _super.creationDate;

    public final SetPath<FavoriteFood, com.jinsu.entity.QFavoriteFood> favoriteFoods = this.<FavoriteFood, com.jinsu.entity.QFavoriteFood>createSet("favoriteFoods", FavoriteFood.class, QFavoriteFood.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final QTeam team;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

