/**
 * 
 */
package Tongxing;

import java.util.Date;

import Tongxing.PublicTool.PasswordCode;
import Tongxing.PublicTool.RandomPublic;
import Tongxing.SendDateManage.EncryptionCodeOne;

/**
 * @author ������
 * 
 */
public class HeartBeat implements Runnable {
	private SocketClient sc;
	private Thread thread;
	private ClientState State;
	HeartBeat(SocketClient socketClient) {
		this.sc = socketClient;
		this.State = sc.Clientstate;
		this.StartHeart();
	}
	/**
	 * ���������߳�
	 */
	public void StartHeart() {
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * �����߳�
	 */
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while (sc.Clientstate.State == 2) {
			RandomPublic.TheadSleep(10000);
			long a=new Date().getTime()/1000 - State.NowTime.getTime()/1000 ;
			if(a>40)
			{this.HeartOut();
			break;
			}
			sc.Sendmessage.SendDate(EncryptionCodeOne.EncryptionVerification(PasswordCode._heartbeatCode));
			//�����������ݸ��������ķ���
			RandomPublic.TheadSleep(1000);
		}
	}
	/**
	 * ���ͻ���������ʱ
	 */
	private void HeartOut()
	{
		sc.lostClient("������ʱ");
	}
}
