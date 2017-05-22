package Tongxing;

import java.io.*;
/**
 * 
 * @author TCP客户端通信引擎;有不会的加入QQ群:426414437交流
 *
 */
public class SocketClient {
	public ClientConnect Clientconnect;
	public ReceiveMessage Receivemessage;
	public SendMessage Sendmessage;
	public HeartBeat Heartbeat;
	public LoginTimeOut LoginTimeout;
	public ClientState Clientstate;
	/**
	 * 启动连接
	 */
	public void Connect()
	{
		Clientstate=new ClientState();
		Clientconnect=new ClientConnect(this);//启动连接
		LoginTimeout=new LoginTimeOut(this);//启动超时判断
		LoginTimeout.StartTime(10);
	}
	/**
	 * 当突然掉线的时候触发这个方法
	 */
	public void lostClient(String Reason)
	{
		if(Clientstate.State==3)
			return;
		if(Clientstate.State!=2)
			loginFailure("服务器已满");
		else
		CloseEngine("突然掉线");//不重连了就关闭客户端，释放资源
	}
	/**
	 * 关闭这个通信引擎；释放所有的资源
	 */
	public void CloseEngine(String Reason)
	{
		if(Clientstate.State==3)
			return;//如果已经触发了关闭引擎就返回
		Clientstate.State=3;
			try {
				if(Clientstate.WorkSocket!=null)
				Clientstate.WorkSocket.close();
			} catch (IOException e) {
			}//关闭socket
			System.out.println(Reason);
		//告诉外面哥已经把引擎关闭了 已完全释放资源
	}
	
	/**
	 * 登录失败之后要处理的一些事情
	 */
	public void loginFailure(String Reason)
	{
		if(Clientstate.State==3)
			return;
    	CloseEngine(Reason);//不重连了就关闭客户端，释放资源
	}
	/**
	 * 当完全登录成功之后要处理的一些事情
	 */
	public void loginSuccess()
	{
		Clientstate.State=2;
		Heartbeat=new HeartBeat(this);
	}
}
