package com.zhangyuzhou.subandpub;

import com.zhangyuzhou.common.Constants;
import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub implements Constants {

    @Override
    public void onMessage(String channel, String message) {
        //收到消息会调用
        String msg = String.format("订阅者收到--> 【%s】 上的一条消息--> 【%s】", channel, message);
        System.out.println(msg);
        super.onMessage(channel, message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        //订阅了频道会调用
        String msg = String.format("订阅者注册成功--> 【%s】, subscribedChannels %d", channel, subscribedChannels);
        System.out.println(msg);
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        //取消订阅 会调用
        String msg = String.format("取消订阅, channel %s, subscribedChannels %d", channel, subscribedChannels);
        System.out.println(msg);
        super.onUnsubscribe(channel, subscribedChannels);
    }
}
