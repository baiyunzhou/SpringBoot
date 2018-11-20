package com.zby;

import org.springframework.util.StopWatch;

public class StopWatchMain {

	public static void main(String[] args) {
		// 计时器
		StopWatch stopWatch = new StopWatch("id-9527");
		stopWatch.start("taskA");
		try {
			Thread.sleep(5555);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stopWatch.stop();
		stopWatch.start("taskB");
		try {
			Thread.sleep(3333);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stopWatch.stop();
		System.out.println("stopWatch:\r\n" + stopWatch);
		System.out.println("stopWatch prettyPrint:\r\n" + stopWatch.prettyPrint());
	}
}
// stopWatch:
// StopWatch 'id-9527': running time (millis) = 8888; [taskA] took 5555 = 63%; [taskB] took 3333 = 38%
// stopWatch prettyPrint:
// StopWatch 'id-9527': running time (millis) = 8888
// -----------------------------------------
// ms % Task name
// -----------------------------------------
// 05555 062% taskA
// 03333 038% taskB
