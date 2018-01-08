/**
 * Title: DbAsynService.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-5-30
 */
package com.wk.cd.async.da.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.wk.cd.common.util.Assert;

/**
 * @author lixl
 */
public abstract class AsynBaseService {
	private BlockingQueue<Object> bq;
	static final int CAP = 5000;
	private static final int TMOUT = 1000;
	static final Object END = new Object();

	AsynBaseService(){
		this.bq = new LinkedBlockingQueue<Object>(CAP);
	}
	
	AsynBaseService(BlockingQueue<Object> bq){
		this.bq = bq;
	}

	
	/**
	 * 数据放入缓存，如果缓存已经满，则会等待一定时间，直到抛异常
	 * @param info 数据
	 * @throws InterruptedException 缓存已经满异常
	 */
	public void putCache(Object info) throws InterruptedException{
		Assert.assertNotEmpty(info, "OBJECT");
		bq.offer(info, TMOUT,TimeUnit.MILLISECONDS);
	}

	public void putEND(){
		bq.add(END);

	}
	
	/**
	 * 获取缓存数据，如果缓存为空会阻塞等待，直到有数据写入
	 * @return T 数据
	 * @throws InterruptedException
	 */
	Object getFromCache()throws InterruptedException{
		return bq.take();
	}

	int getCacheSize(){
		return bq.size();
	}
	
	List<Object> getAllFromCache()throws InterruptedException{
		List<Object> lst = new ArrayList<Object>();
		bq.drainTo(lst);
		return lst;
	}

	abstract void dispatchService() throws InterruptedException;

}
