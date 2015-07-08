/*
 * 入力時に工夫をする。
 *
 * 3本目として必要される回数を値として入れる。
 */



package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MonoLoop {

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


      //それぞれの長さを格納するリストを作ります。
      List<Integer> lengthlist= new ArrayList<Integer>();

      //3つ目の数字を見つけるためのマップです。
      Map<Integer,Integer> lengthmap = new HashMap<Integer,Integer>();



      //1本ずつ入力するか、自動生成か選択
//      createByHand(sc,sumlength,number,lengthlist,lengthmap);
      createByAuto(sumlength,number,lengthlist,lengthmap);



      Collections.sort(lengthlist);

//      ソート後の配列がどうなってるのか確認
//      for(int i = 0 ; i < lengthlist.size() ; i++){
//          System.out.println(lengthlist.get(i));
//      }

      System.out.println("計算を開始します。");
      long start = System.currentTimeMillis();

      int count =countCombi(sumlength,lengthlist,lengthmap);

      long end = System.currentTimeMillis();
      System.out.println(count);

      System.out.println((end - start)  + "ms");

    }//mainメソッドの終わり


    private static void createByHand(Scanner sc,int sumlength,int number,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int length;
        int count;
        int key;

        //1コメの数字はそのまま登録
        length = sc.nextInt();
        lengthlist.add(length);

        for(int i = 1 ; i < number;i++){
            length = sc.nextInt();

            for(int j = 0; j < lengthlist.size(); j++){
                key = sumlength-(lengthlist.get(j)+length);
                if(key <= sumlength / NUMB_TO_BE_COMBINED +1 || key > sumlength - MIN_NUM_OF_SUM){
                    continue;
                }
                if(lengthmap.get(key) == null){
                    lengthmap.put(key, 1);
                }else{
                    count = lengthmap.get(key);
                    lengthmap.put(key, ++count);
                }
            }

            lengthlist.add(length);
        }
    }

    private static void createByAuto(int sumlength,int number,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int length;  //入力された数字で、1本目を表す数字
        int count;
        int key;

        //1コメの数字はそのまま登録
        length = 1;
        lengthlist.add(length);

        for(int i = 1 ; i < number;i++){

            length = i+1;

                for(int j = 0; j < lengthlist.size(); j++){

                        key = sumlength-(lengthlist.get(j)+length);

                        if(lengthlist.get(j) < key && length < key){
                            //keyがほかの2本よりも大きいときに実行
                            if(key <= sumlength / NUMB_TO_BE_COMBINED +1 || sumlength - MIN_NUM_OF_SUM < key){
                            //3本目の数字(key)が許容範囲がの時はマップ登録はしない
                            }else if(lengthmap.get(key) == null){
                                lengthmap.put(key, 1);
                            }else{
                                count = lengthmap.get(key);
                                lengthmap.put(key, ++count);
                            }
                        }
            }
            //次の入力前にリストに登録
            lengthlist.add(length);
        }
    }


    private static int countCombi(int sumlength,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int count=0;

        for(int i = 0 ; i < lengthlist.size();i++){
            if(lengthlist.get(i) < sumlength / NUMB_TO_BE_COMBINED +1 || sumlength - MIN_NUM_OF_SUM < lengthlist.get(i)){
                continue;
            }
            if(lengthmap.get(lengthlist.get(i)) == null){
                continue;
            } else{
//                System.out.println(lengthlist.get(i) +":"+ lengthmap.get(lengthlist.get(i)));
                count += lengthmap.get(lengthlist.get(i));
            }
        }
        return count;
    }

}//クラスの終了
