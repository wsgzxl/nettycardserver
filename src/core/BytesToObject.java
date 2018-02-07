package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月7日 下午5:19:23 

 */

public class BytesToObject {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	ByteArrayInputStream bInputStream;
	DataInputStream dataInputStream;
    
    public BytesToObject(byte[] data)
    {
    	bInputStream = new ByteArrayInputStream(data);
    	dataInputStream=new DataInputStream(bInputStream);
    }
    
    public int readInt()
    {
    	try {
			return dataInputStream.readInt();
		} catch (IOException e) {
			logger.error("readint error!");
			return -1;
		}
    }
    
    public short readshort()
    {
    	try {
			return dataInputStream.readShort();
		} catch (IOException e) {
			logger.error("readshort error!");
			return -1;
		}
    }
    
    public byte readbyte()
    {
    	try {
			return dataInputStream.readByte();
		} catch (IOException e) {
			logger.error("readbyte error!");
			return -1;
		}
    }
    
}
