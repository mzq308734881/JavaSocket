package ItTxFace;
/**
 * 
 * @author �ⲿ����õ�һ���ӿڣ���Ҫ�Ƿ���Ϣ��,�в���ļ���QQȺ:426414437����
 *
 */
public interface ISendTx {
	/**
	 * ��������
	 */
    void StartEngine(String ip,int port);
    /**
     * �ر�����
     */
    void CloseEngine(String Reason);
  /**
   * �ͻ���������������ı�����
   * @param �ı�����
   */
    void sendMessage(String data);
}
