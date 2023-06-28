package com.noint.messenger.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRoom is a Querydsl query type for UserRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRoom extends EntityPathBase<UserRoom> {

    private static final long serialVersionUID = 1333252201L;

    public static final QUserRoom userRoom = new QUserRoom("userRoom");

    public final DateTimePath<java.util.Date> delDate = createDateTime("delDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final NumberPath<Long> roomSeq = createNumber("roomSeq", Long.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QUserRoom(String variable) {
        super(UserRoom.class, forVariable(variable));
    }

    public QUserRoom(Path<? extends UserRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRoom(PathMetadata metadata) {
        super(UserRoom.class, metadata);
    }

}

