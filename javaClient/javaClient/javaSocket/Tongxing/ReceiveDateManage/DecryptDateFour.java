package Tongxing.ReceiveDateManage;


import java.io.UnsupportedEncodingException;

import Tongxing.ClientState;
import Tongxing.PublicTool.ByteToDate;
import Tongxing.PublicTool.EngineToDate;
import Tongxing.PublicTool.PasswordCode;

public class DecryptDateFour {
	/**
	 * ���ı���ͼƬ���ݽ��н���;
	 * @param ���յ�������
	 * @param ClientState
	 * @return EngineToDate
	 */
    public static EngineToDate deciphering(byte[] date, ClientState state)
    {
    	EngineToDate stateCode = null;
        if (date.length < 6)
            return stateCode;//�յ������ݲ���ȷ
        byte headDate = date[1];
        if (headDate == PasswordCode._textCode || headDate == PasswordCode._photographCode)//���ܵ������ı�����
        {
            int SendDateLabel = 0;
            EngineToDate toDate=ByteToDate.OffsetDecrypt(date, 2);
            byte[] dateAll=toDate.DateByte;
            SendDateLabel=toDate.SendDateLabel;
            byte[] ReplyDate = ByteToDate.CombinationTwo(PasswordCode._commonCode, PasswordCode._dateSuccess, SendDateLabel);//ֱ�ӻظ����ͳɹ�
            if (headDate == PasswordCode._textCode)
                    {
                        String str="";
						try {
							str = new String(dateAll,"UTF-8");
						} catch (UnsupportedEncodingException e) {
						}
                        stateCode = new EngineToDate(PasswordCode._textCode, str, ReplyDate);//�����������ı�����
                    }
            else
                    {
                        stateCode = new EngineToDate(PasswordCode._photographCode, dateAll, ReplyDate);//���ͳ�����ͼƬ����
                    }
                
        }
        else if (headDate == PasswordCode._dateSuccess)//���ݳɹ����ط�
        {
            int SendDateLabel = ByteToDate.ByteToInt(2, date);//�ҳ��ѷ����ݵı�ǩ
            if (headDate == PasswordCode._dateSuccess)
            {
                stateCode = new EngineToDate(headDate);
                if (SendDateLabel == state.SendDateLabel)
                { state.SendDate = null; }//�Ѿ��ɹ����ѷ����ݽ���ɾ��
            }
        }
        return stateCode;
    }
}
