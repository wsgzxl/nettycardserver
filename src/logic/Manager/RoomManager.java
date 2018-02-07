package logic.Manager;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import net.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ResponseHandlerId;
import core.ObjectToBytes;
import logic.Room;
import logic.User;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月25日 下午3:58:28 

桌子管理

 */

public class RoomManager {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private  final int roomnostep=10;//每次加10
	
	private AtomicInteger roomnoincreasing=new AtomicInteger(100000);//原子操作加锁
	
	private static RoomManager _instance=new RoomManager();
	
	private  final Object lockobj=new Object();//锁
	
	private RoomManager()
	{
		
	}
	
	public static RoomManager getInstance()
	{
		return _instance;
	}
	
	private ConcurrentHashMap<Integer,Room> rooms=new ConcurrentHashMap<Integer,Room>();
	
    /**
     * 创建房间
     * @return 房间号
     */
	public int createRoom()
	{
		int roomno=roomnoincreasing.addAndGet(roomnostep);
		Room room=new Room();
		rooms.put(roomno, room);
		return roomno;
	}
	
	/**
	 * 加入到房间
	 * 
	 * @param roomid
	 *            房间号
	 */
	public void addToRoom(int roomid, User user) {
		synchronized (lockobj) {
			if (rooms.containsKey(roomid)) {
				rooms.get(roomid).addUser(user);
			} else {
				ResponseMessage message = new ResponseMessage(
						ResponseHandlerId._nofindroom.ordinal(), null);
				user.getHandlerContext().writeAndFlush(message);
			}
		}
	}

	/*
	 * 解散房间
	 */
	public void dissolutionRoom(int roomid) {
		synchronized (lockobj) {
			if (rooms.containsKey(roomid)) {
				rooms.remove(roomid);
			} else {
				logger.error("异常:未找到房间号" + roomid);
			}
		}
	}
	
	
}
