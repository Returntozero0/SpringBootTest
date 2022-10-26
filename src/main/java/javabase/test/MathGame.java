package javabase.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MathGame {
    private static Random random = new Random();

    //用于统计生成的不合法变量的个数
    public int illegalArgumentCount = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello master");
        MathGame game = new MathGame();
        //死循环，每过1秒调用1次下面的方法(不是开启一个线程)
        while (true) {
            game.run();
            TimeUnit.SECONDS.sleep(1);
        }
    }
        //分解质因数
        public void run () throws InterruptedException {
            try {
                //随机生成一个整数，有可能正，有可能负
                int number = random.nextInt() / 10000;
                //调用方法进行质因数分解
                List<Integer> primeFactors = primeFactors(number);
                //打印结果
                print(number, primeFactors);
            } catch (Exception e) {
                System.out.println(String.format("illegalArgumentCount:%3d, ", illegalArgumentCount) + e.getMessage());
            }
        }

        //打印质因数分解的结果
        public static void print ( int number, List<Integer > primeFactors){
            StringBuffer sb = new StringBuffer(number + "=");
            for (int factor : primeFactors) {
                sb.append(factor).append('*');
            }
            if (sb.charAt(sb.length() - 1) == '*') {
                sb.deleteCharAt(sb.length() - 1);
            }
            System.out.println(sb);
        }

        //计算number的质因数分解
        public List<Integer> primeFactors ( int number){
            //如果小于2，则抛出异常，并且计数加1
            if (number < 2) {
                illegalArgumentCount++;
                throw new IllegalArgumentException("number is: " + number + ", need >= 2");
            }
            //用于保存每个质数
            List<Integer> result = new ArrayList<Integer>();
            //分解过程，从2开始看能不能整除
            int i = 2;
            while (i <= number) {  //如果i大于number就退出循环
                //能整除，则i为一个因数，number为整除的结果再继续从2开始除
                if (number % i == 0) {
                    result.add(i);
                    number = number / i;
                    i = 2;
                } else {
                    i++;  //否则i++
                }
            }

            return result;
        }
    }
