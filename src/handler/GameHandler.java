package handler;

import domain.GameRequest;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月22日 下午9:48:46 

具体执行的handler

 */

public abstract interface GameHandler {

	
	//具体执行handler的方法
	public abstract void execute(GameRequest paramGameRequest);
	
}
