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
@version 创建时间：2018年2月8日 下午5:28:39 

开始游戏

 */

public class StartGameHandler implements GameHandler {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(GameRequest paramGameRequest) {
	    
		logger.info("StartGameHandler is startprocess!");
	  
	  
	}
}