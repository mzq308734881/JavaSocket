package Tongxing;

import java.io.*;

import ItTxFace.ISendTx;
import ItTxFace.ITxClient;
import Tongxing.PublicTool.RandomPublic;

import com.example.fgfg.MainActivity;
/**
 * 
 * @author TCP�ͻ���ͨ������;
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
	 * ��������
	 */
	public void StartEngine(String Ip,int Port)
	{
	    Clientstate=new ClientState();
		Clientconnect=new ClientConnect(this,Ip,Port);//��������
		LoginTimeout=new LoginTimeOut(this);//������ʱ�ж�
		LoginTimeout.StartTime(10);
	}
	/**
	 * ��ͻȻ���ߵ�ʱ�򴥷��������
	 */
	public void lostClient(String Reason)
	{
		if(Clientstate.State==3)
			return;
		if(Clientstate.State!=2)
			loginFailure("����������");
		else
		CloseEngine("ͻȻ����");//�������˾͹رտͻ��ˣ��ͷ���Դ
	}
	/**
	 * �ر����ͨ�����棻�ͷ����е���Դ
	 */
	public void CloseEngine(String Reason)
	{
		if(Clientstate.State==3)
			return;//����Ѿ������˹ر�����ͷ���
		Clientstate.State=3;
			try {
				if(Clientstate.WorkSocket!=null)
				{Clientstate.WorkSocket.close();}
			} catch (IOException e) {
			}//�ر�socket
			iTxClient.CloseEngineEnd(Reason);//����������Ѿ�������ر��� ����ȫ�ͷ���Դ
	}
	
	/**
	 * ��¼ʧ��֮��Ҫ�����һЩ����
	 */
	public void loginFailure(String Reason)
	{
		if(Clientstate.State==3)
			return;
    	CloseEngine(Reason);//�������˾͹رտͻ��ˣ��ͷ���Դ
	}
	/**
	 * ����ȫ��¼�ɹ�֮��Ҫ�����һЩ����
	 */
	public void loginSuccess()
	{
		Clientstate.State=2;
		Heartbeat=new HeartBeat(this);
		iTxClient.loginSuccess();//��¼�ɹ���������
	}
	/**
	 * �����ı���Ϣ
	 * @param �ı�����
	 */
	public void sendMessage(String data)
	{
		if(Clientstate==null || Clientstate.State!=2)
			return;
		this.Sendmessage.sendMessage(data);
	}
}
