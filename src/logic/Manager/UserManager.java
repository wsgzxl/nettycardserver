package logic.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import net.ResponseMessage;
import logic.User;

/*
 *  用户管理类
 */

public class UserManager {

	private static UserManager _instance=new UserManager();
	
	private UserManager()
	{
		
	}
	
	public static UserManager getInstance()
	{
		return _instance;
	}
	
	private ConcurrentHashMap<Integer,User> players=new ConcurrentHashMap<Integer,User>();//所有玩家
	
	//发送消息给指定玩家
	public void sendMessage(User user,ResponseMessage message)
	{
		user.getHandlerContext().writeAndFlush(message);
	}
	
	//发送消息给多个玩家
	public void sendUsers(User[] users,ResponseMessage message)
	{
		for(int i=0;i<users.length;i++)
		{
			users[i].getHandlerContext().writeAndFlush(message);
		}
	}
	
	//发送给所有玩家
	public void sendAll(ResponseMessage message)
	{
		for(User user : players.values())
		{
	    	user.getHandlerContext().writeAndFlush(message);
	    }
	}
	
	//添加用户
	public  void addUser(User user)
	{
		 players.put(user.hashCode(),user);
	}
	
	//删除用户
	public  void removeUser(User user)
	{
		players.remove(user.hashCode());
	}
	
	//获取用户数量
	public int getUserCount()
	{
		return players.size();
	}
	
}
