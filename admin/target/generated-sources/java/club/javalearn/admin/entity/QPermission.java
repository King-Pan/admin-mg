package club.javalearn.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPermission is a Querydsl query type for Permission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPermission extends EntityPathBase<Permission> {

    private static final long serialVersionUID = -1228032969L;

    public static final QPermission permission = new QPermission("permission");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath expression = createString("expression");

    public final StringPath icon = createString("icon");

    public final NumberPath<Integer> orderNum = createNumber("orderNum", Integer.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath parentName = createString("parentName");

    public final NumberPath<Long> permissionId = createNumber("permissionId", Long.class);

    public final StringPath permissionName = createString("permissionName");

    public final StringPath permissionType = createString("permissionType");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath url = createString("url");

    public QPermission(String variable) {
        super(Permission.class, forVariable(variable));
    }

    public QPermission(Path<? extends Permission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPermission(PathMetadata metadata) {
        super(Permission.class, metadata);
    }

}

