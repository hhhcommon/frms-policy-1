package cn.com.bsfit.frms.policy.mapper.rams;

import cn.com.bsfit.frms.policy.pojo.rams.Checklist;
import org.apache.ibatis.annotations.Param;

public interface ChecklistMapper {

    int insert(Checklist record);

    Checklist selectByPrimaryKey(Long id);

    int insertChecklistRiskType(@Param("checklistId")long checklistId, @Param("riskTypeValue")String riskTypeValue);
}