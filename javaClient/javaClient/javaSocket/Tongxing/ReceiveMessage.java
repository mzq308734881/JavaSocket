/**
 * 
 */
package Tongxing;

import java.io.IOException;

import Tongxing.ReceiveDateManage.ReceiveEngine;

/**
 * @author һ���������ݵĹ���;�����������صĹ������������
 * 
 */
public class ReceiveMessage implements Runnable {
	private SocketClient sc;
	private Thread thread;
	private ClientState State;
    private ReceiveEngine Re;
	ReceiveMessage(SocketClient socketClient) {
		this.sc = socketClient;
		this.State = sc.Clientstate;
		Re=new ReceiveEngine(sc);
		this.StartReceive();
	}

	/**
	 * ��������
	 */
	public void StartReceive() {
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		try {
			int i = sc.Clientstate.input.read(State.Buffer);
			this.StartReceive();
			Re.ReceiveDecrypt(i);//������ȥ����
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			sc.lostClient("ͻȻ����");
		}
	}
}
