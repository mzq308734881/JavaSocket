package com.example.fgfg;

import ItTxFace.ISendTx;
import ItTxFace.ITxClient;
import Tongxing.SocketClient;
import Tongxing.PublicTool.RandomPublic;
import android.app.Application;

public class MyAPP extends Application implements ITxClient{
	public int ConnectState=0;//0��û���ӣ�1���������ӣ�2�����ӳɹ�
	public ISendTx iSendTx;
	public MainActivity ma;
	public void onCreate()
	{
		super.onCreate();
	}
	/**
	 * �������������ͨ��
	 */
	public void StartConnect(String IP,int port)
	{
		if(this.ConnectState!=0)
			return;
		this.ConnectState=1;
		iSendTx=new SocketClient(this);
		iSendTx.StartEngine(IP, port);
	}
	/**
	 * �ر�����
	 */
	public void CloseConnect()
	{
		if(this.ConnectState!=2)
			return;
		this.ConnectState=0;
		iSendTx.CloseEngine("�����ر�");
	}
	@Override
	public void CloseEngineEnd(String Reason) {
		// TODO �Զ����ɵķ������
		this.ConnectState=0;
		if(this.ma!=null)
		RandomPublic.HandlerTo(ma.handler, Reason);
	}
	@Override
	public void AcceptString(String Message) {
		// TODO �Զ����ɵķ������
		if(this.ma!=null)
			RandomPublic.HandlerTo(ma.handler, Message);
	}
	@Override
	public void loginSuccess() {
		// TODO �Զ����ɵķ������
		this.ConnectState=2;
		if(this.ma!=null)
			RandomPublic.HandlerTo(ma.handler, "���ӳɹ�");
	}
}
