package Tongxing.SendDateManage;

import java.io.UnsupportedEncodingException;

import Tongxing.ClientState;
import Tongxing.PublicTool.ByteToDate;
import Tongxing.PublicTool.EngineToDate;
import Tongxing.PublicTool.PasswordCode;
import Tongxing.PublicTool.RandomPublic;
/**
 * 
 * @author �����ݽ��е�һ�μ���
 *
 */
public class EncryptionDateOne {
	/**
	 * ���ı���ͼƬ���ݽ��м���;������ȳ������ƣ�ֱ���׸��ļ���������
	 * @param EngineToDate
	 * @param ClientState
	 * @return ����֮�������
	 */
    public static byte[] encryption(EngineToDate stateCode,ClientState state)
    {
	byte[] returnByte = null;
    if (stateCode.State == PasswordCode._textCode)
    {
        byte textCode = PasswordCode._textCode;
        byte[] date=null;
		try {
			date = stateCode.Datestring.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
        returnByte = encryptionTemporary(date, textCode, state);
    }
    else if (stateCode.State == PasswordCode._photographCode)
    {
        byte photographCode = stateCode.State;
        returnByte = encryptionTemporary(stateCode.DateByte, photographCode, state);
    }
    return returnByte;
}
    /**
     * һ����ͨ�Ķ��������岿�ֽ��м��ܣ�
     * @param Ҫ���ܵ�����
     * @param textCode
     * @param ClientState
     * @return ����
     */
private static byte[] encryptionTemporary(byte[] date, byte textCode,ClientState state)
{
    state.SendDateLabel = RandomPublic.RandomNumber(16787);//�����͵����ݽ��б��
    byte[] dateOverall = ByteToDate.OffsetEncryption(date, state.SendDateLabel, 2);
    dateOverall[0] = PasswordCode._commonCode; dateOverall[1] = textCode;
    return dateOverall;
}
}
