package Tongxing.PublicTool;
/**
 * 
 * @author һ����ͨ�Ĺ�����,�Խ�����ⲿ����һ������������
 *
 */
public class EngineToDate {
	public byte State = 0;//״̬��
	public byte[] DateByte = null;//�ֽ�����
    public String Datestring = null;//�ı�����
    public int SendDateLabel = 0;//�������ݵı�ǩ
    public byte[] ReplyDate = null;//ֱ�ӻظ�������
    /**
     * �����ı����ݵ�ʱ����Ҫ�õ���
     * @param �ı�����
     * @param �ı�����
     */
    public EngineToDate(byte i, String str)
    {
        State = i;
        Datestring = str;
    }
    /**
     * �����յ���ȷ���ı����ݵ�ʱ��Ҫ�õ���
     * @param �ļ�����
     * @param �ı�����
     * @param �ظ�������
     */
    public EngineToDate(byte i, String str,byte[] replyDate)
    {
        State = i;
        Datestring = str;
        ReplyDate = replyDate;
    }
    /**
     * �����յ���ȷ���ֽڼ����ݵ�ʱ��Ҫ�õ���
     * @param i
     * @param dateByte
     * @param replyDate
     */
    public EngineToDate(byte i, byte[] dateByte, byte[] replyDate)
    {
        State = i;
        DateByte = dateByte;
        ReplyDate = replyDate;
    }
    /**
     * ����ֻ��״̬����ֽڼ�
     * @param ����2,3
     * @param b�ֽ�����b
     */
    public EngineToDate(byte i,byte[] b)
    {
        State = i;
        DateByte = b;
    }
    /**
     * �����ݽ���ֱ�ӻظ�
     * @param �ֽڼ�
     */
    EngineToDate(byte[] replyDate)
    {
        ReplyDate = replyDate;
    }
    /**
     * ����ֻ���ѷ����ݺͱ��
     * @param ���
     * @param �ѷ�����
     */
    EngineToDate(int Label, byte[] dateByte)
    {
        SendDateLabel = Label;
        DateByte = dateByte;
    }
    /**
     * ����״̬����ѷ����ݱ��
     * @param state
     * @param Label
     */
    EngineToDate(byte state,int Label)
    {
        State = state;
        SendDateLabel = Label;
    }
    /**
     * ��Щ����Ҫ����
     * @param ����3,4
     */
    public EngineToDate(byte i)
    {
        State = i;
    }
}
