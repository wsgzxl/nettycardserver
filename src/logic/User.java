package logic;

import io.netty.channel.ChannelHandlerContext;


/*
 * 用户类
 * 
 */

public class Player {
   
	 private int id=0;//玩家ID
	 private String name="";//玩家姓名
	 private int nointalbe=0;//玩家在桌子上的序号
	 private ChannelHandlerContext ctx;//通信管道 
	 private Table table=null;//玩家所在桌子
	 
	 
}
