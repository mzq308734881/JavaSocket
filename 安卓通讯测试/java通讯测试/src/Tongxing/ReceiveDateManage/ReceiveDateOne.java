package Tongxing.ReceiveDateManage;

import Tongxing.ClientState;
import Tongxing.PublicTool.ByteToDate;
/**
 * 
 * @author �Խ��յ������ݽ��е�һ�δ����ҳ���Ҫ�����ݣ��ѿյ�ȥ��
 *
 */
public class ReceiveDateOne {
	/**
	 * �ѻ������������ó��������Ұѻ�������գ�
	 * @param ClientState
	 * @param ��Ҫ�ĳ���
	 * @return ��ʽ���յ�������
	 */
   public static byte[] DateOneManage(ClientState stateOne,int insert)
   {
       byte[] receiveByte = null;
       if (stateOne.Buffer[0] == 0 && stateOne.BufferBackup != null && stateOne.BufferBackup.length >= insert)
       { receiveByte = stateOne.BufferBackup; stateOne.BufferBackup = null;}//��Ҫ���ڻ�������������С
       else { receiveByte = stateOne.Buffer;}
       byte[] haveDate = ByteToDate.ByteToByte(receiveByte, insert,0);
       for(byte buffer : stateOne.Buffer)
    	   buffer=0;
       return haveDate;
   }
}
