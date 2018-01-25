package netty;

import java.sql.Date;

import logic.LogicMain;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import net.RequestMessage;
import net.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.GameRequest;
import domain.MessageQueue;
import Core.Message;

public class ServerHandler extends ChannelInboundHandlerAdapter{
	
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx){
		
		
		try
		{
		super.handlerAdded(ctx);

		LogicMain.getInstance().getDispatcher().addMessageQueue(ctx,new MessageQueue());
		
		logger.info("进来了一个连接:"+ctx.channel().id().toString());
		
		}catch(Exception ex)		
		{
		   logger.error("异常信息:"+ex);
	    }
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx)
	{
		try
		{
	    
		   super.handlerRemoved(ctx);
	       
		   //删除此链接的消息队列
		   LogicMain.getInstance().getDispatcher().removeMessageQueue(ctx);
		   
		   logger.info("删除了一个连接:"+ctx.channel().id().toString());
		
		}catch(Exception ex)		
		{
		   logger.error("异常信息:"+ex);
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
	{
		try
		{
		
		RequestMessage message=(RequestMessage)msg;
		logger.info("reciver data:"+message.toString());	
		
		LogicMain.getInstance().getDispatcher().addMessage(new GameRequest(ctx,message));
		
		
	/*	ResponseMessage m=new ResponseMessage(1000,new byte[]{0xc,0xd});
		ctx.writeAndFlush(m);*/
		
		/*ByteBuf buf = (ByteBuf)msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
        ReferenceCountUtil.release(msg);//回收
*/        
       
		/*String body = new String(req,"UTF-8");
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
				new Date(System.currentTimeMillis()).toString():"BAD ORDER";
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(resp);*/
		
	}catch(Exception ex)
	{
		logger.error("异常信息:"+ex);
	}
}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		try
		{
		logger.info(ctx.channel().id()+"异常断开");
	    cause.printStackTrace();
		ctx.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}