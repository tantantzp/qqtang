package com.example.client.view.myThread;

import com.example.client.view.myGameStruct.Man;

/**
 * ��������Ϸ������,֮����ʹ��
 */
public class ManFootThread extends Thread {
    Man man;
    int oi;
    int dir;
    int time;
    int trow, tcol;
	private int sleepSpan=60;//�߳���Ϣ��ʱ��
	
	public ManFootThread(Man man, int row, int col)//���췽��
	{
		this.man = man;
		oi = man.i;
		dir = man.dir;
		time = 5;
		trow = row;
		tcol = col;
	}
	@Override
	public void run()
	{	
		synchronized (man){
			try {
		    for(int i = 0; i < time; i++){
		    	man.i = oi + i % 2 + 1;
				if(dir == 0){				
					man.row += (1 / (float)time);
				}
				else if(dir == 1 ){
					man.col += (1 / (float)time);
				}
				else if(dir == 2){			
					man.row -= (1 / (float)time);

				}
				else if(dir == 3){
				    man.col -= (1 / (float)time);
				}
		    	
				sleep(sleepSpan);//��Ϣ60����	
		    }
		    man.i = oi;
		    man.row = trow;
		    man.col = tcol;
		    
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	        
		}
	}
	
	
}
