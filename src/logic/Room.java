package logic;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import logic.Enums.SitDownAndUp;

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
	
	private ReentrantLock lockobj=new ReentrantLock();//锁
	
	private boolean isstartgame=false;//是否开始游戏
	
	public Room(int roomindex)
	{
		for(int i=0;i<maxusers;i++)
		{
	    	roomindexno.add(i);
		}
		setRoomNo(roomindex);
		isstartgame=false;
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
	    user.Send(message);
	}
	
	/*
	 * 发给一些人
	 */
	public void sendToUsers(User[] users,ResponseMessage message)
	{
		for(int i=0;i<users.length;i++)
		{
			users[i].Send(message);
		}
	}
	
	/*
	 * 发送给房间的所有人
	 */
	public void sendAll(ResponseMessage message)
	{
		for(User user:users.values())
		{
			user.Send(message);
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
		
		lockobj.lock();
		{
		  int lastindex=roomindexno.size()-1;
		  //设置座位号
		  int roomindex=(int) roomindexno.get(lastindex);
		  user.setRoomIndex(roomindex);
		  roomindexno.remove(lastindex);
		}
		lockobj.unlock();

		users.put(user.hashCode(), user);
	    
		for(int i=0;i<users.size();i++)
		{
			for(int j=0;j<users.size();j++)
			{
		    	users.get(j).Send(users.get(i).getBeforeGameMessage());
			}
		}
		
	}
	
	/**
	 * 离开房间
	 * @param user
	 */
	public void remoUser(User user)
	{
		
		users.remove(users.hashCode());
		
		lockobj.lock();
		{
	    	//回收座位号
		    roomindexno.add(user.getRoomIndex());
		}
		
		user.setSitDownState(SitDownAndUp.up);//设置状态为站起
		
		lockobj.unlock();
	}

    /**
     * 开始游戏
     */
	public void startGame()
	{
		
		isstartgame=true;
		
	}
	
}
