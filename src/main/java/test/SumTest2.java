package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SumTest2 {
    public static void main(String[] args) {

//        final int NUMBER_OF_CHOICES=3;

        /*
         * num1,num2,num3(昇順とする)の組み合わせを考える。
         * (①,②,③)に当てはめるとすると、
         * 今回は ① < ② の制約がある。この時のすべての組み合わせは、
         * (num1,num2,num3),(num1,num3,num2),(num2,num3,num1)
         * この3とおりである。
         */
        final int NUMBER_OF_COMBINATION = 3;


        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        //3本の合計値が入ります。
        int sumlength= sc.nextInt();

        //入力される本数が入ります。
        int number = sc.nextInt();

        //numberの長さを持つ配列を作ります。
        int[] lengtharray = new int[number];

        //3つ目の数字を見つけるためのマップです。
        Map<Integer,Integer> lengthmap = new HashMap<Integer,Integer>();

        /* 1本ずつの長さの入力を受け付けます。
         *
         * キーの値は3つ目の数字として呼び出されるときの値を入れています。
         * 例)入力された数字が2の場合
         * 1本目、2本目の値の合計が8の時に3本目として2が必要とされる。
         * よって、キーを8、値を2としている。
         */
        for(int i = 0; i < number; i++){
            int length = sc.nextInt();
            lengtharray[i]=length;
            lengthmap.put(sumlength-length, length);
        }

        int count=0;

        for(int i = 0 ; i < number;i++){
            for(int j = i+1; j< number ; j++){

                //2+6+2=10のような組み合わせではなく、かつ3文字目にフィットする文字が見つかった時にカウントする。
                if((lengtharray[i]*2+lengtharray[j] !=sumlength)&&(lengtharray[i]+lengtharray[j]*2 !=sumlength)&&(lengthmap.get(lengtharray[i]+lengtharray[j]) != null)){
                    count++;
                }
            }
        }

        System.out.println("組み合わせの数"+count/NUMBER_OF_COMBINATION);

    }
}
