package Tongxing;

public class sjujj {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		SocketClient sc=new SocketClient();
		sc.Connect();
		try {
			Thread.sleep(50000000);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
