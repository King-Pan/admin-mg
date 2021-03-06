package club.javalearn.admin.repository;

import club.javalearn.admin.entity.DataDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/7/23
 * Time: 下午12:22
 * Description: No Description
 */
public interface DataDictRepository extends JpaRepository<DataDict, Long>, QuerydslPredicateExecutor<DataDict>, JpaSpecificationExecutor<DataDict> {

    /**
     * 查询数据字典中的所有类型
     *
     * @return 数据字典中的所有类型
     */
    @Query(value = "select distinct d.type , d.dictValue,d.id  from DataDict d where d.parentId is null  ")
    List<Object[]> findAllType();


    /**
     * 通过ID查询字典信息
     * @param id 字典ID
     * @return 字典信息
     */
    DataDict getById(Long id);
}
