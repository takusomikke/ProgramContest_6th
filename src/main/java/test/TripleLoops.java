package test;

import java.util.Scanner;

public class TripleLoops {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        //3本の合計値が入ります。
        int sumlength= sc.nextInt();

        //入力される本数が入ります。
        int number = sc.nextInt();

        //numberの長さを持つ配列を作ります。
        int[] lengtharray = new int[number];

        //数字の入力を受け付け()
        setLengthArray(lengtharray);

        System.out.println("計算を開始します。");

        long start = System.currentTimeMillis();

        //組み合わせをカウントします。
        int count = countCombi(sumlength,lengtharray);

        long end = System.currentTimeMillis();

        System.out.println(count);

        System.out.println((end - start)  + "ms");

    }//メインメソッドの終了

    /*
     * 本数分の長さの配列を作成
     */
    private static void setLengthArray(int[]lengtharray){
        for(int i = 1 ; i<=lengtharray.length;i++){
              lengtharray[i-1]=i;
          }
    }

    /*
     * 重複して数えることを防いでいる。
     * 配列はソートされている必要はない。
     */
    private static int countCombi(int sumlength,int[]  lengtharray){
        int count=0;
        for(int i = 0 ; i < lengtharray.length-2;i++){
            for(int j = i+1; j< lengtharray.length-1 ; j++){
                for(int k = j+1 ; k <lengtharray.length; k++){
                    if(lengtharray[i]+lengtharray[j]+lengtharray[k]==sumlength){
                        System.out.println(lengtharray[i]+":"+lengtharray[j]+":"+lengtharray[k]);
                        count++;
                    }
                }
            }
        }
        return count;

    }
}//クラスの終了
