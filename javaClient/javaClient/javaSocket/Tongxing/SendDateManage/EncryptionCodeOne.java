package Tongxing.SendDateManage;

import Tongxing.PublicTool.PasswordCode;
/**
 * 
 * @author �԰��Ž��м��ܵ�һ����
 *
 */
public class EncryptionCodeOne {
	/**
	 * �԰��Ž��м���
	 * @param ����
	 * @return ����֮������
	 */
     public static byte[] EncryptionVerification(byte Verification)
     {
         byte[] haveDate = new byte[2];
         haveDate[0] = PasswordCode._verificationCode;
         haveDate[1] = Verification;
         return haveDate;
     }
}
