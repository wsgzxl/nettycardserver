package domain;

import io.netty.channel.ChannelHandlerContext;
import net.RequestMessage;
import net.ResponseMessage;

/*
@author YHL
@qq: 1357098586
@email: 1357098586@qq.com

服务器对客户端应答

 */

public class GameResponse {

	    //请求应答消息
		private ResponseMessage responsemessag=null;
		
		public ResponseMessage GetMessage()
		{
			return responsemessag;
		}
		
		//客户端通信句柄
		private ChannelHandlerContext channelcontext=null;
		
		public ChannelHandlerContext GetChannelContext()
		{
			return channelcontext;
		}
		
	
	    public GameResponse(ChannelHandlerContext ctx,ResponseMessage msg)
	    {
	    	this.channelcontext=ctx;
	    	this.responsemessag=msg;
	    }
	
}
