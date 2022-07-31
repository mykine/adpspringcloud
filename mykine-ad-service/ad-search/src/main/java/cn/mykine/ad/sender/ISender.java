package cn.mykine.ad.sender;

import cn.mykine.ad.mysql.dto.MySqlRowData;

/**
 * Created by Jo@mykine
 */
public interface ISender {

    void sender(MySqlRowData rowData);
}
