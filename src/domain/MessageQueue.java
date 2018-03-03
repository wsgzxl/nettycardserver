package domain;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月22日 下午7:57:46 

 */

public class MessageQueue {
    
	private Queue<GameRequest> requestQueue=null;
	private volatile boolean running=false;
	
	public MessageQueue()
	{
		this.requestQueue=new ConcurrentLinkedQueue<GameRequest>();
		this.setRunning(false);
	}
	
	public Queue<GameRequest> getRequestQueue()
	{
		return this.requestQueue;
	}
	
	public synchronized void clear()
	{
		this.requestQueue.clear();
		this.requestQueue=null;
	}
	
	public synchronized int size()
	{
		return this.requestQueue!=null?this.requestQueue.size():0;
	}
	
	public synchronized boolean add(GameRequest request)
	{
		return this.requestQueue.add(request);
	}
	
	public void setRunning(boolean running)
	{
		this.running=running;
	}
	
	public  boolean isRunning()
	{
		return this.running;
	}
	
}
