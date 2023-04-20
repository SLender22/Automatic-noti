import java.util.Scanner;

public class LittleGames {
//    这是一个石头剪刀布的简单游戏
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("石头请输入1");
        System.out.println("布请输入2");
        System.out.println("剪刀请输入3");
        int i = scanner.nextInt();
/*
      这里的random的取值是1-4，为什么这里的random取值不设置1-3
      因为要保证石头 剪刀 布三个出来的概率是相同的
      所以random取到4是一个异常值 要提出剔除
 */
        int random = (int)((Math.random())*3+1);
        if(random==4){
            System.out.println("错误，请重新来一次");
        }
        else {
            switch (random){
                case 1:
                    System.out.println("系统出的是石头");
                    break;
                case 2:
                    System.out.println("系统出的是布");
                    break;
                case 3:
                    System.out.println("系统出的是剪刀");
                    break;
            }
            switch (i-random){
                case 0:
                    System.out.println("平局");
                    break;
                case 1:
                    System.out.println("你赢了");
                    break;
                case -1:
                    System.out.println("你输了");
                    break;
                case 2:
                    System.out.println("你输了");
                    break;
                case -2:
                    System.out.println("你赢了");
                    break;

            }
        }

    }
}
