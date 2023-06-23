package co.istad.cms.repository;

import co.istad.cms.model.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryRepository {
    @Delete("""
                    DELETE * FROM contents WHERE id = #{id}
            """)
    boolean delete(@Param("id") Integer id);



    @Select("""
            SELECT *
            FROM categories
            WHERE is_deleted = FALSE
            """)
    @Result(property = "isDeleted", column = "is_deleted")
    List<Category> select();

}
