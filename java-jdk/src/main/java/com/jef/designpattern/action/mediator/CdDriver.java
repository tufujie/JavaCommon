package com.jef.designpattern.action.mediator;

/**
 * CD光驱类，一个同事类
 * @author Jef
 * @date 20180902
 */
public class CdDriver extends BaseColleague {
    public CdDriver(Mediator mediator) {
        super(mediator);
    }

    /**
     * 光驱读出来的视频和音频
     */
    private String videaAndSound;

    /**
     * 获取光驱读取出来的视频和音频
     * @return 光驱读取出来的视频和音频
     */
    public String getVideaAndSound() {
        return videaAndSound;
    }

    public void setVideaAndSound(String videaAndSound) {
        this.videaAndSound = videaAndSound;
    }

    /**
     * 读取光盘，业务方法
     */
    public void readCd() {
        this.setVideaAndSound("科幻大片视频,科幻大片音频");
        // 通知主板，自己的状态发生了改变
        this.getMediator().changeed(this);
    }
}
