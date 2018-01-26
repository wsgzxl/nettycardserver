package logic.Manager;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import logic.Table;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年1月25日 下午3:58:28 

桌子管理

 */

public class TableManager {

	
	private TableManager _instance=null;
	
	private TableManager()
	{
		
	}
	
	public TableManager getInstance()
	{
		if(null==_instance)
		{
			_instance=new TableManager();
		}
		return _instance;
	}
	
	private ConcurrentHashMap<Integer,Table> tables=new ConcurrentHashMap<Integer,Table>();
	
}
