package com.example.client.view.myView;

import com.example.client.model.Config;
import com.example.client.view.others.Constant;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


/**.
 * ��Ϸ����Activity��
 */
public class GameActivity extends Activity {
	GameView gameView;
	/**.
	 * GameplayView��Ա��������Ϸ��ͼ
	 */	
	GameplayView gameplayView; //��Ϸ���������
	public boolean backgroundsoundFlag = true; //�������ֿ����ı�־λ��Ĭ��Ϊ����
	/**.
	 * ���캯�������ƺ���
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(
         ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //ǿ�ƺ���    	
        super.onCreate(savedInstanceState); //��Ϸ������ֻ���������ý�������������������ͨ������
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //ȥ������
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN); //ȥ����ͷ

        Bundle bundle = this.getIntent().getExtras();
        int type = bundle.getInt("type");
        if (type == 0) {
            goToGameView();
        } else {
        	goToGameView2();
        }
    }

    public void goToLogin() {
		Intent intent = new Intent();
		intent.setClass(GameActivity.this, MainActivity.class);
		finish();
		startActivity(intent);
    }
    /**.
     * �����������Ϸ����
     */
	public void goToGameView() {
		if (gameView == null) {
			gameView = new GameView(this);
    	}
    	setContentView(gameView);
    	gameView.requestFocus(); //��ȡ����
    	gameView.setFocusableInTouchMode(true); //��Ϊ�ɴ���
	}
    /**.
     * ������Ϸ����
     */
	public void goToGameView2() {
		if (gameplayView == null) {
			gameplayView = new GameplayView(this);
    	}
    	setContentView(gameplayView);
    	gameplayView.requestFocus(); //��ȡ����
    	gameplayView.setFocusableInTouchMode(true); //��Ϊ�ɴ���
	}
    /**.
     * 	�����������
     */
	public void goToHall() {
		Intent intent = new Intent();
		intent.setClass(GameActivity.this, HallActivity.class);
		Log.d(Config.LOG_TAG, "go to hall activity from game activity");
		finish();
		startActivity(intent);
	}

    /**.
     * 	���뷿�����
     */
	public void goToRoom() {
		Intent intent = new Intent();
		intent.setClass(GameActivity.this, RoomActivity.class);
		finish();
		startActivity(intent);
	}
	
	
    /**.
     * 	�������SurfaceView���͵���Ϣ
     */
	Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
        	switch(msg.what) {
        	case 0:
        		goToGameView(); //������Ϸ��������
        		break;
        	}
        }
	};
}