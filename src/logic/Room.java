package logic;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ResponseMessage;


/*
 * 桌子类
 */

public class Room {
   
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private final int maxusers=8;//8个座位
	
	private ConcurrentHashMap<Integer,User> users=new ConcurrentHashMap<Integer,User>(); //桌子上的人数
	
	private Integer roomno=-1; //桌号
	
	private Vector roomindexno=new Vector();//座位管理
	
	private Object lockobj=new Object();//lock
	
	public Room()
	{
		for(int i=0;i<maxusers;i++)
		{
	    	roomindexno.add(i);
		}
	}
	
	/**
	 * 设置房间号
	 * @param roomno
	 */
	public void setRoomNo(int roomno)
	{
		this.roomno=roomno;
	}
	
	/*
	 * 返回房间号
	 */
	public Integer getRoomNo()
	{
		return roomno;
	}
	
	/*
	 * 发给某个人
	 */
	public void sendToUser(User user,ResponseMessage message)
	{
		ChannelHandlerContext ctx=user.getHandlerContext();
		if(null==ctx)
		{
			logger.info("ctx is error!");
			return;
		}
		user.getHandlerContext().writeAndFlush(message);
	}
	
	/*
	 * 发给一些人
	 */
	public void sendToUsers(User[] users,ResponseMessage message)
	{
		for(int i=0;i<users.length;i++)
		{
			ChannelHandlerContext ctx=users[i].getHandlerContext();
			if(null==ctx)
			{
				logger.error("ctx is error!");
				return;
			}
			ctx.writeAndFlush(message);
		}
	}
	
	/*
	 * 发送给房间的所有人
	 */
	public void sendAll(ResponseMessage message)
	{
		for(User user:users.values())
		{
			user.getHandlerContext().writeAndFlush(message);
		}
	}
	
	/**
	 * 添加玩家到房间
	 * @param user
	 */
	public void addUser(User user)
	{
		if(users.size()>maxusers)
		{
			logger.info("房间人数已满");
			return;
		}
		
		synchronized(lockobj)
		{
		  //设置座位号
		  int roomindex=(int) roomindexno.get(roomindexno.size()-1);
		  user.setRoomIndex(roomindex);
		}
		
		users.put(user.hashCode(), user);
	    
	}
	
	/**
	 * 删除房间的某个人
	 * @param user
	 */
	public void remoUser(User user)
	{
		users.remove(users.hashCode());
		
		synchronized(lockobj)
		{
	    	//回收座位号
		    roomindexno.add(user.getRoomIndex());
		}
		
	}
	
}
