package com.zero.system.webSocket;


import com.alibaba.fastjson.JSONObject;
import com.zero.system.entity.User;
import com.zero.system.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint("/websocket")
@Component
public class MyWebSocket {

/*    @Autowired
    private EmployeeService employeeService;*/

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for(MyWebSocket item: webSocketSet){
            try {
                item.sendMessage(message,1);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     * type=1表示从客户端发送过来的请求数据然后推送给页面，0表示从数据库获取的数据推送给页面
     */
    public void sendMessage(String message,int type) throws IOException, EncodeException {
        if (type == 1) {
            this.session.getBasicRemote().sendText(message);
        }else{
            Integer id = Integer.valueOf(message);
            /**
             *  在websocket里面 直接通过@Autowire注解 调用service会报空指针
             *  所以我们要通过spring上下文的方式(ApplicationContext)拿到service层
             *  写一个act类 ApplicationContextRegister
             *
             *  websocket在实际使用中的场景:
             *  如果用户一旦进行相关操作，通过这些操作触发websocket发送请求，去数据库查数据然后返回给页面
             *  比如前端A用户点击充值，管理端对A用户的充值请求点击审核按钮后，会回调前端的API接口，告诉前端API，A用户的充值已经审核
             *  那么前端API会对用户表进行更新操作然后 把充值成功的信息和金额通过websocket发送给前端页面
             *
             */
            /*ApplicationContext act = ApplicationContextRegister.getApplicationContext();
            EmployeeService employeeService = act.getBean(EmployeeService.class);
            Employee em = employeeService.testSelectByPrimaryKey(id);
            System.err.println("************查询第" + id + "个用户****************");*/
            //把对象转换为String返回给前端 页面
            /*String s = JSONObject.toJSONString(em);
            String result = s+"|data";
            System.out.println(s);*/


            ApplicationContext act = ApplicationContextRegister.getApplicationContext();
            System.out.println("===============act: "+act);
            UserService UserService = act.getBean(UserService.class);
            User userResultBean = UserService.queryUser(id.toString());
//            System.err.println("************查询第" + id + "个用户****************");
            String s = JSONObject.toJSONString(userResultBean);
            String result = s+"|data";
            this.session.getBasicRemote().sendText(result);
        }

        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static CopyOnWriteArraySet<MyWebSocket> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<MyWebSocket> webSocketSet) {
        MyWebSocket.webSocketSet = webSocketSet;
    }


}
