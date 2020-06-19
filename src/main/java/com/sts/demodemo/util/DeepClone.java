package com.sts.demodemo.util;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DeepClone {

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public Object deepClone(Object obj) {
        //将对象写进流里写到内存里
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //从内存中读出来
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
