package test;

import java.util.Scanner;

public class FNOCWithoutMapTest {

    /*
     * 組み合わせる本数
     */
    private static final int NUMB_TO_BE_COMBINED = 3;

    /*
     * 棒の長さは、1以上の自然数で重複なしなので
     * 2本の最小値は 1 + 2 = 3である。
     */
    private static final int MIN_NUM_OF_SUM = 3;

    public static void main(String[] args) {

      @SuppressWarnings("resource")
      Scanner sc = new Scanner(System.in);

      //3本の合計値が入ります。
      int sumlength= sc.nextInt();

      //入力される本数が入ります。
      int number = sc.nextInt();

      //それぞれの長さを格納する配列を作ります。
      int[] lengtharray= new int[number+1];

      long start = System.currentTimeMillis();

      //1本ずつ入力するか、自動生成か選択
//      createByHand(sc,sumlength,number,lengtharray);
      createByAuto(sumlength,number,lengtharray);

//      ソート後の配列がどうなってるのか確認
//      for(int i = 0 ; i < lengthlist.size() ; i++){
//          System.out.println(lengthlist.get(i));
//      }

      System.out.println("計算を開始します。");

      int count =countCombi(sumlength,lengtharray);

      long end = System.currentTimeMillis();
      System.out.println(count);

      System.out.println((end - start)  + "ms");

    }//mainメソッドの終わり


    private static void createByHand(Scanner sc,int sumlength,int number,int[] lengtharray){
        int length;
        int key;
        int max_key_value;

        for(int i = 0 ; i < number;i++){
            length = sc.nextInt();

            key = sumlength -length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if(key <= max_key_value){
                lengtharray[key]=1;
            }
        }
    }

    private static void createByAuto(int sumlength,int number,int[] lengtharray){

        int key;
        int max_key_value;

        for(int length = 1 ; length <=number;length++){

            key = sumlength -length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if(key <= max_key_value){
                lengtharray[key]=1;
            }
        }
    }

    private static int countCombi(int sumlength,int[] lengtharray){
        int count=0;
        for(int i = 1 ; i < sumlength / NUMB_TO_BE_COMBINED;i++){
                for(int j = i+1; j < (sumlength-i) /2.0 ; j++){
                    count += lengtharray[i+j];
                }
        }
        return count;
    }
}
