package co.istad.cms.repository;

import co.istad.cms.model.Category;
import co.istad.cms.model.Content;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Mapper
public interface ContentRepository {


    @Select("""
            SELECT *
            FROM contents 
            WHERE is_deleted = FALSE
            """)




    @Results(id = "contentResultMap",value = {
            @Result(property = "isDeleted",column = "is_deleted"),
            @Result(property = "createAd",column = "create_at"),
            @Result(property = "category",column = "category_id",
            one = @One(select = "selectContentCategory"))

    })
    List<Content> select();
     @Select( "SELECT *FROM categories WHERE id =#{categoryId}")
     @Result(property = "isDeleted", column = "is_deleted")
    Category selectContentCategory(@Param("categoryId")Integer categoryId);

    @Select("SELECT * FROM contents  WHERE id=#{id}")
    @ResultMap(value = "contentResultMap")
    Optional<Content> selectById(@Param("id") Integer id);



    @Insert("""
            INSERT INTO contents
                (uuid, slug, keyword, title, description, thumbnail, editor, is_deleted, created_at, category_id)
            VALUES (
                #{c.uuid}, #{c.slug}, #{c.keyword}, #{c.title},
                #{c.description}, #{c.thumbnail}, #{c.editor},
                #{c.isDeleted}, #{c.createdAt}, #{c.category.id}
            )
            """)
    boolean insert(@Param("c") Content content);

    @Delete("DELETE FROM contents WHERE id =#{id}")
    boolean deleteById(@Param("id") Integer id);





//    @Update("""
//            UPDATE contents
//            SET is_deleted = #{isDeleted}
//            WHERE id = #{id}
//            """)
@Options(statementType = StatementType.CALLABLE)
@Update("CALL pro_update_content_status(#{id}, #{is_delete})")

    boolean updateIsDeletedById(@Param("id") Integer id,
                                @Param("isDeleted") Boolean isDeleted);


    @Update("""
            UPDATE contents 
            SET title=#{c.title},
            description=#{c.description},
            keyword=#{c.keyword} ,
            slug =#{c.slug},
            thumbnail = #{c.thumbnail},
            editor=#{c.editor},
            category_id=#{c.category.id}
            WHERE id = #{c.id}
            """)
    boolean update(@Param("c") Content content);
}
