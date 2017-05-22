package com.example.fgfg;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidStatic.StaticNetwork;
/**
 * 
 * @author ��ʱ���æ,��ʾ������Щ��ª;Ҳֻ�к�C#���������ı�����;,�в���ļ���QQȺ:426414437����
 *
 */
public class MainActivity extends ActionBarActivity implements OnClickListener{
	private static final String TAG = "MainActivity"; 
	private MyAPP myapp;
	private int param = 1; 
	private Button a1;
	private Button a2;
	private Button a3;
	private EditText w1;
	private EditText w2;
	private EditText w3;
	public Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate called."); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		a1=(Button) findViewById(R.id.a1);
		a2=(Button) findViewById(R.id.a2);
		a3=(Button) findViewById(R.id.a3);
		w1=(EditText) findViewById(R.id.w1);
		w2=(EditText) findViewById(R.id.w2);
		w3=(EditText) findViewById(R.id.w3);
		a1.setOnClickListener(this);
		a2.setOnClickListener(this);
		a3.setOnClickListener(this);
		myapp=(MyAPP) getApplication();
		myapp.ma=this;
		this.HandlerOk();
		w3.setText("�ȴ�����.....");
		/*a1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v){
				Intent intent=new Intent(); 
				intent.setAction("android.intent.action.TIGERTIAN");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				//intent.setClass(MainActivity.this, MainActivity01.class);
				startActivity(intent);
			}
		});*/
	}
	//[start]Activity�ļ�����������
	//Activity�������ߴӺ�̨���»ص�ǰ̨ʱ������  
    @Override  
    protected void onStart() {  
        super.onStart();  
        Log.i(TAG, "onStart called.");  
    }  
      
    //Activity�Ӻ�̨���»ص�ǰ̨ʱ������  
    @Override  
    protected void onRestart() {  
        super.onRestart();  
        Log.i(TAG, "onRestart called.");  
    }  
      
    //Activity�������ߴӱ����ǡ���̨���»ص�ǰ̨ʱ������  
    @Override  
    protected void onResume() {  
        super.onResume();  
        /*Log.i(TAG, "onResume called.");  
        if(myapp.ConnectState==2)
        	w3.setText("�Ѿ�����");
        else
        	w3.setText("��������");
        if(myapp.ConnectState==0)
        myapp.StartConnect();//���û��������������
*/    }  
      
    //Activity���ڻ�û�ʧȥ����ʱ������,��onResume֮���onPause֮��  
    /*@Override 
    public void onWindowFocusChanged(boolean hasFocus) { 
        super.onWindowFocusChanged(hasFocus); 
        Log.i(TAG, "onWindowFocusChanged called."); 
    }*/  
      
    //Activity�����ǵ������������ʱ������  
    @Override  
    protected void onPause() {  
        super.onPause();  
        Log.i(TAG, "onPause called.");  
        //�п�����ִ����onPause��onStop��,ϵͳ��Դ���Ž�Activityɱ��,�����б�Ҫ�ڴ˱���־�����  
    }  
      
    //�˳���ǰActivity������ת����Activityʱ������  
    @Override  
    protected void onStop() {  
        super.onStop();  
        Log.i(TAG, "onStop called.");     
    }  
      
    //�˳���ǰActivityʱ������,����֮��Activity�ͽ�����  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        Log.i(TAG, "onDestory called.");  
        myapp.ma=null;//��������ȫ�ֱ������ͷ���Դ
    }  
    /** 
     * Activity��ϵͳɱ��ʱ������. 
     * ����:��Ļ����ı�ʱ,Activity���������ؽ�;��ǰActivity���ں�̨,ϵͳ��Դ���Ž���ɱ��. 
     * ����,����ת������Activity���߰�Home���ص�����ʱ�÷���Ҳ�ᱻ����,ϵͳ��Ϊ�˱��浱ǰView�����״̬. 
     * ��onPause֮ǰ������. 
     */  
    @Override  
    protected void onSaveInstanceState(Bundle outState) {  
        outState.putInt("param", param);  
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);  
        super.onSaveInstanceState(outState);  
    }
      
    /** 
     * Activity��ϵͳɱ�������ؽ�ʱ������. 
     * ����:��Ļ����ı�ʱ,Activity���������ؽ�;��ǰActivity���ں�̨,ϵͳ��Դ���Ž���ɱ��,�û���������Activity. 
     * �����������onRestoreInstanceState���ᱻ����,��onStart֮��. 
     */  
    @Override  
    protected void onRestoreInstanceState(Bundle savedInstanceState) {  
        param = savedInstanceState.getInt("param");  
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);  
        super.onRestoreInstanceState(savedInstanceState);  
    }  
  //[end]
	/**
	 * ��ʼ�����̵߳�Handler
	 */
	private void HandlerOk()
	{
		handler=new Handler(){
			public void handleMessage(Message msg){
			String message=(String)msg.obj;
			w3.setText(message);
			}
			 };
	}
	/**
	 * ��ť����¼�
	 */
	@SuppressLint("HandlerLeak")
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		switch (v.getId()) {
		case R.id.a1: 
			Log.i(TAG, StaticNetwork.isMobileConnected(this)+""); 
	        myapp.StartConnect(w1.getText().toString(),Integer.valueOf(w2.getText().toString()));//���û��������������
			break;
        case R.id.a2:
        	myapp.iSendTx.sendMessage(w3.getText().toString());//������Ϣ
			break;
        case R.id.a3://�رտͻ���
        	Log.i(TAG, StaticNetwork.isWifiConnected(this)+""); 
        	myapp.CloseConnect();
			//Intent intent=new Intent(MainActivity.this,MainActivity01.class);
			//startActivity(intent);
			break;
		default:
			break;
		}
	}
}
