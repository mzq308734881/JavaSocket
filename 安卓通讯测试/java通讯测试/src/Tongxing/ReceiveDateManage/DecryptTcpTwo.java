package Tongxing.ReceiveDateManage;

import java.util.ArrayList;
import java.util.Arrays;

import Tongxing.ClientState;
import Tongxing.PublicTool.ByteToDate;
import Tongxing.PublicTool.PasswordCode;
/**
 * 
 * @author ճ��������
 *
 */
public class DecryptTcpTwo {
	/**
	 * ��TCPճ�����ݽ��н��ܣ������������İ�ͨ��������ʽ���ظ��ͻ�
	 * @param ���յ�������
	 * @param �ϴβ���������
	 * @return ���ص����ݼ���;
	 */
    public static ArrayList<byte[]> DecryptPackage(byte[] receiveDate, ClientState residualpackage)
    {
    	ArrayList<byte[]> listDate = new ArrayList<byte[]>();
        if (receiveDate.length < 4)
            return listDate;
        while (true)
        {
            byte[] haveDate = DecryptByte(receiveDate, residualpackage);
            if (haveDate != null)
                listDate.add(haveDate);
            if (haveDate == null || residualpackage.BufferBackup == null)
                break;
            if (residualpackage.BufferBackup.length < 4)
                break;
            receiveDate = residualpackage.BufferBackup; residualpackage.BufferBackup = null;
        }
        return listDate;
    }
    private static byte[] DecryptByte(byte[] receiveDate, ClientState residualpackage)
    {
    	byte[] BufferBackup=residualpackage.BufferBackup;
        byte[] haveDate = null;
        boolean ddd=false;
        int stickPackageCode = ByteToDate.ByteToInt(0, receiveDate);
        if (stickPackageCode != PasswordCode._stickPackageCode)
        {//��һ���밵�Ų���ͬ��˵�������п�����ǰ��һ������ȥ��
            ddd = true;
            if (BufferBackup != null)
            { //˵�������ǰ��һ������������
                byte[] addDate = new byte[receiveDate.length + BufferBackup.length];
                ByteToDate.BytesToBytes(BufferBackup, 0, addDate);
                ByteToDate.BytesToBytes(receiveDate, BufferBackup.length, addDate);
                receiveDate = addDate; residualpackage.BufferBackup = null;
            }
            else//��֪����ʲô���ݣ�ֱ���ӵ�
            { return null; }
        }
        if (ddd)
            stickPackageCode = ByteToDate.ByteToInt(0, receiveDate);
        if (stickPackageCode == PasswordCode._stickPackageCode)
        { //��һ��������ͬ��˵����һ���°���ʼ�ˣ�������һ��ǰ��Ĳ�����
            if (receiveDate.length < 9)
            { BufferBackup = receiveDate; return null; }
            int datelenth = ByteToDate.ByteToInt(4, receiveDate);
            residualpackage.BufferBackup = null;//�Բ������ݽ��г�ʼ��
            if (datelenth == receiveDate.length - 8)
            { //˵�������յ������ݾ���һ��������
                haveDate = Arrays.copyOfRange(receiveDate, 8, datelenth+8);
            }
            else if (datelenth > receiveDate.length - 8)
            { //˵������û������ȫ,�����ݷ��ڲ������������һ�ֽ���
            	BufferBackup = receiveDate;
            }
            else
            {//˵�������ٶ�����ճ��һ��
                haveDate =Arrays.copyOfRange(receiveDate, 8, datelenth+8);
                residualpackage.BufferBackup = Arrays.copyOfRange(receiveDate, 8 + datelenth, receiveDate.length);
                //��ʣ�µ���Ȼ���ڲ�����
            }
        }
        return haveDate;
    }
}
