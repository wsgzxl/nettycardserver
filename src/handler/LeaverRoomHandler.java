package handler;

import logic.Manager.RoomManager;
import logic.Manager.UserManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.BytesToObject;
import domain.GameRequest;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月6日 下午7:18:53 

 */

public class LeaverRoomHandler implements GameHandler {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(GameRequest paramGameRequest) {
	    
		logger.info("LeaverRoomHandler is startprocess!");
	
	    byte[] data=paramGameRequest.GetMessage().getData();
	    BytesToObject bytestoobject=new BytesToObject(data);
		int roomid=bytestoobject.readInt();
		RoomManager.getInstance().leaveToRoom(roomid, UserManager.getInstance().getUser(paramGameRequest.GetChannelContext()));
	
	}
}
