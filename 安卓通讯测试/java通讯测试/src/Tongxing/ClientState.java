package Tongxing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;

public class ClientState {
	public Socket WorkSocket;
	public byte[] Buffer=new byte[1024];// ������
	public byte[] SendDate = null;// �ѷ��͵�����
	public int SendDateLabel = 0;// �������ݵı�ǩ
	public byte[] BufferBackup = null;// ���ݻ�����;��Ҫ�ǻ�������ʱ����Ҫ�������С��ʱ���õ���
	public Date NowTime = new Date();
	public DataInputStream input;
	public DataOutputStream out;
	public int State=0;//�ͻ���״̬��0����û�κε�¼��1������δ��¼��2��ʾ�ѵ�¼,3��ʾ�Ѵ����ر�����
}
