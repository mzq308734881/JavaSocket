package Tongxing.SendDateManage;

import Tongxing.PublicTool.ByteToDate;
import Tongxing.PublicTool.PasswordCode;

public class EncryptionTcpTwo {
	/**
	 * ��TCP���ͽ���ճ�����ܣ�
	 * @param Ҫ���ܵ�����
	 * @return ����֮�������
	 */
    public static byte[] EncryptionPackage(byte[] sendDate)
    {
        byte[] dateAll = new byte[sendDate.length + 8];
        ByteToDate.IntToByte(PasswordCode._stickPackageCode, 0, dateAll);
        ByteToDate.IntToByte(sendDate.length, 4, dateAll);
        ByteToDate.BytesToBytes(sendDate, 8, dateAll);
        return dateAll;
    }
}
