package com.example.client.view.myThread;

import com.example.client.view.myGameStruct.Ball;
/**
 * ��������Ϸ������,֮����ʹ��
 */
public class BallExplodeThread extends Thread {
    Ball ball;

	private int sleepSpan=60;//�߳���Ϣ��ʱ��
	
	public BallExplodeThread(Ball ball)//���췽��
	{
		this.ball = ball;
	
	}
	@Override
	public void run()
	{	
		
		try {
			for(int i = 0; i < 3; i ++){
				ball.i = i + 1;
				sleep(sleepSpan);//��Ϣ60����	
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		ball.exist = false;
		ball.map.mapdata[ball.row][ball.col] = 0;
		
	}
	
	
}
