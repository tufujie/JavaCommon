package com.jef.designpattern.action.mediator;

/**
 * 客户端
 * @author Jef
 * @date 20180902
 */
public class Client {
    public static void main(String[] args) {
        // 创建中介者对象
        MotherBoard mediator = new MotherBoard();
        // 创建各个同事类
        CdDriver cdDriver = new CdDriver(mediator);
        Cpu cpu = new Cpu(mediator);
        Video video = new Video(mediator);
        Sound sound = new Sound(mediator);
        mediator.setCdDriver(cdDriver);
        mediator.setCpu(cpu);
        mediator.setVideo(video);
        mediator.setSound(sound);
        // 开始读取数据
        cdDriver.readCd();
    }
}
