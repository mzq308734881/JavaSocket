package Tongxing;

public class sjujj {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SocketClient sc=new SocketClient();
		sc.Connect();
		try {
			Thread.sleep(50000000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
