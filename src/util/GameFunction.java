package util;

public class GameFunction {
	
	public static long getSleepTimeByLevel(int level)
	{
		System.out.println("getSleepTimeByLevel����ͨ���ȼ����������ٶ�");
		long sleep=(-40*level+740);
		sleep=sleep<100?100:sleep;
		System.out.println("getSleepTimeByLevel��������");
		return sleep;
	}
}
