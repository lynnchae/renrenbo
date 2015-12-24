package me.daoke.renrenfm.controller.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhuosh on 2015/8/2.
 */
public class Data {

    static class RandomHan {
        private Random ran = new Random();
        private final static int delta = 0x9fa5 - 0x4e00 + 1;

        public char getRandomHan() {
            return (char) (0x4e00 + ran.nextInt(delta));
        }
    }

    public static List<Author> initAllDate(){
        RandomHan han = new RandomHan();
        List<Author> data = new ArrayList<Author>();
        Author author = null;
        for(int i=0; i < 100; i++) {
            author = new Author();
            author.setAge(new Random().nextInt(100)+"");
            author.setAuthorName(han.getRandomHan() + han.getRandomHan() + "");
            author.setAuthorRemark(han.getRandomHan() + han.getRandomHan() + "" + han.getRandomHan() + han.getRandomHan() + "");
            author.setDenseNum(new Random().nextInt(1000) + "");
            author.setPicUrl("http://localhost:8080/renrenfm/jpg/" + i % 5 + ".jpg");
            author.setId(i + "");
            author.setStatus(new Random().nextInt(2));
            data.add(author);
            author = null;
        }
        return data;
    }
    
}
