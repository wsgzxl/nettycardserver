package net;

import core.Message;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月21日 下午8:57:30 

服务器回发给客户端的消息

 */

public class ResponseMessage extends Message {

	public ResponseMessage(int id, byte[] data) {
		super(id, data);
	    
	}
   
}
