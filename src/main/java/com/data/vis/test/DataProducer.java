package com.data.vis.test;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.data.vis.entity.TestData;
import com.data.vis.web.MyWebSocket;
import com.google.gson.Gson;

@Component
public class DataProducer {
 
	Gson gson=new Gson();
    public static final long INTERVAL=3_000L;
	Thread thread;
	volatile boolean run;
	@PostConstruct
	public void onInit() {
		run=true;
		thread=new Thread(()->{
			while(run) {
				try {
					TimeUnit.MILLISECONDS.sleep(INTERVAL);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
				TestData data=new TestData();
				data.price=Math.random()*(5)+10;
				data.time=System.currentTimeMillis();
				String message=gson.toJson(data);
				MyWebSocket.sendInfo(message);
			}
		});
		thread.start();
	}
	
	@PreDestroy
	public void onDestroy() {
		run=false;
		thread.interrupt();
	}
}
