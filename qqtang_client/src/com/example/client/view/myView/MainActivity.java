package com.example.client.view.myView;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.client.R;
import com.example.client.R.id;
import com.example.client.R.layout;
import com.example.client.R.menu;
import com.example.client.controller.Controller;
import com.example.client.model.Config;
import com.example.client.view.others.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * ��¼����
 */
public class MainActivity extends Activity {
	EditText name, password;
	Button login, reg;
	Controller controller;
	/**.
	 * ���Ƶ�¼���棬����Controller
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //��Ϸ������ֻ���������ý�������������������ͨ������
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //ȥ������
        getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN); //ȥ����ͷ
        this.setRequestedOrientation(
        		ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //ǿ�ƺ���
		setContentView(R.layout.activity_main);
		//��ȡ�ֱ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //���������е���Ļ�ߺͿ�ֵ
        if(dm.widthPixels > dm.heightPixels) {
        	Constant.SCREEN_WIDTH = dm.widthPixels;
        	Constant.SCREEN_HEIGHT = dm.heightPixels;
        } else {
        	Constant.SCREEN_HEIGHT = dm.widthPixels;
        	Constant.SCREEN_WIDTH = dm.heightPixels;
        }
		name = (EditText) findViewById(R.id.text_name);
		password = (EditText) findViewById(R.id.text_password);
		login = (Button) findViewById(R.id.btn_login);
		reg = (Button) findViewById(R.id.btn_reg);
		//ȡ��controller������
		controller = Controller.getController();
		controller.setHandler(signinHandler);
		login.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    String username = name.getText().toString();
			    String pass = password.getText().toString();
				String target = "hall-server1";
			    controller.signin(username, pass, target);
				//gotoChooseView();
			}
		}
		);
		reg.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				gotoReg();
			}
		}
		);
	}
	/*.
	 * �л���������
	 */
    public void gotoChooseView() {
		Intent intent = new Intent();
		Log.d(Config.LOG_TAG, "go to hall activity from main activity");
		intent.setClass(MainActivity.this, HallActivity.class);
		finish();
		startActivity(intent);
		
    }
    /*.
	 * �л�ע�����
	 */
    public void gotoReg() {
    	Intent intent = new Intent();
    	intent.setClass(MainActivity.this, RegActivity.class);
    	finish();
    	startActivity(intent);
    }
	/**.
	 * ���ý���˵�
	 *
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	/**.
	 * ���õ�¼����Handler������Controller��Ϣ
	 */
	Handler signinHandler = new Handler() {
		public void handleMessage(Message msg) {
		    JSONObject signinStatus;
			try {
				signinStatus = new JSONObject((String) msg.obj);
			if (signinStatus.getString("type").equals("signin")) {
			    String status = "";
				    if (signinStatus.has("status")) {
				        status =
				        signinStatus.getString("status");
				    }
					if (status != null
						&& status.equals("Success")) {
					    Toast.makeText(
					    MainActivity.this,
					    status,
					    Toast.LENGTH_SHORT).show();
					    gotoChooseView();
					} else {
						Toast.makeText(
						MainActivity.this,
						status,
						Toast.LENGTH_SHORT).show();
						//gotoChooseView();
					}
				}
			}  catch (JSONException e) {
				Toast.makeText(
				MainActivity.this,
				"execption",
				Toast.LENGTH_SHORT
				).show();
			}
		}
	 };
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			// �����˳��Ի���
			AlertDialog isExit = new
				AlertDialog.Builder(this).create();
			// ���öԻ������
			isExit.setTitle("��ʾ");
			// ���öԻ�����Ϣ
			isExit.setMessage("ȷ��Ҫ�˳���");
			// ���ѡ��ť��ע�����
			isExit.setButton("ȷ��", listener);
			isExit.setButton2("ȡ��", listener);
			// ��ʾ�Ի���
			isExit.show();
			}
	return false;
	}
	/*
	 * ���÷��ؼ��Ի��������
	 *
	 */
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����
				System.exit(0);
				break;
			case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���
				break;
			default:
				break;
			}
		}
	};
}
