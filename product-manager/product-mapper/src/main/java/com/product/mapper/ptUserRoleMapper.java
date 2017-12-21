package com.product.mapper;

import com.product.model.ptUserRole;
import com.product.model.ptUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ptUserRoleMapper {
    int countByExample(ptUserRoleExample example);

    int deleteByExample(ptUserRoleExample example);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(ptUserRole record);

    int insertSelective(ptUserRole record);

    List<ptUserRole> selectByExample(ptUserRoleExample example);

    ptUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByExampleSelective(@Param("record") ptUserRole record, @Param("example") ptUserRoleExample example);

    int updateByExample(@Param("record") ptUserRole record, @Param("example") ptUserRoleExample example);

    int updateByPrimaryKeySelective(ptUserRole record);

    int updateByPrimaryKey(ptUserRole record);
}