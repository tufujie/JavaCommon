package com.jef.websocket;

import com.jef.util.LogicUtils;
import org.springframework.web.socket.BinaryMessage;

import java.nio.ByteBuffer;

/**
 * WebSocket发送消息
 *
 * @author Jef
 * @date 2018/11/27 13:55
 */
public class SendMessage {

    /**
     * 模拟发送消息
     * @param payloadLength
     * @param sizeLimit
     */
    public void sendMessage(Integer payloadLength, Integer sizeLimit) {
        if (LogicUtils.isNull(payloadLength)) {
            payloadLength = 9721;
        }
        BinaryMessage sendMessage = new BinaryMessage(new byte[payloadLength]);
        int length = sendMessage.getPayloadLength();
        if (LogicUtils.isNull(sizeLimit)) {
            sizeLimit = 8192;
        }
        if (length > sizeLimit) {
            int maxPage = length / sizeLimit + (length % sizeLimit == 0 ? 0 : 1);
            System.out.println("最大页=" + maxPage);
            ByteBuffer byteBuffer = sendMessage.getPayload();
            int position = 0;
            for (int page = 0; page < maxPage; page++) {
                length = byteBuffer.remaining() > sizeLimit ? sizeLimit : byteBuffer.remaining();
                byteBuffer.position(position);
                byteBuffer.limit(length);
                BinaryMessage binaryMessage = new BinaryMessage(byteBuffer, page == maxPage - 1);
                System.out.println("分片发送消息page=" + page + ",position=" + position + ",length=" + length);
                position += length;
            }
        } else {
            System.out.println("发送消息");
        }
    }

}
