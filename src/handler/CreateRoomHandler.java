package handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

import logic.Enums.SitDownAndUp;
import logic.Manager.RoomManager;
import logic.Manager.UserManager;
import net.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ResponseHandlerId;
import core.ObjectToBytes;
import domain.GameRequest;
import domain.GameResponse;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月6日 下午3:23:07 

请求创建房间

 */

public class CreateRoomHandler implements GameHandler{
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(GameRequest paramGameRequest) {
	    
		logger.info("CreateRoom is startprocess!");
		int roomid=RoomManager.getInstance().createRoom();
	  
		//创建房间的用户默认坐下
		UserManager.getInstance().getUser(paramGameRequest.GetChannelContext()).setSitDownState(SitDownAndUp.down);
	    
		ObjectToBytes objecttobytes=new ObjectToBytes();
	    objecttobytes.writeInt(ResponseHandlerId._createroom.ordinal());
	    objecttobytes.writeInt(roomid);
		ResponseMessage responsemessage=new ResponseMessage(ResponseHandlerId._createroom.ordinal(),objecttobytes.getBytes());
		paramGameRequest.GetChannelContext().writeAndFlush(responsemessage);
	
	}


}
