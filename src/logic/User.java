package logic;

import io.netty.channel.ChannelHandlerContext;


/*
 * 用户类
 * 
 */

public class User {
   
	 private int id=-1;//玩家ID
	 
	 public int getId()
	 {
		 return id;
	 }
	 
	 private String name="";//玩家姓名
	 
	 public String getName()
	 {
		 return name;
	 }
	 
	 private int tableindex=0;//玩家在桌子上的序号
	 
	 public int getTableIndex()
	 {
		 return tableindex;
	 }
	 
	 private ChannelHandlerContext ctx;//通信管道 
	 
	 public ChannelHandlerContext getHandlerContext()
	 {
		 return ctx;
	 }
	 
	 private Table table=null;//玩家所在桌子
	 
	 public Table getTable()
	 {
		 return table;
	 }
	 
}
