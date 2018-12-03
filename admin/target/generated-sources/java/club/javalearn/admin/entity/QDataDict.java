package club.javalearn.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataDict is a Querydsl query type for DataDict
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataDict extends EntityPathBase<DataDict> {

    private static final long serialVersionUID = -960761112L;

    public static final QDataDict dataDict = new QDataDict("dataDict");

    public final StringPath dictCode = createString("dictCode");

    public final StringPath dictValue = createString("dictValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> orderNum = createNumber("orderNum", Integer.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath type = createString("type");

    public QDataDict(String variable) {
        super(DataDict.class, forVariable(variable));
    }

    public QDataDict(Path<? extends DataDict> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataDict(PathMetadata metadata) {
        super(DataDict.class, metadata);
    }

}

