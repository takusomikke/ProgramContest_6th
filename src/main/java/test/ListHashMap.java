/*
 * 3本の合計値(SUM_OF_THREE)と本数(NUM_TO_BE_ENTERD)を入力すると自動的に
 * 1,2,3,････ NUM_TO_BE_ENTERD という配列を生成することで
 * 仮想的に本数分の入力がされる。
 * 時間測定の機能もあるので、アルゴリズムのスピードも図れる
 */

package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ListHashMap {

    /*
     * 組み合わせる本数
     */
    private static final int NUM_TO_BE_COMBINED = 3;

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

      //それぞれの長さを格納するリストを作ります。
      List<Integer> lengthlist= new ArrayList<Integer>();

      //3つ目の数字を見つけるためのマップです。
      Map<Integer,Integer> lengthmap = new HashMap<Integer,Integer>();


      long start = System.currentTimeMillis();

      //1本ずつ入力するか、自動生成か選択
//      createByHand(sc,SUM_OF_THREE,NUM_TO_BE_ENTERD,lengthlist,lengthmap);
      createByAuto(SUM_OF_THREE,NUM_TO_BE_ENTERD,lengthlist,lengthmap);


      Collections.sort(lengthlist);

//      ソート後の配列がどうなってるのか確認
//      for(int i = 0 ; i < lengthlist.size() ; i++){
//          System.out.println(lengthlist.get(i));
//      }

      System.out.println("計算を開始します。");

      int count =countCombi(SUM_OF_THREE,lengthlist,lengthmap);

      long end = System.currentTimeMillis();
      System.out.println(count);

      System.out.println((end - start)  + "ms");

    }//mainメソッドの終わり


    private static void createByHand(Scanner sc,int SUM_OF_THREE,int NUM_TO_BE_ENTERD,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int length;
        int key;
        int min_key_value = (int)Math.ceil( SUM_OF_THREE / NUM_TO_BE_ENTERD )+1;
        int max_key_value;

        for(int i = 0 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();
            lengthlist.add(length);

            key = SUM_OF_THREE -length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if(min_key_value <= key && key <= max_key_value){
                lengthmap.put(key, length);
            }
        }
    }

    private static void createByAuto(int SUM_OF_THREE,int NUM_TO_BE_ENTERD,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){

        int key;
        int min_key_value = (int)Math.ceil( SUM_OF_THREE / NUM_TO_BE_ENTERD )+1;
        int max_key_value;

        for(int length = 1 ; length <=NUM_TO_BE_ENTERD;length++){
            lengthlist.add(length);

            key = SUM_OF_THREE -length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if(min_key_value <= key && key <= max_key_value){
                lengthmap.put(key, length);
            }
        }
    }

    private static int countCombi(int SUM_OF_THREE,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int count=0;
        for(int i = 0 ; i < lengthlist.size();i++){
            if(lengthlist.get(i) < SUM_OF_THREE / NUM_TO_BE_COMBINED){
                for(int j = i+1; lengthlist.get(j) < (SUM_OF_THREE-lengthlist.get(i))/2.0 ; j++){
//                    System.out.println("判定前：("+lengthlist.get(i)+","+lengthlist.get(j)+","+lengthmap.get(lengthlist.get(i)+lengthlist.get(j))+")");
                    if(lengthmap.get(lengthlist.get(i)+lengthlist.get(j)) != null){
//                        System.out.println("全部通過：("+lengthlist.get(i)+","+lengthlist.get(j)+","+lengthmap.get(lengthlist.get(i)+lengthlist.get(j))+")\n");
                        count++;
                    }
                }
            }else{
                break;
            }
        }
        return count;
    }

}//クラスの終了
