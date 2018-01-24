package netty;

import net.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;



public class TcpEncoder extends MessageToByteEncoder<ResponseMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ResponseMessage message, ByteBuf out)
			throws Exception {
		try
		{
		   byte[] data=message.toByteArray();
		   int length=data.length;
		   out.writeInt(length);
		   out.writeBytes(data);
		}catch(Exception ex)
		{
		   
		}
	}
   
}
