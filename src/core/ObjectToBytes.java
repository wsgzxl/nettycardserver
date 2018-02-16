package core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
@author YHL
@qq: 1357098586
@version 创建时间：2018年2月6日 下午4:52:33 

 */

public class ObjectToBytes {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	 ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();
     DataOutputStream dataOutputStream = new DataOutputStream( bOutputStream );
     
   /**
    * 写入int类型
    * @param data 写入整形数据
    */
     public boolean writeInt(int data)
     {
    	 try {
			dataOutputStream.writeInt(data);
			return true;
		} catch (IOException e) {
		    logger.info("异常信息:"+e);
		    return false;
		}
     }
     
     /**
      * 写入短整形
      * @param data 
      */
     public boolean writeShort(short data)
     {
    	 try
    	 {
    		 dataOutputStream.writeShort(data);
    		 return true;
    	 } catch (IOException e) {
 		    logger.info("异常信息:"+e);
 		    return false;
 		 }
     }
     
     /**
      * 写入一个字节
      * @param data
      * @return
      */
     public boolean writeByte(byte data)
     {
    	 try
    	 {
    		 dataOutputStream.write(new byte[]{data});
    		 return true;
    	 }catch (IOException e) {
  		    logger.info("异常信息:"+e);
  		    return false;
  		 }
     }
     
     /**
      * 写入多个字节
      * @param data
      * @return
      */
     public boolean writeByte(byte[] data)
     {
    	 try
    	 {
    		 dataOutputStream.write(data);
    		 return true;
    	 } catch (IOException e) {
  		    logger.info("异常信息:"+e);
  		    return false;
  		 }
     }
     
     /**
      * 写入字符串
      * @param data
      * @return
      */
     public boolean writeString(String data)
     {
    	 try
    	 {
    		 byte[] bytes=data.getBytes();
    		 dataOutputStream.writeInt(data.length());
    		 dataOutputStream.write(bytes);
    		 return true;
    	 }catch(IOException e){
    		 logger.info("异常信息:"+e);
    		 return false;
    	 }
     }
     
     /**
      * 
      * @return
      */
     public byte[] getBytes()
     {
    	 return bOutputStream.toByteArray();
     }
     
}
