package cn.mykine.ad.mysql.dto;

import cn.mykine.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jo@mykine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    /**
     * binlog数据中字段数组索引 -> 字段名
     * */
    private Map<Integer, String> posMap = new HashMap<>();
}
