package com.sansam;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Before;
import org.junit.Test;

public class ExecutorServiceTest {
	
	static ExecutorService pool;
	
	@Before
	public void init(){
		pool = Executors.newFixedThreadPool(10);
	}
	
	/**
	 * execute ��������Ϊ Runnable �޷���ֵ �첽ִ��
	 * @param pool
	 */
	@Test
	public void executeRunnable(){
		// 1
		System.out.println(getClass()+" before executeRunnable ");
		pool.execute(new Runnable() {
			public void run() {
				// 3
				System.out.println(getClass()+ " : executeRunnable runnable");
			}
		});
		// 2
		System.out.println(getClass()+" after executeRunnable ");
		
		//ִ�н�� �첽
//		class com.sansam.ExecutorServiceTest before executeRunnable 
//		class com.sansam.ExecutorServiceTest after executeRunnable 
//		class com.sansam.ExecutorServiceTest$1 : executeRunnable runnable

	}
	
	
	/**
	 *  submit(new Runnable()); ������һ��Future������ܷ���ֵ��future.get()���� ����߳�ִ������򷵻�null ��ʱΪͬ��ִ��
	 *  �����ִ��future.get()���� Ϊ�첽ִ��
	 */
	@Test
	public void submitRunnable(){
		System.out.println(getClass()+" before submitRunnable ");
		
		Future<?> future = pool.submit(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(getClass() + " : submitRunnable");
			}
		});
		
		// ����������ִ���򷵻� null 
		// ������ future.get() ���� Ϊͬ��ִ�� �������
//			class com.sansam.ExecutorServiceTest before submitRunnable 
//			class com.sansam.ExecutorServiceTest$2 : submitRunnable
//			future get :null
//			class com.sansam.ExecutorServiceTest after submitRunnable 
		try {
			System.out.println("future get :"+future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ��ִ�� future.get()���� �첽ִ�� �������
//		class com.sansam.ExecutorServiceTest before submitRunnable 
//		class com.sansam.ExecutorServiceTest after submitRunnable 
//		class com.sansam.ExecutorServiceTest$2 : submitRunnable
		System.out.println(getClass()+" after submitRunnable ");
	}
	
	@Test
	public void submitCallable(){
		System.out.println(getClass()+" before submitCallable ");
		Future<String> future = pool.submit(new Callable<String>() {

			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println(getClass()+" : submitCallable");
				return  "submitCallable return...";
			}
		});
		
		// ����future.get()���� ���Ի�� callable���ص�ֵ ͬ��ִ��
//		class com.sansam.ExecutorServiceTest before submitCallable 
//		class com.sansam.ExecutorServiceTest$3 : submitCallable
//		future get :submitCallable return...
//		class com.sansam.ExecutorServiceTest after submitCallable 
		try {
			System.out.println("future get :"+future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ������future.get()���� �첽ִ��
//		class com.sansam.ExecutorServiceTest before submitCallable 
//		class com.sansam.ExecutorServiceTest after submitCallable 
//		class com.sansam.ExecutorServiceTest$3 : submitCallable
		System.out.println(getClass()+" after submitCallable ");
	}
}
