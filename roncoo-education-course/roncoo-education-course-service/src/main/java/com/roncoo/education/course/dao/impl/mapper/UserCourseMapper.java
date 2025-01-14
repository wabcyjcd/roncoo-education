package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCourseMapper {
    int countByExample(UserCourseExample example);

    int deleteByExample(UserCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    List<UserCourse> selectByExample(UserCourseExample example);

    UserCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCourse record, @Param("example") UserCourseExample example);

    int updateByExample(@Param("record") UserCourse record, @Param("example") UserCourseExample example);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);
}