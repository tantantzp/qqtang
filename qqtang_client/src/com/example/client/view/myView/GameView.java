package com.example.client.view.myView;

import java.util.ArrayList;
import java.util.Vector;

import com.example.client.R;
import com.example.client.R.drawable;
import com.example.client.view.myGameStruct.Ball;
import com.example.client.view.myGameStruct.Man;
import com.example.client.view.myGameStruct.MapPic;
import com.example.client.view.myThread.KeyThread;
import com.example.client.view.myWidgets.MyButton;
import com.example.client.view.others.Constant;
import com.example.client.view.others.PicLoadUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * ��������Ϸ������,֮����ʹ��
 */
public class GameView extends SurfaceView  implements SurfaceHolder.Callback{
	
	GameActivity activity;
	Paint paint;//����
	
	int mapdata[][] = {
			{0,0,1,1,0,0,1,1,0,0},
			{0,0,1,1,0,0,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,0,0,0,0},
			{0,0,0,0,1,1,0,0,0,0}
	};
	
	Man man;
	MapPic mappic;//����ͼƬ�������
	MyButton dir0, dir1, dir2, dir3 ,btn_ball;
	
	int pointx;//���ص���Ļ��λ��
	int pointy;//���ص���Ļ��λ��
	
	
	KeyThread keythread;//ˢ֡�߳�
	//TimeRunningThread timeruningthread;//���𵹼�ʱ���߳�
	
	Bitmap man_pic[];//��һ�������ͼƬ
	Bitmap dir_pic[];
	Bitmap back_pic[];//����ͼƬ
    Bitmap ball_pic[];
    Bitmap btn_ball_pic;
	Vector<Ball> vecBall = new Vector<Ball>();
	
	
	
	public GameView(GameActivity activity) {
		super(activity);
		this.activity=activity;
		getHolder().addCallback(this);//ע��ص��ӿ�
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT));//ֻ����Ļ��Χ�ڻ���ͼƬ
		
		canvas.drawColor(Color.WHITE);//��������Ϊ��ɫ		
		mappic.drawSelf(canvas, paint);//���Ʊ���
		for(int i = 0; i < vecBall.size(); i++){
			vecBall.elementAt(i).drawSelf(canvas, paint);
			vecBall.elementAt(i).time();
		}
		man.drawSelf(canvas, paint);//��ߵ�0,0Ϊƫ����
        
		dir0.drawSelf(canvas, paint);
		dir1.drawSelf(canvas, paint);
		dir2.drawSelf(canvas, paint);
		dir3.drawSelf(canvas, paint);
		btn_ball.drawSelf(canvas, paint);
	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		pointx=(int) event.getX();
		pointy=(int) event.getY();
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://����
			if(dir0.isPointInRect(pointx, pointy)){
			    man.manGo(0);
			}
			else if(dir1.isPointInRect(pointx, pointy)){
			    man.manGo(1);
			}
			else if(dir2.isPointInRect(pointx, pointy)){
			    man.manGo(2);
			}
			else if(dir3.isPointInRect(pointx, pointy)){
			    man.manGo(3);
			}
			else if(btn_ball.isPointInRect(pointx, pointy)){
				createBall(pointx, pointy);
			}
			break;
		case MotionEvent.ACTION_MOVE://�ƶ�
			
			break;
		case MotionEvent.ACTION_UP://̧��
		
			break;
		}
		return true;
	}
	public void createBall(float pointx, float pointy){
		Ball nball = new Ball(mappic, ball_pic, (int)man.row, (int)man.col);
	    vecBall.add(nball);
	    
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		paint=new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����
		
		Constant.changeRadio();//���ó������г�����ֵ�ķ���
		initBitmap();//��ʼ��λͼ��Դ
		//��������

		
		keythread=new KeyThread(this);//ˢ֡�߳�		
		mappic=new MapPic(mapdata, back_pic);//��������ͼƬ��Ķ���
		man = new Man(man_pic, mappic);
		mappic=new MapPic(mapdata, back_pic);//��������ͼƬ��Ķ���
		dir0 = new MyButton(dir_pic[0], dir_pic[0], Constant.BLOCK_HEIGHT * 1, Constant.BLOCK_WIDTH * 4.5f,"");
		dir1 = new MyButton(dir_pic[1], dir_pic[1], Constant.BLOCK_HEIGHT * 1.8f, Constant.BLOCK_WIDTH * 3.7f,"");
		dir2 = new MyButton(dir_pic[2], dir_pic[2], Constant.BLOCK_HEIGHT * 1, Constant.BLOCK_WIDTH * 3f,"");
		dir3 = new MyButton(dir_pic[3], dir_pic[3], Constant.BLOCK_HEIGHT * 0.2f, Constant.BLOCK_WIDTH * 3.7f,"");
	    btn_ball = new MyButton(btn_ball_pic, btn_ball_pic, Constant.BLOCK_HEIGHT * 6, Constant.BLOCK_WIDTH * 4, "");
		//����ȫ���߳�	
		startAllThreads();
	}	
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		stopAllThreads();//ֹͣ�����߳�
		
		//�ر������߳�
	    while (retry) {
	        try {
	        	
	        	keythread.join();
	      
	            retry = false;
	        } 
	        catch (InterruptedException e) {e.printStackTrace();}//���ϵ�ѭ����ֱ�������߳̽���
		}
	    
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	//��ʼ��ͼƬ��Դ
	public void initBitmap(){
		//�����ͼƬ
		man_pic=new Bitmap[]{
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t11),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t12),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t13),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t21),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t22),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t23),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t31),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t32),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t33),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t41),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t42),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.t43),Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT)
			};


		//����ͼƬ
		back_pic=new Bitmap[]{
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.grass1), 
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.grass2), 
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT)
		
		};
		dir_pic=new Bitmap[]{
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.dir00), 
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.dir10), 
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.dir20), 
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
			PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.dir30), 
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT)
		};
		ball_pic = new Bitmap[]{
				PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.ball), 
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
				PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.ball), 
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
				PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.ball), 
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT),
				PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.ball), 
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT)
		};
		btn_ball_pic = PicLoadUtil.scaleToFit(BitmapFactory.decodeResource(this.getResources(), R.drawable.btn_ball), 
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT);

	}
	//������Ϸ�ķ���
	public void overGame()
	{
		stopAllThreads();//ֹͣ�����߳�
			
		activity.goToHall();//������Ϣ��������Ϸ��������
	}
	//���������߳�
	public void startAllThreads()
	{
		keythread.setKeyFlag(true);//ˢ֡�̵߳ı�־λ��Ϊtrue
		
		keythread.start();//����ˢ֡�߳�

	}
	//ֹͣ�����߳�
	public void stopAllThreads()
	{
		keythread.setKeyFlag(false);//�ر�ˢ֡�߳�
		
	}
	
	//���»��ƻ���
	@SuppressLint("WrongCall")
	public void repaint()
	{
		Canvas canvas=this.getHolder().lockCanvas();
		try
		{
			synchronized(canvas)
			{
				onDraw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			if(canvas!=null)
			{
				this.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK )
		{
			// �����˳��Ի���
			AlertDialog isExit = new AlertDialog.Builder(activity).create();
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

	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
	{
		public void onClick(DialogInterface dialog, int which)
		{
			switch (which)
			{
			case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����
				activity.finish();
				break;
			case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���
				break;
			default:
				break;
			}
		}
	};	
}
