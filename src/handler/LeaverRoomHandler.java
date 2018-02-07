package handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
	}
	
}
