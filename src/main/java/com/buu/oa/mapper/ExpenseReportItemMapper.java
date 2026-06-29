package com.buu.oa.mapper;

import com.buu.oa.entity.ExpenseReportItem;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 报销明细表Mapper接口
 * 定义报销明细相关数据库操作方法
 */
public interface ExpenseReportItemMapper {

    /**
     * 批量插入报销明细
     * @param itemList 明细列表
     * @return 受影响行数
     */
    int batchInsertItems(@Param("itemList") List<ExpenseReportItem> itemList);
}