package core;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import io.netty.buffer.ByteBuf;

/**
 * @author: LYW
 * @qq: 1357098586
 * @email: 1357098586@qq.com
 * 
 * 协议内容  包长�?4 bytes)+id(2 bytes)+data(byte[])
 * 
 * 收发消息都是Message
 */
public class Message {
	
	private int id; //消息ID
	private byte[] data;//数据内容
	
	public Message(int id,byte[] data){
		this.id = id;
		this.data = data;
	}
	
	public int getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	/**
	 * 包体不能大于65536
	 * @return
	 */
	public byte[] toByteArray() throws Exception{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		output.writeShort(this.id);
		for(int i=0;i<this.data.length;i++) {
			output.writeByte(this.data[i]);
		}
		output.flush();
		return byteStream.toByteArray();
	}
	
	public String toString()
	{
		byte[] data=getData();
		String message=String.valueOf(getId());
		message+=" ";
		for(int i=0;i<data.length;i++)
		{
			message+=data[i];
			message+=" ";
		}
		return message;
	}
}
