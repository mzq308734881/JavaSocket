package ItTxFace;
/**
 * 
 * @author ������������ӿ�����,�в���ļ���QQȺ:426414437����
 *
 */
public interface ITxClient {
	/**
	 * ������ȫ�رմ����˷���
	 * @param ���汻�رյ�ԭ��
	 */
	void CloseEngineEnd(String Reason);
	/**
	 * ���ӷ��������յ��ı���Ϣ��ʱ�򴥷��˷���
	 * @param ���յ����ı�����
	 */
	void AcceptString(String Message);
	/**
	 * ���ӳɹ���ʱ�򴥷��˷���
	 */
	void loginSuccess();
}
