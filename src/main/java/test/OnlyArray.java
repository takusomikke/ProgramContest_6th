package test;

import java.util.Scanner;

public class OnlyArray {

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
      int SUM_OF_THREE= sc.nextInt();

      //入力される本数が入ります。
      int NUM_TO_BE_ENTERD = sc.nextInt();

      //それぞれの長さを格納する配列を作ります。
      int[] lengtharray= new int[NUM_TO_BE_ENTERD+1];

      long start = System.currentTimeMillis();

      //1本ずつ入力するか、自動生成か選択
//      createByHand(sc,SUM_OF_THREE,NUM_TO_BE_ENTERD,lengtharray);
      createByAuto(SUM_OF_THREE,NUM_TO_BE_ENTERD,lengtharray);

//      ソート後の配列がどうなってるのか確認
//      for(int i = 0 ; i < lengthlist.size() ; i++){
//          System.out.println(lengthlist.get(i));
//      }

      System.out.println("計算を開始します。");

      int count =countCombi(SUM_OF_THREE,lengtharray);

      long end = System.currentTimeMillis();
      System.out.println(count);

      System.out.println((end - start)  + "ms");

    }//mainメソッドの終わり


    private static void createByHand(Scanner sc,int SUM_OF_THREE,int NUM_TO_BE_ENTERD,int[] lengtharray){
        int length;
        int key;
        int min_key_value = (int)Math.ceil( SUM_OF_THREE / NUM_TO_BE_ENTERD )+1;
        int max_key_value;

        for(int i = 0 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();

            key = SUM_OF_THREE -length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if(min_key_value < key && key <= max_key_value){
                lengtharray[key]=1;
            }
        }
    }

    private static void createByAuto(int SUM_OF_THREE,int NUM_TO_BE_ENTERD,int[] lengtharray){

        int key;
        int min_key_value = (int)Math.ceil( SUM_OF_THREE / NUM_TO_BE_ENTERD )+1;
        int max_key_value;

        for(int length = 1 ; length <=NUM_TO_BE_ENTERD;length++){

            key = SUM_OF_THREE -length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if(min_key_value < key && key <= max_key_value){
                lengtharray[key]=1;
            }
        }
    }

    private static int countCombi(int SUM_OF_THREE,int[] lengtharray){
        int count=0;
        for(int i = 1 ; i < SUM_OF_THREE / NUMB_TO_BE_COMBINED;i++){
                for(int j = i+1; j < (SUM_OF_THREE-i) /2.0 ; j++){
                    count += lengtharray[i+j];
                }
        }
        return count;
    }
}
