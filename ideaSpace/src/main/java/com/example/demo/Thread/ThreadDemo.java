package com.example.demo.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/8
 */
public class ThreadDemo {
	public static void main(String[] args) throws Exception {
		ThreadDemo inst = new ThreadDemo();
		List<FutureTask<Integer>> taskList = new ArrayList<>();
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			FutureTask<Integer> ft = new FutureTask<>(new ComputaTask(i, "" + i));
			taskList.add(ft);
			exec.submit(ft);
		}

		System.out.println("所有计算 任务提交完毕，主线程接着干其他事情");
		Integer totalResult = 0;
		for (FutureTask<Integer> ft:taskList) {
			totalResult = totalResult + ft.get();
		}
		exec.shutdown();
		System.out.println("多任务计算后的总结过是：" + totalResult);
	}




}

class ComputaTask implements Callable<Integer> {

	private Integer result = 0;
	private String taskName = "";
	public ComputaTask(Integer result, String taskName) {
		this.result = result;
		this.taskName = taskName;
		System.out.println("生成子线程计算任务:" + taskName);
	}

	public String getTaskName() {
		return this.taskName;
	}

	@Override
	public Integer call() throws Exception {
		for (int i = 0; i < 100; i++) {
			result =+ 1;
		}
		Thread.sleep(5000);



		System.out.println("子线程计算任务:" + taskName +"执行完成!");
		return result;
	}
}
