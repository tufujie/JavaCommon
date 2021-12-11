package com.jef.designpattern.action.mediator;

/**
 * 显卡
 * @author Jef
 * @date 20180902
 */
public class Video extends BaseColleague {
    private String videoData;

    public Video(Mediator mediator) {
        super(mediator);
    }

    public String getVideoData() {
        return videoData;
    }

    /**
     * 显示视频数据
     */
    public void showVideo(String data) {
        System.out.println(data);
    }
}
