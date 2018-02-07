package constant;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月6日 下午3:51:13 

回发给客户端的消息

 */

public enum ResponseHandlerId {

     _createroom(128),//创建房间成功
	 _nofindroom(129);//没有此房间
     
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
