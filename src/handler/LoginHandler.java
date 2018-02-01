package handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.GameRequest;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月23日 下午2:19:58 

登录处理包 username+password

 */

public class LoginHandler implements GameHandler {

	private Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	public void execute(GameRequest paramGameRequest) {
		
		logger.info("LoginHandler is startprocess!");
		byte[] data=paramGameRequest.GetMessage().getData();
		/*
		 * 做客户端应答
		 */
	}
	
}
