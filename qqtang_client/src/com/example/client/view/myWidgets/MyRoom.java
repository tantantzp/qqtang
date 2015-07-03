package com.example.client.view.myWidgets;

import com.example.client.view.myView.GameActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * ������,���淿����Ϣ,���Ʒ���
 */
public class MyRoom {
	/**
	 * �������Ͻ�λ��
	 */
	float x;
	float y;
	/**
	 * ������
	 */
	int width;
	int height;
	/**
	 * ����λͼ
	 */
	Bitmap onBitmap; 
	Bitmap offBitmap;
	boolean isOn = false; //����Ϊfalse
	/**
	 * ������Ϣ
	 */
	String info;
	String roomName;
	String players;
	String state;
	/**
	 * ���캯��
	 */
	public MyRoom(Bitmap onBitmap,Bitmap offBitmap, float x,float y)
	{	
		this.info = "room";
		this.onBitmap=onBitmap;
		this.offBitmap=offBitmap;
		this.x=x;
		this.y=y;
		this.width=offBitmap.getWidth();
		this.height=offBitmap.getHeight();		
	}
	/**
	 * ���÷�����Ϣ
	 */
	public void setRoomInfo(String _roomName, String _players, String _state)
	{
		roomName = _roomName;
		players = _players;
		state = _state;
	}
	/**
	 * ���Ʒ���
	 */
	public void drawSelf(Canvas canvas,Paint paint)//�������ⰴť
	{
		if(!isOn)
		{
			canvas.drawBitmap(onBitmap, x, y, paint);
			canvas.drawText(roomName, x + width/4, y + 2*height/6, paint);
			
			canvas.drawText(players, x + width/4, y + 3*height/6, paint);
			
			canvas.drawText(state, x + width/4, y + 5*height/6, paint);
		}
		else
		{
			//info = roomName + "\n" + players + "\n" + state;
			info = "enter";
			canvas.drawBitmap(offBitmap, x, y, paint);
			canvas.drawText(info, x + width/5, y + 2*height/5, paint);
		}
	}
	/**
	 * �Ƿ��ڼ�����£�״̬
	 */
	public boolean isOnflag()//����isOn��״̬
	{
		return isOn;
	}
	/**
	 * ���ü���״̬
	 */
	public void setOnflag(boolean flag)
	{
	    isOn = flag;
	}
	/**
	 * һ�����Ƿ��ھ����ڣ������߽磩
	 */
	public boolean isPointInRect(float pointx,float pointy)
	{
		if(//����ھ����ڣ�����true
				pointx>=x&&pointx<=x+width&&
				pointy>=y&&pointy<=y+height
		  )
		  {
			  isOn = true;
			  return true;//����ڣ�����true
		  }
		isOn = false;
		return false;//���򷵻�false
	}	
}
