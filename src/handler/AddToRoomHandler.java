package handler;

import logic.Manager.RoomManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.GameRequest;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月6日 下午7:22:49 

加入房间

 */

public class AddToRoomHandler implements GameHandler {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(GameRequest paramGameRequest) {
	    
		logger.info("AddToRoomHandler is startprocess!");
	//	RoomManager.getInstance().add
		
	}
}
