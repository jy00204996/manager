package com.zero.system.webSocket;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 定时任务 websocket的定时任务
 使用spring的Schedule建立定时任务

 @EnableScheduling 开启spring定时任务功能
 @Scheduled(cron = "0/10 * * * * ?")    用于标识定时执行的方法，此处主要方法返回值一定是void，没有入参。对应定时时间配置可以百度cron语法，根据自己的业务选择合适的周期
 在这类中，我们通过上面MyWebSocket提供的静态方法获取其中的webSocketSet ，来获取所有此业务相关的所有websocketsession，可以在定时任务中对session内容进行验证判断（权限验证等），进行发送消息

 作者：清风徐来水波不清
 链接：https://www.jianshu.com/p/62132f605669
 来源：简书
 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。

 */
@Component
@EnableScheduling
public class TimeTask {

    private int i=1;

//    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);
    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行一次
    public void test(){
        System.err.println("*********   定时任务执行  间隔5秒执行一次,从数据库里获取数据 **************");
        CopyOnWriteArraySet<MyWebSocket> webSocketSet = MyWebSocket.getWebSocketSet();

        for (MyWebSocket c : webSocketSet) {
            try {
                int ii = this.i++;
//                当大于50的时候情况重新来次
                if (ii >= 1000) {
                    this.i=1;
                }
                //type==0表示从数据库里获取数据
                c.sendMessage(String.valueOf(ii),0);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }

//        System.err.println("/n 定时任务完成.......");
    }



}
