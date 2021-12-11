package com.jef.designpattern.action.mediator;

/**
 * CPU类，一个同事类
 * @author Jef
 * @date 20180902
 */
public class Cpu extends BaseColleague {
    /**
     * 分解出来的视频数据
     */
    private String videoData;

    /**
     * 分解出来的音频数据
     */
    private String soundData;

    public Cpu(Mediator mediator) {
        super(mediator);
    }

    public String getVideoData() {
        return videoData;
    }

    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }

    public String getSoundData() {
        return soundData;
    }

    public void setSoundData(String soundData) {
        this.soundData = soundData;
    }

    /**
     * 处理来自CD的数据，分为视频和音频数据
     */
    public void executeData(String videoAndSound) {
        // 具体分割数据方法由数据组装方法决定
        String[] data = videoAndSound.split(",");
        this.setVideoData(data[0]);
        this.setSoundData(data[1]);
        // 通知主板，CPU的工作完成
        this.getMediator().changeed(this);
    }





}
