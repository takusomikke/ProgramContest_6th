package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlyArray {

    /*
     * 組み合わせる本数
     */
    private static final int NUM_TO_BE_COMBINED = 3;

    /*
     * 棒の長さは、1以上の自然数で重複なしなので
     * 2本の最小値は 1 + 2 = 3である。
     */
    private static final int MIN_SUM_OF_TWO = 3;

    /*
     * 3本の合計値が入ります。
     */
    private static int SUM_OF_THREE;

    /*
     * 入力される本数が入ります。
     */
    private static int NUM_TO_BE_ENTERD;

    private static int MIN_KEY =0;
    private static int MAX_KEY =0;


    public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);

      SUM_OF_THREE= sc.nextInt();
      NUM_TO_BE_ENTERD = sc.nextInt();

      //keyを格納する配列を作ります。
      int[] lengtharray= new int[SUM_OF_THREE+1];
      //入力される数字を格納します。
      List<Integer> lengthlist = new ArrayList<Integer>();

      long start = System.currentTimeMillis();

      //1本ずつ入力するか、自動生成か選択
//      createByHand(sc,lengtharray);
      createByAuto(lengthlist,lengtharray);

//      ソート後の配列がどうなってるのか確認
//      for(int i = 0 ; i < lengthlist.size() ; i++){
//          System.out.println(lengthlist.get(i));
//      }

      System.out.println("計算を開始します。");

      int count =countCombi(lengthlist,lengtharray);

      long end = System.currentTimeMillis();
      System.out.println(count);

      System.out.println((end - start)  + "ms");

    }//mainメソッドの終わり


    private static void createByHand(Scanner sc,int[] lengtharray){
        int length;
        int key;
        int max_key_value = (SUM_OF_THREE/NUM_TO_BE_COMBINED) * 2 - 1 ;

        /*
         * 1本目だけちょっと特別
         */
        length = sc.nextInt();

        key = SUM_OF_THREE -length;

        if(MIN_SUM_OF_TWO <= key && key <= max_key_value){
            MIN_KEY = key;
            MAX_KEY = key;
            lengtharray[key]=1;
        }


        for(int i = 1 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();

            key = SUM_OF_THREE -length;

            if(MIN_SUM_OF_TWO <= key && key <= max_key_value){
                lengtharray[key]=lengtharray[key]+1;
                if(key < MIN_KEY){
                    MIN_KEY = key;
                }else if(MAX_KEY < key){
                    MAX_KEY = key;
                }
            }
        }
    }

    private static void createByAuto(List<Integer> lengthlist,int[] lengtharray){

        int[] tmp = new int[SUM_OF_THREE];
        int key;
        int max_key_value = (SUM_OF_THREE/NUM_TO_BE_COMBINED)*2-1;


        for(int length = 1 ; length <=NUM_TO_BE_ENTERD;length++){

            key = SUM_OF_THREE -length;

            if(1 <= length && length <= SUM_OF_THREE - MIN_SUM_OF_TWO){
                tmp[length]=length;
            }

            if(MIN_SUM_OF_TWO <= key && key <= max_key_value){
//                System.out.println("length:"+length+"key:"+key);
                lengtharray[key]=1;
            }
        }
        for(int i =1; i < SUM_OF_THREE; i++){
            if(tmp[i] != 0){
                lengthlist.add(tmp[i]);
            }
        }
    }

    private static int countCombi(List<Integer> lengthlist,int[] lengtharray){
        int count=0;
        for(int i = 0 ; lengthlist.get(i) < SUM_OF_THREE / NUM_TO_BE_COMBINED;i++){
            for(int j = i+1; lengthlist.get(j) < (SUM_OF_THREE-lengthlist.get(i)) /2.0 ; j++){
                count += lengtharray[lengthlist.get(i)+lengthlist.get(j)];
            }
        }
        return count;
    }
}
