package com.product.mapper;

import com.product.model.ptPermission_role;
import com.product.model.ptPermission_roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 角色权限关联
 * @author yuanst
 *
 */
public interface ptPermissionRoleMapper {
	
    int countByExample(ptPermission_roleExample example);

    int deleteByExample(ptPermission_roleExample example);

    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(ptPermission_role record);

    int insertSelective(ptPermission_role record);

    List<ptPermission_role> selectByExample(ptPermission_roleExample example);

    ptPermission_role selectByPrimaryKey(Integer rolePermissionId);

    int updateByExampleSelective(@Param("record") ptPermission_role record, @Param("example") ptPermission_roleExample example);

    int updateByExample(@Param("record") ptPermission_role record, @Param("example") ptPermission_roleExample example);

    int updateByPrimaryKeySelective(ptPermission_role record);

    int updateByPrimaryKey(ptPermission_role record);
}
