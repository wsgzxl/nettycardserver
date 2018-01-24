package netty;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.RequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TcpDecoder extends ByteToMessageDecoder  {

	
	private  Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) {

       
		try
		{
		 
			if(in.readableBytes()<4)
			{
				return;
			}
			in.markReaderIndex();
			int totalLength=in.readInt();
			if(in.readableBytes()<totalLength)
			{
				in.resetReaderIndex();
				return;
			}
			
			int requestId=in.readUnsignedShort();
			int bodylength=totalLength-2;
			byte[] bodydata=new byte[bodylength];
			in.readBytes(bodydata);
			out.add(new RequestMessage(requestId,bodydata));
		}
		catch(Exception ex)
		{
			logger.error("Òì³£ÐÅÏ¢:"+ex);
		}
		
	}
   
}
