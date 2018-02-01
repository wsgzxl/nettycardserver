package net;

import core.Message;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月21日 下午9:00:02 

客户端的消息

 */

public class RequestMessage extends Message {

	public RequestMessage(int id, byte[] data) {
		super(id, data);
		
	}

    public int getId()
    {
    	return super.getId();
    }
    
    public byte[] getData()
    {
    	return super.getData();
    }
}
