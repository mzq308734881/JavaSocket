package Tongxing.PublicTool;

import java.util.Arrays;

public class ByteToDate {
	/**
	 * ��һ�������Ͷ����ֽ�����µ��ֽ�����
	 * @param �ֽ�
	 * @param �ֽ�
	 * @param ����
	 * @return ��ɺ������
	 */
    public static byte[] CombinationTwo(byte a, byte b, int c)
    {
        byte[] dateOverall = new byte[6];
        dateOverall[0] = a;
        dateOverall[1] = b;
        IntToByte(c, 2, dateOverall);
        return dateOverall;
    }
    /**
     * ��һ���ֽ���������ȡ��һ������
     * @param ��ʼλ��
     * @param �ֽ�����
     * @return ȡ��������
     */
    public static int ByteToInt(int a, byte[] b)
    {
        byte[] inta = Arrays.copyOfRange(b, a, a+4);
        int dl = byte2int(inta);
        return dl;
    }
    /**
    * int32ת��Ϊ�����ƣ�4���ֽڣ�
    * @param i ��ת��������
    * @return ����4�ֽڶ�������
    */
    private static byte[] int2byte(int i){
    byte[] res = new byte[4];
    res[0] = (byte)i;
    res[1] = (byte)(i>>>8);
    res[2] = (byte)(i>>>16);
    res[3] = (byte)(i>>>24);
    return res;
    }
    /**
    * 4�ֽڶ�������ת��Ϊint32������
    * @param bytes 4�ֽڵĶ�������
    * @return int32����
    */
    private static int byte2int(byte[] bytes){
    int res = (((bytes[3]<<24)>>>24)<<24)|(((bytes[2]<<24)>>>24)<<16)|(((bytes[1]<<24)>>>24)<<8)|((bytes[0]<<24)>>>24);
    return res;
    }
    /**
    * ������longת��Ϊ8�ֽڵĶ�������
    * @param l ������long
    * @return 8�ֽڵĶ�������
    */
    private static byte[] long2byte(long l){
    byte[] res = new byte[8];
    res[0] = (byte)l;
    res[1] = (byte)(l>>>8);
    res[2] = (byte)(l>>>16);
    res[3] = (byte)(l>>>24);
    res[4] = (byte)(l>>>32);
    res[5] = (byte)(l>>>40);
    res[6] = (byte)(l>>>48);
    res[7] = (byte)(l>>>56);
    return res;
    }
    /**
    * 8�ֽڵĶ�������ת��Ϊ������long
    * @param bytes 8�ֽڵĶ�������
    * @return ������long
    */
    private static long byte2long(byte[] bytes){
    long l0 = bytes[7];
    long l1 = bytes[6];
    long l2 = bytes[5];
    long l3 = bytes[4];
    long l4 = bytes[3];
    long l5 = bytes[2];
    long l6 = bytes[1];
    long l7 = bytes[0];
    long res = (l0<<56)|(((l1<<56)>>>56)<<48)|(((l2<<56)>>>56)<<40)|(((l3<<56)>>>56)<<32)|(((l4<<56)>>>56)<<24)|(((l5<<56)>>>56)<<16)|(((l6<<56)>>>56)<<8)|((l7<<56)>>>56);
    return res;
    }
    /**
     * ��һ���ֽ���������ȡ��һ��������
     * @param a��ʼλ��
     * @param �ֽ�����
     * @return ������
     */
    public static long ByteToLong(int a, byte[] b)
    {
        byte[] inta =Arrays.copyOfRange(b,a,a+8);
        long dl = byte2long(inta);
        return dl;
    }
    /**
     * ��һ������Copy��һ���ֽ������ָ��λ��
     * @param ����
     * @param ��ʼλ��
     * @param c�ֽ�����
     */
    public static void IntToByte(int a, int b, byte[] c)
    {
        byte[] inta = int2byte(a);
        BytesToBytes(inta,b,c);
    }
    /**
     * ��һ�������鸴�Ƶ�һ���������ָ��λ��
     * @param ������
     * @param ָ��λ��
     * @param ������
     */
    public static void BytesToBytes(byte[] from,int of,byte[] to)
    {
    	for(byte dd : from)
        {
    		to[of]=dd;
        	of++;
        }
    }
    /**
     * ��һ��������Copy��һ���ֽ������ָ��λ��
     * @param ������
     * @param ��ʼλ��
     * @param �ֽ�����
     */
    public static void IntToByte(long a, int b, byte[] c)
    {
        byte[] inta = long2byte(a);
        BytesToBytes(inta,b,c);
    }
    /**
     * ��һ������ȡ��ָ������
     * @param ����
     * @param ����
     * @param ��ʼλ��
     * @return ���ص�����
     */
    public static byte[] ByteToByte(byte[] a, int b,int index)
    {
            byte[] haveDate =Arrays.copyOfRange(a,index,b);
            return haveDate;
    }
    /**
     * ͨ��ƫ������һ�����ݽ��м��ܣ��ѱ�ǩ�����ݵ���һ�����壻
     * @param ��Ҫ���ܵ�����
     * @param ��ǩ
     * @param ƫ����
     * @return ������ɵ�����
     */
    public static byte[] OffsetEncryption(byte[] date, int sendDateLabel, int offset)
    {
        byte[] dateOverall = new byte[date.length + 4 + offset];
        IntToByte(sendDateLabel, offset, dateOverall);
        BytesToBytes(date,4 + offset,dateOverall);
        return dateOverall;
    }
    /**
     * ͨ��ƫ������һ�����ݽ��н��ܣ�
     * @param �����ܵ�����
     * @param ƫ����
     * @return ���ܳ��������ݺͱ�ǩ�γ��µ�EngineToDate���������е�DateByte   SendDateLabel
     */
    public static EngineToDate OffsetDecrypt(byte[] date,int offset)
    {
    	int sendDateLabel = ByteToInt(offset, date);
        byte[] dateOverall = Arrays.copyOfRange(date, 4 + offset, date.length);
        return new EngineToDate(sendDateLabel,dateOverall);
    }
}
