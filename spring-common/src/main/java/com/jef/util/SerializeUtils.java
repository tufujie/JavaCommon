package com.jef.util;

import com.alibaba.fastjson.util.IOUtils;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Jef
 * @date 2018/11/30 12:36
 */
public class SerializeUtils {
    /**
     * 序列化对象
     * @return
     * @throws IOException
     */
    public static <T> String serialize(T seria) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(seria);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 反序列化对象
     * @param <T>
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T deSerialization(String str,T seria) throws IOException,
            ClassNotFoundException {
        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        seria= (T) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return seria;
    }

    /**
     * fst将对象序列化成二进制数组
     * @param obj
     * @return
     */
    public static byte[] fstserialize(Object obj) {
        ByteArrayOutputStream out = null;
        FSTObjectOutput fout = null;
        try {
            out = new ByteArrayOutputStream(1024 * 16);
            fout = new FSTObjectOutput(out);
            fout.writeObject(obj);
            fout.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * fst反序列化二进制数组成对象
     * @param bytes
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fstdeserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        FSTObjectInput in = null;
        try {
            in = new FSTObjectInput(new ByteArrayInputStream(bytes));
            return (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * jdk序列化
     * @param obj
     * @return
     */
    public static byte[] jdkserialize(Object obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.close(oos);
            IOUtils.close(baos);
        }
    }

    /**
     * jdk反序列化
     * @param bits
     * @return
     */
    public static Object jdkdeserialize(byte[] bits) {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bits);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.close(ois);
            IOUtils.close(bais);
        }
    }

}