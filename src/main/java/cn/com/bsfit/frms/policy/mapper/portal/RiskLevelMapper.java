package cn.com.bsfit.frms.policy.mapper.portal;

import org.apache.ibatis.annotations.Param;

public interface RiskLevelMapper {

    Long getRiskLevel(@Param("score") int score);
}