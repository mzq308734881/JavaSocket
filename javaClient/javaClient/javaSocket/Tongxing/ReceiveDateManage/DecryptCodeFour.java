package Tongxing.ReceiveDateManage;

public class DecryptCodeFour {
	/**
	 * �԰��Ž��н���,����0˵���ǷǷ�����
	 * @param �յ��İ�������
	 * @return ��������
	 */
     public static byte DecryptVerification(byte[] Verification)
     {
         if (Verification.length != 2)
             return 0;
         return Verification[1];
     }
}
