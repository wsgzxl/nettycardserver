package dispatcher;

import handler.GameHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.GameRequest;
import domain.MessageQueue;
import domain.MessageWorker;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月23日 上午11:07:38 

消息处理

 */

public class HandlerDispatcher implements Runnable {

	
    private  final Logger logger=LoggerFactory.getLogger(getClass());
	
    private  ConcurrentHashMap<Integer,MessageQueue> sessionMsgQ=null;
    
    //线程
    private Executor messageExecutor;
    
    //消息分发
    private Map<Integer,GameHandler> handleMap=null;
    
    //是否在运行
    private boolean running;
    
    //休眠时间
    private long sleepTime;
    
    public HandlerDispatcher()
    {
    	this.sessionMsgQ=new ConcurrentHashMap<Integer,MessageQueue>();
    	this.running=true;
    	this.sleepTime=200L;
    	
    }
    
    public void setHandlerMap(Map<Integer,GameHandler> handlerMap)
    {
    	this.handleMap=handlerMap;
    }
    
    public Map<Integer,GameHandler>  getHandlerMap()
    {
    	return this.handleMap;
    }
    
    public void setMessageExecutor(Executor  messageExecutor)
    {
    	this.messageExecutor=messageExecutor;
    }
    
    public void setSleepTime(long sleepTime)
    {
    	this.sleepTime=sleepTime;
    }
    
    /*
     * 为链接添加消息队列
     */
    
    public void addMessageQueue(ChannelHandlerContext channel,MessageQueue messageQueue){
    	this.sessionMsgQ.put(channel.hashCode(), messageQueue);
    }
    
    /*
     * 为链接删除消息队列
     */
    public void removeMessageQueue(ChannelHandlerContext channel)
    {
    	MessageQueue queue=(MessageQueue)this.sessionMsgQ.remove(channel.handler());
    	if(queue!=null)
    	{
    		queue.clear();
    		queue=null;
    	}
    }
    
    /*
     * 添加消息
     */
    
    public void addMessage(GameRequest request)
    {
    	try
    	{
    		MessageQueue messageQueue=(MessageQueue)this.sessionMsgQ
    				.get(Integer.valueOf(request.GetChannelContext().hashCode()));
    		if(messageQueue==null)
    		{
    			messageQueue=new MessageQueue();
    			sessionMsgQ.put(Integer.valueOf(request.GetChannelContext().hashCode()),messageQueue);
    		    messageQueue.add(request);
    		}
    		else
    		{
    			messageQueue.add(request);
    		}
    	}
    	catch(Exception ex)
    	{
    		logger.error("异常信息:"+ex);
    	}
    }
    
	@Override
	public void run() {
	   
		while(this.running)
		{
			try
			{
				for(MessageQueue messageQueue:sessionMsgQ.values())
				{
					if((messageQueue!=null) && (messageQueue.size()>0) && (!messageQueue.isRunning()))
					{
						MessageWorker messageWorker=new MessageWorker(messageQueue);
						if(this.messageExecutor!=null)
						{
						    this.messageExecutor.execute(messageWorker);
						}
						else
						{
							logger.info("messageExecutor is null!");
						}
					}
				}
			}catch(Exception ex)
			{
				logger.error("异常信息："+ex);
			}
			try
			{
				Thread.sleep(this.sleepTime);
			}
			catch(Exception ex)
			{
			    logger.error("异常信息:"+ex);
			}
		}
		
	}
	
	public void stop()
	{
		running=false;
	}

}
