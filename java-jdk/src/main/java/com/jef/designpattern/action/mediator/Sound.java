package com.jef.designpattern.action.mediator;

/**
 * 声卡
 * @author Jef
 * @date 20180902
 */
public class Sound extends BaseColleague {
    private String soundData;

    public Sound(Mediator mediator) {
        super(mediator);
    }

    public String getSoundData() {
        return soundData;
    }

    /**
     * 展示音频数据
     */
    public void showSound(String data) {
        System.out.println(data);
    }
}
