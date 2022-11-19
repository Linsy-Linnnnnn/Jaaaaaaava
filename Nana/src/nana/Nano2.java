//design by 林穗颜
package nana;

import java.util.Scanner;

public class Nano2 {
	public static void main(String[] args) {
		double sy;
		System.out.println("成绩是？");
		Scanner y = new Scanner(System.in);
	    sy = y.nextDouble();
		while(sy>100||sy<0){
			System.out.println("咋不输一万呢？0~100啊！");
			sy = y.nextDouble();
		}
		if (sy<=100&&sy>=90){
			System.out.println("优秀");
		}
		if (sy<=90&&sy>=80){
			System.out.println("良好");
			
		}
		if (sy<=80&&sy>=70){
			System.out.println("中等");
			
		}
		if (sy<=70&&sy>=60){
			System.out.println("及格");
		}
		if (sy<60){
			System.out.println("你不及格！不及格！");
			
		}
		
	
	
	
		


	}
}
