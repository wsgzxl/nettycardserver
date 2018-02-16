package logic.Manager;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ResponseMessage;
import logic.User;

/*
 *  用户管理类
 */

public class UserManager {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private static UserManager _instance=new UserManager();//防止并发带来的锁，所以直接new
	
	private UserManager()
	{
		
	}
	
	public static UserManager getInstance()
	{
		return _instance;
	}
	
	private ConcurrentHashMap<Integer,User> players=new ConcurrentHashMap<Integer,User>();//所有玩家
	private ConcurrentHashMap<ChannelHandlerContext,User> cus=new ConcurrentHashMap<ChannelHandlerContext,User>();//玩家的channelcontext,user
	
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
	
}
