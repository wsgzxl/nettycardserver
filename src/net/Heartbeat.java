package net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class Heartbeat extends ChannelInboundHandlerAdapter {

	
    @Override
	public void userEventTriggered(ChannelHandlerContext ctx,Object evt) throws Exception
	 {
		 if(evt instanceof IdleStateEvent)
		 {
			 IdleStateEvent event=(IdleStateEvent)evt;
			 String type="";
			 if(event.state()==IdleState.READER_IDLE)
			 {
			      type="read_idle";
			 }
			 else if(event.state()==IdleState.WRITER_IDLE)
			 {
			      type="write_idle";
			 }
			 else if(event.state()==IdleState.ALL_IDLE)
			 {
				 type="all idle";
			 }
			 
		 }
		 else
		 {
		     super.userEventTriggered(ctx, evt);
		 }
	 }

	 
}
