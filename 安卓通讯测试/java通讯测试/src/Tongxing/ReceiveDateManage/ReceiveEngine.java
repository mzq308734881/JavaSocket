package Tongxing.ReceiveDateManage;

import java.util.ArrayList;
import java.util.Date;

import Tongxing.ClientState;
import Tongxing.SocketClient;
import Tongxing.PublicTool.EngineToDate;
import Tongxing.PublicTool.PasswordCode;
import Tongxing.SendDateManage.EncryptionCodeOne;

/**
 * 
 * @author ���յ�����ȫ������������;Ȼ������Ҫ�����ݸ����÷�!
 * 
 */
public class ReceiveEngine {
	private SocketClient sc;
private ClientState state;
	public ReceiveEngine(SocketClient socketClient) {
		this.sc = socketClient;
		this.state=sc.Clientstate;
	}
/**
 * �������ݵ����棻�����յ�����֮��ȫ�������������������
 * @param ���ݵ���Ч����
 */
	public void ReceiveDecrypt(int insert) {
		if (insert < 1)
			return;
		byte[] ReceiveDate = ReceiveDateOne.DateOneManage(state,insert);
		// �����������������ó�����Ȼ��Ի�������������
		if (ReceiveDate == null)
			return;
		state.NowTime = new Date();
		ArrayList<byte[]> listDate = DecryptTcpTwo.DecryptPackage(ReceiveDate,state);
        //��TCP����ȫ���������;�ŵ������ѭ������ȥ����
        for (byte[] date : listDate)
        {
        	EngineToDate statecode =DecryptDateThree.Distribution(date);
        	if(statecode==null)
        		continue;
            TcpCodeManage(statecode);
        }
	}
	/**
	 * �ѷ������������ݽ��й��ࣻ�簵�ţ���ͨ���ݵ�
	 * @param EngineToDate
	 */
    private void TcpCodeManage(EngineToDate statecode)
    {
        if (statecode.State == PasswordCode._verificationCode)//˵���ǰ��ţ��׸����Ŵ�������
        {
            byte haveDate = DecryptCodeFour.DecryptVerification(statecode.DateByte);
            VerificationCodeManage(haveDate);
        }
        else
        {
            codeManage(statecode); }
    }
    /**
     * �Ѱ��ŷ���ɻ�������
     * @param ����
     */
    private void VerificationCodeManage(byte haveDate)
    {
        switch (haveDate)
        {
            case 0://������Ҫ������
                break;
            case PasswordCode._heartbeatCode://��������Ϣ
                //stateOne.HeartTime = DateTime.Now;
                break;
            case PasswordCode._serverToClient://�ͻ��˺ͷ���˰�����ȷ���ѵ�¼;
            	sc.Sendmessage.SendDate(EncryptionCodeOne.EncryptionVerification(PasswordCode._clientToServer));
                sc.loginSuccess();//��ȫ��¼�ɹ������˷���
                break;
            case PasswordCode._clientCloseCode://������Ҫ��ͻ��˹ص�;
                sc.CloseEngine("ǿ�ƹر�");
                break;
        }
    }
  /**
   * �ѷǰ������ݽ��й��ࣻ�ֳ���ͨ���ݣ��ļ����ݵ�;
   * @param EngineToDate
   */
    private void codeManage(EngineToDate statecode)
    {
    	EngineToDate stateCode = null;
        switch (statecode.State)
        {
            case PasswordCode._commonCode://��ͨ������Ϣ;�׸���ͨCodeȥ����
                stateCode = DecryptDateFour.deciphering(statecode.DateByte, state);
                if(stateCode!=null)
                CommonCodeManage(stateCode);
                break;
            /*case PasswordCode._bigDateCode://�׸��ְ�Codeȥ����
                stateCode = EncryptionDecryptSeparateDate.FileDecrypt(statecode.DateByte, stateOne);
                CommonCodeManage(stateOne, stateCode);
                break;
            case PasswordCode._fileCode://�׸��ļ�������ȥ�����������null�Ͳ��÷�����
                byte[] haveDate = FileStart.ReceiveDateTO(statecode.DateByte, stateOne);
                if (haveDate != null)
                    Send(stateOne, haveDate);
                break;*/
        }
    }
    private void CommonCodeManage(EngineToDate stateCode)
    {
        switch (stateCode.State)
        {
            case PasswordCode._textCode://�ı���Ϣ
            	sc.Sendmessage.SendDate(stateCode.ReplyDate);
            	System.out.println(stateCode.Datestring);
                //���յ��ı���Ϣ���ⲿ����
                break;
            case PasswordCode._photographCode://ͼƬ��Ϣ
            	sc.Sendmessage.SendDate(stateCode.ReplyDate);
                //���յ�ͼƬ��Ϣ;���ⲿ����
                break;
            case PasswordCode._dateSuccess://���ݷ��ͳɹ�
            	state.SendDate = null;
            	System.out.println("���ͳɹ�");
                //�ҵ������Ѿ��ɹ��յ�,���ⲿ����
                break;
            case 0://˵���������ֻҪֱ�ӻظ����Է��Ϳ�����
            	sc.Sendmessage.SendDate(stateCode.ReplyDate);
                break;
        }
    }
}
