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
 * @author 因时间匆忙,演示程序有些简陋;也只有和C#服务器的文本交流;,有不会的加入QQ群:426414437交流
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
		w3.setText("等待启动.....");
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
	//[start]Activity的几种生命周期
	//Activity创建或者从后台重新回到前台时被调用  
    @Override  
    protected void onStart() {  
        super.onStart();  
        Log.i(TAG, "onStart called.");  
    }  
      
    //Activity从后台重新回到前台时被调用  
    @Override  
    protected void onRestart() {  
        super.onRestart();  
        Log.i(TAG, "onRestart called.");  
    }  
      
    //Activity创建或者从被覆盖、后台重新回到前台时被调用  
    @Override  
    protected void onResume() {  
        super.onResume();  
        /*Log.i(TAG, "onResume called.");  
        if(myapp.ConnectState==2)
        	w3.setText("已经连接");
        else
        	w3.setText("正在连接");
        if(myapp.ConnectState==0)
        myapp.StartConnect();//如果没有启动就连接上
*/    }  
      
    //Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后  
    /*@Override 
    public void onWindowFocusChanged(boolean hasFocus) { 
        super.onWindowFocusChanged(hasFocus); 
        Log.i(TAG, "onWindowFocusChanged called."); 
    }*/  
      
    //Activity被覆盖到下面或者锁屏时被调用  
    @Override  
    protected void onPause() {  
        super.onPause();  
        Log.i(TAG, "onPause called.");  
        //有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据  
    }  
      
    //退出当前Activity或者跳转到新Activity时被调用  
    @Override  
    protected void onStop() {  
        super.onStop();  
        Log.i(TAG, "onStop called.");     
    }  
      
    //退出当前Activity时被调用,调用之后Activity就结束了  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        Log.i(TAG, "onDestory called.");  
        myapp.ma=null;//结束了在全局变量里释放资源
    }  
    /** 
     * Activity被系统杀死时被调用. 
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死. 
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态. 
     * 在onPause之前被调用. 
     */  
    @Override  
    protected void onSaveInstanceState(Bundle outState) {  
        outState.putInt("param", param);  
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);  
        super.onSaveInstanceState(outState);  
    }
      
    /** 
     * Activity被系统杀死后再重建时被调用. 
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity. 
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后. 
     */  
    @Override  
    protected void onRestoreInstanceState(Bundle savedInstanceState) {  
        param = savedInstanceState.getInt("param");  
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);  
        super.onRestoreInstanceState(savedInstanceState);  
    }  
  //[end]
	/**
	 * 初始化跨线程的Handler
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
	 * 按钮点击事件
	 */
	@SuppressLint("HandlerLeak")
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.a1: 
			Log.i(TAG, StaticNetwork.isMobileConnected(this)+""); 
	        myapp.StartConnect(w1.getText().toString(),Integer.valueOf(w2.getText().toString()));//如果没有启动就连接上
			break;
        case R.id.a2:
        	myapp.iSendTx.sendMessage(w3.getText().toString());//发送信息
			break;
        case R.id.a3://关闭客户端
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
