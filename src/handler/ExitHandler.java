package handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.GameRequest;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月24日 下午6:30:27 

 */

public class ExitHandler implements GameHandler {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(GameRequest paramGameRequest) {
	    
		logger.info("ExitHandler is startprocess!");
		
	}

}
