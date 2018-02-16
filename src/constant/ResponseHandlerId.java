package constant;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月6日 下午3:51:13 

回发给客户端的消息

 */

public enum ResponseHandlerId {

     _createroom(128),//创建房间成功
	 _nofindroom(129),//没有此房间
     _addtoroom(130),//添加到房间
     _leavetoroom(131), //离开房间
     _roomfull(132),//房间人数满
     _beforegame(133);//游戏未开始消息
     
     private final int value;
     
     private ResponseHandlerId(int value)
     {
    	 this.value=value;
     }
     
     public int getValue()
     {
    	 return value;
     }
}
