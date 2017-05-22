package Tongxing.ReceiveDateManage;

import Tongxing.PublicTool.EngineToDate;
import Tongxing.PublicTool.PasswordCode;

/**
 * 
 * @author �����ݽ���ȫ��λ�ķ���͹��ˣ������ǹ������ͨ���ݣ��ļ����ݵ�
 *
 */
public class DecryptDateThree {
	/**
	 * �����ݽ���ȫ��λ�ķ���͹��ˣ������ǹ������ͨ���ݣ��ļ����ݵȷ���null��ʾΥ������
	 * @param ����
	 * @return EngineToDate
	 */
    public static EngineToDate Distribution(byte[] date)
    {
    	EngineToDate statecode = null;
        if(date.length<2)
            return statecode;
        byte headcode=date[0];
        if (headcode == PasswordCode._fileCode || headcode == PasswordCode._bigDateCode || headcode == PasswordCode._commonCode || headcode == PasswordCode._verificationCode)
            statecode = new EngineToDate(headcode, date);
        return statecode;
    }
}
