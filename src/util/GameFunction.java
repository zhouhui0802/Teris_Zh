package util;

public class GameFunction {
	
	public static long getSleepTimeByLevel(int level)
	{
		System.out.println("getSleepTimeByLevel函数通过等级控制下落速度");
		long sleep=(-40*level+740);
		sleep=sleep<100?100:sleep;
		System.out.println("getSleepTimeByLevel函数结束");
		return sleep;
	}
}
