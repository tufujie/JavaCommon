package com.jef.websocket;

import org.junit.Test;

/**
 * WebSocket应用测试
 * @author Jef
 * @date 2018/11/28 10:26
 */
public class WebSocketTest {

    /**
     * 测试WebSocket
     */
    @Test
    public void testWebSocket() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.sendMessage(null, null);
    }

}
