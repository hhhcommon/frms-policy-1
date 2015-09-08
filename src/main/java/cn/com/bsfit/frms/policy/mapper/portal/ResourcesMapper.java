package cn.com.bsfit.frms.policy.mapper.portal;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcesMapper {

    //根据资源code查找拥有该资源的所有用户编号
    List<Long> selectUserByResourceCode(@Param("resourceCode") String resourceCode);
}