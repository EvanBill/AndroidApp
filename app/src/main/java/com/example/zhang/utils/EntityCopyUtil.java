package com.example.zhang.utils;import java.io.ByteArrayInputStream;import java.io.ByteArrayOutputStream;import java.io.IOException;import java.io.ObjectInputStream;import java.io.ObjectOutputStream;import java.io.OptionalDataException;import java.util.ArrayList;import java.util.List;public class EntityCopyUtil {    /**     * 对一个数据进行深度拷贝 数据要实现Serializable接口     *     * @param src     * @return     */    public static ArrayList deepCopy(List src) {        ArrayList dest = null;        try {            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();            ObjectOutputStream out = new ObjectOutputStream(byteOut);            out.writeObject(src);            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());            ObjectInputStream in = new ObjectInputStream(byteIn);            dest = (ArrayList) in.readObject();            byteOut.close();            out.close();            byteIn.close();            in.close();        } catch (OptionalDataException e) {            e.printStackTrace();        } catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        }        return dest;    }    /**     * 对一个数据对象进行深度拷贝     *     * @param src 数据要实现Serializable接口     * @return     */    public static Object deepCopyObject(Object src) {        Object dest = null;        try {            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();            ObjectOutputStream out = new ObjectOutputStream(byteOut);            out.writeObject(src);            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());            ObjectInputStream in = new ObjectInputStream(byteIn);            dest = in.readObject();            byteOut.close();            out.close();            byteIn.close();            in.close();        } catch (OptionalDataException e) {            e.printStackTrace();        } catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        }        return dest;    }}