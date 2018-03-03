package logic.Manager;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dispatcher.HandlerDispatcher;
import domain.GameRequest;
import domain.MessageQueue;
import net.ResponseMessage;
import logic.User;

/*
 *  用户管理类
 */

public class UserManager {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private static UserManager _instance=new UserManager();//防止并发带来的锁，所以直接new
	
	private ConcurrentHashMap<Integer,User> players=new ConcurrentHashMap<Integer,User>();//所有玩家
	
	private ConcurrentHashMap<ChannelHandlerContext,User> cus=new ConcurrentHashMap<ChannelHandlerContext,User>();//玩家的channelcontext,user
	
	private HandlerDispatcher handlerDispatcher;
	
	private UserManager()
	{
	
	}
	
	public static UserManager getInstance()
	{
		return _instance;
	}
	
	/**
	 * 获取所有的用户
	 * @return
	 */
	public Map<Integer,User> getUser(){
		return players;
	}
	
	//发送消息给指定玩家
	public void sendMessage(User user,ResponseMessage message)
	{
		user.Send(message);
	}
	
	//发送消息给多个玩家
	public void sendUsers(User[] users,ResponseMessage message)
	{
		for(int i=0;i<users.length;i++)
		{
			users[i].Send(message);
		}
	}
	
	//发送给所有玩家
	public void sendAll(ResponseMessage message)
	{
		for(User user : players.values())
		{
	    	user.Send(message);
	    }
	}
	
	//添加用户
	public  void addUser(User user)
	{
		 players.put(user.hashCode(),user);
		 cus.put(user.getHandlerContext(),user);
	}
	
	//删除用户
	public  void removeUser(User user)
	{
		players.remove(user.hashCode());
		cus.remove(user.getHandlerContext());
	}
	
	/**
	 * 返回玩家的ctx
	 * @param ctx
	 * @return
	 */
	public User getUser(ChannelHandlerContext ctx)
	{
		return cus.get(ctx);
	}
	
	//获取用户数量
	public int getUserCount()
	{
		return players.size();
	}
	
	/*
	 * 设置消息处理器
	 */
	public void setHandlerDispatcher(HandlerDispatcher handlerDispatcher){
		this.handlerDispatcher=handlerDispatcher;
	}
	
    /*
     * 为链接添加消息队列
     */
    
    public void addMessageQueue(GameRequest request){
       if(null!=this.handlerDispatcher){
    	   this.handlerDispatcher.addMessage(request);
       }
    }
    
    /*
     * 为链接删除消息队列
     */
    private void removeMessageQueue(ChannelHandlerContext channel)
    {
    	//TODO当链接断开的时候清空队列
    	
    }
    
    /*
     * 删除这个用户
     */
    public void removeUser(ChannelHandlerContext ctx){
    	User user=cus.remove(ctx);
    	if(user!=null){
    		players.remove(user);
    	}
    }
    
}
