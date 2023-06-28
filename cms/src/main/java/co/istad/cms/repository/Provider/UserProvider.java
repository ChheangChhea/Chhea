package co.istad.cms.repository.Provider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider implements ProviderMethodResolver {
    public String select(){


        return new SQL(){{
            SELECT("*");
            FROM("users");

        }

        }.toString();
    }
    public String selectUserRoles(){
        return new SQL(){{
            SELECT("r.id , r.name");
            FROM("roles AS r");
            INSERT_INTO("Users_roles_id =# {userId}");

        }}.toString();
    }

}
