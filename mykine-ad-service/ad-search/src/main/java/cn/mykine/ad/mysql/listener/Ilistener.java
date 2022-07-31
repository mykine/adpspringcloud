package cn.mykine.ad.mysql.listener;

import cn.mykine.ad.mysql.dto.BinlogRowData;

/**
 * Created by Jo@mykine
 */
public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
