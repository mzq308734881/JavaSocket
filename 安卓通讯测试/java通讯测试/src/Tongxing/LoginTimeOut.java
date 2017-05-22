package Tongxing;

import Tongxing.PublicTool.RandomPublic;

/**
 * 
 * @author �жϵ�¼��ʱ����;�������ӵ�����������
 * 
 */
public class LoginTimeOut implements Runnable {
	private SocketClient sc;
	private Thread thread;
	private int Time = 10;
	private ClientState State;
	LoginTimeOut(SocketClient socketClient) {
		this.sc = socketClient;
		this.State = sc.Clientstate;
	}

	/**
	 * ������ʱ�ж�
	 * 
	 * @param ���֮����Ϊ��ʱ
	 *            ,��λΪ��
	 */
	public void StartTime(int time) {
		this.Time = time;
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * ��ʱ�ж��߳�,���н����ʱ�򣬻��Զ������߳�
	 */
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		int timeout = 0;
		while (State.State < 2)// ��û�е�¼״̬��ر�״̬һֱѭ��
		{
			RandomPublic.TheadSleep(1000);
			timeout++;
			if (timeout >= Time) {
				this.LoginOut();
				break;
			}
		}
	}
	/**
	 * ��¼��ʱ
	 */
	private void LoginOut() {
		if(State.State!=2)//�������ʱ�պ������Ѿ���¼�ɹ���;�Ͳ��ùر���
		sc.loginFailure("��¼��ʱ");
	}
}
