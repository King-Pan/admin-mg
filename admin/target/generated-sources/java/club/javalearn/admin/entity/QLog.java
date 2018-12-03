package club.javalearn.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLog is a Querydsl query type for Log
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLog extends EntityPathBase<Log> {

    private static final long serialVersionUID = 755735004L;

    public static final QLog log = new QLog("log");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath errorMessage = createString("errorMessage");

    public final StringPath ip = createString("ip");

    public final NumberPath<Long> logId = createNumber("logId", Long.class);

    public final StringPath method = createString("method");

    public final StringPath moduleName = createString("moduleName");

    public final StringPath operation = createString("operation");

    public final StringPath params = createString("params");

    public final StringPath result = createString("result");

    public final StringPath status = createString("status");

    public final NumberPath<Long> time = createNumber("time", Long.class);

    public final StringPath url = createString("url");

    public final StringPath userName = createString("userName");

    public QLog(String variable) {
        super(Log.class, forVariable(variable));
    }

    public QLog(Path<? extends Log> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLog(PathMetadata metadata) {
        super(Log.class, metadata);
    }

}

