package cn.mykine.ad.index.adunit;

/**
 * Created by Jo@mykine
 * 流量类型
 */
public class AdUnitConstants {

    public static class POSITION_TYPE {

        public static final int KAIPING = 1;//开屏广告
        public static final int TIEPIAN = 2;//视频播放前的贴片广告
        public static final int TIEPIAN_MIDDLE = 4;//中贴，视屏播放的中间植入的广告
        public static final int TIEPIAN_PAUSE = 8;//暂停贴，暂停播放时植入的广告
        public static final int TIEPIAN_POST = 16;//后贴，播放完后植入的广告
    }
}
