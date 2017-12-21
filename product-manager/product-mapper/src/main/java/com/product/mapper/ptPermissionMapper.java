package com.product.mapper;

import com.product.model.ptPermission;
import com.product.model.ptPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
 *<p>Title: ptPermissionMapper</p>
 * @author yuanst
 * <p>Company:</p>
 * @date 2017年12月21日,下午4:58:15
 * @version 1.0
 */
public interface ptPermissionMapper {
    int countByExample(ptPermissionExample example);

    int deleteByExample(ptPermissionExample example);

    int deleteByPrimaryKey(Integer permissionKey);

    int insert(ptPermission record);

    int insertSelective(ptPermission record);

    List<ptPermission> selectByExample(ptPermissionExample example);

    ptPermission selectByPrimaryKey(Integer permissionKey);

    int updateByExampleSelective(@Param("record") ptPermission record, @Param("example") ptPermissionExample example);

    int updateByExample(@Param("record") ptPermission record, @Param("example") ptPermissionExample example);

    int updateByPrimaryKeySelective(ptPermission record);

    int updateByPrimaryKey(ptPermission record);
    List<ptPermission> findPermissionListByUserId(int userId);
    List<ptPermission> findMenuListByUserId(int userId);
}
