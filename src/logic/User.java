package logic;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ResponseHandlerId;
import core.ObjectToBytes;
import domain.GameRequest;
import domain.MessageQueue;
import net.ResponseMessage;
import logic.Enums.SitDownAndUp;
import io.netty.channel.ChannelHandlerContext;


/*
 * 用户类
 * 
 */

public class User {
   
	 private Logger logger=LoggerFactory.getLogger(getClass());
	
	private int weixinid=-1;//玩家微信ID
	 
	 public int getId()
	 {
		 return weixinid;
	 }
	 
	 private String name="";//玩家姓名
	 
	 public String getName()
	 {
		 return name;
	 }
	 
	 private int roomindex=0;//玩家在桌子上的序号
	 
	 public int getRoomIndex()
	 {
		 return roomindex;
	 }
	 
	 /**
	  * 
	  * @param index
	  */
	 public void setRoomIndex(int index)
	 {
		 roomindex=index;
	 }
	 
	 private SitDownAndUp sitdown;
	 
	 public SitDownAndUp getSitDownState()
	 {
		 return sitdown;
	 }
	 
	 public void setSitDownState(SitDownAndUp state)
	 {
		 this.sitdown=state;
	 }
	 
	 private ChannelHandlerContext ctx;//通信管道 
	 
	 public ChannelHandlerContext getHandlerContext()
	 {
		 return ctx;
	 }
	 
	 /**
	  * 设置通信管道
	  * @param ctx
	  */
	 public void setChannelHandler(ChannelHandlerContext ctx){
		 this.ctx=ctx;
	 }
	
	 private Room room=null;//玩家所在房间
	 
	 public Room getRoom()
	 {
		 return room;
	 }
	 
	 /**
	  * 获取游戏开始前数据
	  */
	 public ResponseMessage getBeforeGameMessage()
	 {
		 ObjectToBytes objecttobytes=new ObjectToBytes();
		 objecttobytes.writeInt(getId());
		 objecttobytes.writeString(getName());
		 objecttobytes.writeInt(getRoomIndex());
		 return new ResponseMessage(ResponseHandlerId._beforegame.ordinal(),objecttobytes.getBytes());
	 }
	 
	 /**
	  * 发送数据
	  * @param message
	  */
	 public void Send(ResponseMessage message)
	 {
		 ctx.writeAndFlush(message);
	 }
	 
}
