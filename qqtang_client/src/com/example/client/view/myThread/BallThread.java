package com.example.client.view.myThread;

import com.example.client.view.myGameStruct.Ball;
/**
 * ��������Ϸ������,֮����ʹ��
 */
public class BallThread extends Thread {
    Ball ball;

	private int sleepSpan = 60;//�߳���Ϣ��ʱ��
	
	public BallThread(Ball ball)//���췽��
	{
		this.ball = ball;
	
	}
	@Override
	public void run()
	{	
		
		try {
			for(int i = 0; i < 30; i++){
				ball.i = ball.i;
				sleep(sleepSpan);//��Ϣ2000����		
			}
	
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		ball.explode();
		
	}
	
	
}
