package com.zhangyuzhou.book01.chapter07.reference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类原子操作
 */
public class ReferenceAtomicDemo {

    private static final AtomicReference<ReferenceAtomicUser> reference = new AtomicReference<>();

    public static void main(String[] args) {
        ReferenceAtomicUser zhangYuZhou = new ReferenceAtomicUser("zhangyuzhou", 10);
        reference.set(zhangYuZhou);

        ReferenceAtomicUser liSi = new ReferenceAtomicUser("liSi", 100);
        reference.set(liSi);

        ReferenceAtomicUser zhangSan = new ReferenceAtomicUser("liSi", 20);
        reference.compareAndSet(zhangYuZhou, zhangSan);
        System.err.println(String.format("人【%s】，年龄：【%s】", reference.get().getName(), reference.get().getAge()));
    }

    static class ReferenceAtomicUser {
        private String name;

        private int age;

        public ReferenceAtomicUser(String name, int age) {
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
