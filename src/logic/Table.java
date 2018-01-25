package logic;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ResponseMessage;


/*
 * 桌子类
 */

public class Table {
   
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private List<User> players=new ArrayList<User>(); //桌子上的人数
	
	public List<User> getUsers()
	{
		return players;
	}
	
	private Integer tableno=-1; //桌号
	
	public Integer getTableno()
	{
		return tableno;
	}
	
	public void sendToUser(User user,ResponseMessage message)
	{
		ChannelHandlerContext ctx=user.getHandlerContext();
		if(null==ctx)
		{
			logger.info("ctx is error!");
			return;
		}
		user.getHandlerContext().writeAndFlush(message);
	}
	
	public void sendToUsers(User[] users,ResponseMessage message)
	{
		for(int i=0;i<users.length;i++)
		{
			ChannelHandlerContext ctx=users[i].getHandlerContext();
			if(null==ctx)
			{
				logger.error("ctx is error!");
				return;
			}
			ctx.writeAndFlush(message);
		}
	}
	
	public void sendToAll(ResponseMessage message)
	{
		for(int i=0;i<players.size();i++)
		{
			ChannelHandlerContext ctx=players.get(i).getHandlerContext();
			if(null==ctx)
			{
				logger.info("ctx is null!");
				return;
			}
			ctx.writeAndFlush(message);
		}
	}
	
}
