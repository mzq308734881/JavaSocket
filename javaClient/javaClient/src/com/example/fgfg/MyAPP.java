package com.example.fgfg;

import ItTxFace.ISendTx;
import ItTxFace.ITxClient;
import Tongxing.SocketClient;
import Tongxing.PublicTool.RandomPublic;
import android.app.Application;

public class MyAPP extends Application implements ITxClient{
	public int ConnectState=0;//0是没连接，1是正在连接，2是连接成功
	public ISendTx iSendTx;
	public MainActivity ma;
	public void onCreate()
	{
		super.onCreate();
	}
	/**
	 * 启动与服务器的通信
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
	 * 关闭引擎
	 */
	public void CloseConnect()
	{
		if(this.ConnectState!=2)
			return;
		this.ConnectState=0;
		iSendTx.CloseEngine("主动关闭");
	}
	@Override
	public void CloseEngineEnd(String Reason) {
		// TODO 自动生成的方法存根
		this.ConnectState=0;
		if(this.ma!=null)
		RandomPublic.HandlerTo(ma.handler, Reason);
	}
	@Override
	public void AcceptString(String Message) {
		// TODO 自动生成的方法存根
		if(this.ma!=null)
			RandomPublic.HandlerTo(ma.handler, Message);
	}
	@Override
	public void loginSuccess() {
		// TODO 自动生成的方法存根
		this.ConnectState=2;
		if(this.ma!=null)
			RandomPublic.HandlerTo(ma.handler, "连接成功");
	}
}
