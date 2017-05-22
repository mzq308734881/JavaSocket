package Tongxing;

import java.io.IOException;

import Tongxing.PublicTool.EngineToDate;
import Tongxing.PublicTool.PasswordCode;
import Tongxing.SendDateManage.EncryptionDateOne;
import Tongxing.SendDateManage.EncryptionTcpTwo;
/**
 * 
 * @author һ���������ݵ���
 *
 */
public class SendMessage implements Runnable {
	private SocketClient sc;
	private Thread thread;
	private byte[] Date;
	private ClientState State;
	SendMessage(SocketClient socketClient) {
		this.sc = socketClient;
		this.State=sc.Clientstate;
	}
	/**
	 * ��������������ݣ��������ԭʼ��
	 * @param �ֽڼ�����
	 */
	public void SendDate(byte[] HaveDate) {
		this.Date=EncryptionTcpTwo.EncryptionPackage(HaveDate);
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			sc.Clientstate.out.write(Date);
		} catch (IOException e) {
			
		}
	}
	/**
	 * ������������ı�����
	 * @param �ı�����
	 */
    public void sendMessage(String data)
    {
    	EngineToDate stateCode = new EngineToDate(PasswordCode._textCode, data);
        byte[] sendDate = EncryptionDateOne.encryption(stateCode, State);
        State.SendDate = sendDate;
        SendDate(sendDate);
    }
}
