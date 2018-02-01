package domain;

import core.Message;
import net.RequestMessage;
import io.netty.channel.ChannelHandlerContext;

/*
@author YHL
@qq: 1357098586
@email: 1357098586@qq.com

客户端业务请求
 */

public class GameRequest {
 
	
	//请求消息
	private RequestMessage requestmessage=null;
	
	public RequestMessage GetMessage()
	{
		return requestmessage;
	}
	
	public int getRequestId()
	{
		return requestmessage==null?-1:requestmessage.getId();
	}
	
	//客户端通信句柄
	private ChannelHandlerContext channelcontext=null;
	
	public ChannelHandlerContext GetChannelContext()
	{
		return channelcontext;
	}
	
	/*
	 * ctx 客户端通信句柄
	 * msg消息数据
	 */
	public GameRequest(ChannelHandlerContext ctx,RequestMessage msg)
	{
		this.channelcontext=ctx;
		this.requestmessage=msg;
	}
	
}
