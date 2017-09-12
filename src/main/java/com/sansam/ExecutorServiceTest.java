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
	 * execute 方法参数为 Runnable 无返回值 异步执行
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
		
		//执行结果 异步
//		class com.sansam.ExecutorServiceTest before executeRunnable 
//		class com.sansam.ExecutorServiceTest after executeRunnable 
//		class com.sansam.ExecutorServiceTest$1 : executeRunnable runnable

	}
	
	
	/**
	 *  submit(new Runnable()); 可以用一个Future对象接受返回值，future.get()方法 如果线程执行完毕则返回null 此时为同步执行
	 *  如果不执行future.get()方法 为异步执行
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
		
		// 如果任务结束执行则返回 null 
		// 调用了 future.get() 方法 为同步执行 结果入下
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
		
		// 不执行 future.get()方法 异步执行 结果如下
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
		
		// 调用future.get()方法 可以获得 callable返回的值 同步执行
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
		
		// 不调用future.get()方法 异步执行
//		class com.sansam.ExecutorServiceTest before submitCallable 
//		class com.sansam.ExecutorServiceTest after submitCallable 
//		class com.sansam.ExecutorServiceTest$3 : submitCallable
		System.out.println(getClass()+" after submitCallable ");
	}
}
