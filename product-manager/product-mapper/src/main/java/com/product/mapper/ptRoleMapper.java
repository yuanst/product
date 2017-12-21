package com.product.mapper;

import com.product.model.ptRole;
import com.product.model.ptRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ptRoleMapper {
    int countByExample(ptRoleExample example);

    int deleteByExample(ptRoleExample example);

    int deleteByPrimaryKey(Integer roleKey);

    int insert(ptRole record);

    int insertSelective(ptRole record);

    List<ptRole> selectByExample(ptRoleExample example);

    ptRole selectByPrimaryKey(Integer roleKey);

    int updateByExampleSelective(@Param("record") ptRole record, @Param("example") ptRoleExample example);

    int updateByExample(@Param("record") ptRole record, @Param("example") ptRoleExample example);

    int updateByPrimaryKeySelective(ptRole record);

    int updateByPrimaryKey(ptRole record);
}