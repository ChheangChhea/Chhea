package co.istad.cms.repository;

import co.istad.cms.model.Role;
import co.istad.cms.model.User;
import co.istad.cms.repository.Provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {
    @SelectProvider(UserProvider.class)
    @Results(id = "userResultMap", value = {
            @Result(column = "display_name", property = "displayName"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "created_at", property = "createAt"),
            @Result(column = "id", property = "roles",
                    many = @Many(select = "selectUserRoles"))

    })
    List<User> select();

    @SelectProvider(UserProvider.class)
    List<Role>selectUserRoles(@Param("userId") Integer userId);
}
