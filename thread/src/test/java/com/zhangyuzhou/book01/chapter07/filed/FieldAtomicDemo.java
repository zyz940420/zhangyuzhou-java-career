package com.zhangyuzhou.book01.chapter07.filed;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子操作对象的字段
 */
public class FieldAtomicDemo {

    private static final AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(FieldAtomicUser.class, "age");

    public static void main(String[] args) {
        FieldAtomicUser user = new FieldAtomicUser("zhangyuzhou", 20);
        updater.getAndIncrement(user);
        System.err.println(updater.get(user));
    }

    static class FieldAtomicUser {
        private String name;

        //要更新的字段必须用原子类修饰，否则存在并发风险
        //必须使用public 修饰，否则报错访问不到
        public volatile int age;

        public FieldAtomicUser(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
