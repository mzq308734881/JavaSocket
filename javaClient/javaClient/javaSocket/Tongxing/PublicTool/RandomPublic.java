package Tongxing.PublicTool;

import java.util.Random;

import android.os.Handler;
import android.os.Message;
/**
 * 
 * @author һ��������̬��;��������� �ӳٵ�
 *
 */
public class RandomPublic {
	private static Random _randomNumber = new Random(500);
    private static Random _randomTime = new Random();
    /**
     * ����ָ������ȡһ�������
     * @param ���ֵ
     * @return �����
     */
    public static int RandomNumber(int number)
    {
        return _randomNumber.nextInt(number);
    }
    /**
     * ����ʱ��Ϊ����ȡһ�������
     * @param ���ֵ
     * @return �����
     */
    public static int RandomTime(int number)
    {
        return _randomTime.nextInt(number);
    }
    /**
     * �ӳٶ��;��λ����
     * @param ���ٺ���
     */
    public static void TheadSleep(int millise)
    {
    	try {
			Thread.sleep(millise);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    /**
     * ���߳������߳�ֱ�Ӳ����ķ���
     * @param handler
     * @param ��Ҫ��������
     */
    public static void HandlerTo(Handler handler,String str)
    {
    	Message message = Message.obtain();
    	message.obj=str;
    	handler.sendMessage(message);
    }
}
