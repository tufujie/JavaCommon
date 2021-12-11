package com.jef.designpattern.action.mediator;

/**
 * 中介者实现类，主板类，跟各个同事类交互
 * @author Jef
 * @date 20180902
 */
public class MotherBoard implements Mediator {
    private CdDriver  cdDriver;
    private Cpu cpu;
    private Video video;
    private Sound sound;

    public void setCdDriver(CdDriver cdDriver) {
        this.cdDriver = cdDriver;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void changeed(BaseColleague colleague) {
        if (colleague instanceof CdDriver) {
            this.openCdDriverReadData();
        } else if (colleague instanceof Cpu) {
            this.openCpuDealData();
        }
    }

    /**
     * 打开CD读取数据
     */
    private void openCdDriverReadData() {
        // 先获取光驱读取的数据
        String videoAndSounnd = this.cdDriver.getVideaAndSound();
        // 把这些数据传递给CPU进行处理
        this.cpu.executeData(videoAndSounnd);
    }

    /**
     * 打开CPU处理数据
     */
    private void openCpuDealData() {
        // 先获取CPU处理后的数据
        String videoData = this.cpu.getVideoData();
        String soundData = this.cpu.getSoundData();
        // 把这些数据传递给显卡和声卡显示出来
        this.video.showVideo(videoData);
        this.sound.showSound(soundData);
    }

}
