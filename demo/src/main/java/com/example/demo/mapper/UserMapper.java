package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper extends BassMapper<User>{
	
    @Select("select * from ts_user where no = #{no}")
    List<User> selectByFileType(String no);

    @Select("${sql}")
    List<User> findByAll(@Param("sql") String sql);

    @SelectProvider(type=SoftwareFileMapperProvider.class,method = "getByAll")
    List<User> getByAll( User user);

    class SoftwareFileMapperProvider{
        public String getByAll(User user){
            return new SQL(){{
                SELECT("id,no,name");
                FROM("ts_user");
//                if(user.getId()!=null){
//                    WHERE("id=#{id}");
//                }
            }}.toString();
        }
    }

}
