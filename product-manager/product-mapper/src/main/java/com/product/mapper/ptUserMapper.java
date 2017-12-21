package com.product.mapper;

import com.product.model.ptUser;
import com.product.model.ptUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ptUserMapper {
    int countByExample(ptUserExample example);

    int deleteByExample(ptUserExample example);

    int deleteByPrimaryKey(Integer userKey);

    int insert(ptUser record);

    int insertSelective(ptUser record);

    List<ptUser> selectByExample(ptUserExample example);

    ptUser selectByPrimaryKey(Integer userKey);

    int updateByExampleSelective(@Param("record") ptUser record, @Param("example") ptUserExample example);

    int updateByExample(@Param("record") ptUser record, @Param("example") ptUserExample example);

    int updateByPrimaryKeySelective(ptUser record);

    int updateByPrimaryKey(ptUser record);
    ptUser getByName(String loginName);
}