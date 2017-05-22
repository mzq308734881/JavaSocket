package Tongxing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * 
 * @author ���ӷ���������
 *
 */
public class ClientConnect implements Runnable {

	private Thread thread;
	private SocketClient sc;
	private ClientState State;
	ClientConnect(SocketClient socketClient) {
		this.sc = socketClient;
		this.State = sc.Clientstate;
		this.StartConnect();
	}
	/**
	 * ��������
	 */
	public void StartConnect() {
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}
	/**
	 * �����߳�
	 */
	@Override
	public void run() {
		try {
			State.WorkSocket = new Socket("127.0.0.1", 10020);
			this.ConnectSuccess();
		} catch (Exception e) {
            this.ConnectFailure();
		}
	}
	/**
	 * ���ӳɹ�
	 */
	private void ConnectSuccess()
	{
        try {
        	//��ȡ������������    
        	State.input = new DataInputStream(State.WorkSocket.getInputStream());
			//��������˷�������    
        	State.out = new DataOutputStream(State.WorkSocket.getOutputStream());
        	State.State=1;//�Ѿ�����
        	sc.Receivemessage=new ReceiveMessage(sc);
        	sc.Sendmessage=new SendMessage(sc);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			sc.loginFailure("������ʧ��");
		}
	}
	/**
	 * ����ʧ��
	 */
	private void ConnectFailure()
	{
		sc.loginFailure("���ӷ�����ʧ��");
	}
}