package Tongxing;

import java.io.*;

import ItTxFace.ISendTx;
import ItTxFace.ITxClient;
import Tongxing.PublicTool.RandomPublic;

import com.example.fgfg.MainActivity;
/**
 * 
 * @author TCP客户端通信引擎;
 *
 */
public class SocketClient implements ISendTx{
	public ClientConnect Clientconnect;
	public ReceiveMessage Receivemessage;
	public SendMessage Sendmessage;
	public HeartBeat Heartbeat;
	public LoginTimeOut LoginTimeout;
	public ClientState Clientstate;
	public ITxClient iTxClient;
	public SocketClient(ITxClient ict)
	{
		this.iTxClient=ict;
	}
	/**
	 * 启动连接
	 */
	public void StartEngine(String Ip,int Port)
	{
	    Clientstate=new ClientState();
		Clientconnect=new ClientConnect(this,Ip,Port);//启动连接
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
				{Clientstate.WorkSocket.close();}
			} catch (IOException e) {
			}//关闭socket
			iTxClient.CloseEngineEnd(Reason);//告诉外面哥已经把引擎关闭了 已完全释放资源
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
		iTxClient.loginSuccess();//登录成功告诉外面
	}
	/**
	 * 发送文本信息
	 * @param 文本数据
	 */
	public void sendMessage(String data)
	{
		if(Clientstate==null || Clientstate.State!=2)
			return;
		this.Sendmessage.sendMessage(data);
	}
}
