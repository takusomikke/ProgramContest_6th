/*
 * 3本の合計値(sumlength)と本数(number)を入力すると自動的に
 * 1,2,3,････ number という配列を生成することで
 * 仮想的に本数分の入力がされる。
 * 時間測定の機能もあるので、アルゴリズムのスピードも図れる
 *
 * ＜参考＞
 * 10000
 * 10000
 * と入力した場合組み合わせの数は　
 * 8328334
 * です。
 * 最速163ms
 *
 * 100000
 * 100000
 * と入力した場合の組み合わせの数は
 * 833283334
 * 13908ms
 *
 * 1000000
 * 1000000
 * と入力した場合の組み合わせの数は
 * 1728454710
 * 1950494ms
 */

package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FindNumOfCombiTest {

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

      /* 1本ずつの長さの入力を受け付けます。
       *
       * キーの値は3つ目の数字として呼び出されるときの値を入れています。
       * 例)入力された数字が2の場合
       * 1本目、2本目の値の合計が8の時に3本目として2が必要とされる。
       * よって、キーを8、値を2としている。
       */

      /*
       * 1本ずつ入力するか、自動生成か選択
       */
//      createByHand(sc,sumlength,number,lengthlist,lengthmap);
      createByAuto(sumlength,number,lengthlist,lengthmap);

      long start = System.currentTimeMillis();

      System.out.println("入力された長さを昇順にソートしています。");
      Collections.sort(lengthlist);
      System.out.println("ソートが完了しました。");

//      ソート後の配列がどうなってるのか確認
//      for(int i = 0 ; i < lengthlist.size() ; i++){
//          System.out.println(lengthlist.get(i));
//      }

      System.out.println("計算を開始します。");

      int count =countCombi(sumlength,lengthlist,lengthmap);

      long end = System.currentTimeMillis();
      System.out.println(count);

      System.out.println((end - start)  + "ms");

    }//mainメソッドの終わり


    private static void createByHand(Scanner sc,int sumlength,int number,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int length;
        for(int i = 0 ; i < number;i++){
            length = sc.nextInt();
            lengthlist.add(length);
            if(sumlength-length <= length*2 - 3){
                lengthmap.put(sumlength-length, length);
            }
        }
    }

    private static void createByAuto(int sumlength,int number,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        for(int length = 1 ; length <=number;length++){
            lengthlist.add(length);
            if(sumlength-length <= length*2 - 3){
                lengthmap.put(sumlength-length, length);
            }
        }
    }

    private static int countCombi(int sumlength,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int count=0;
        for(int i = 0 ; i < lengthlist.size();i++){
            if(lengthlist.get(i) < sumlength/3){
                for(int j = i+1; lengthlist.get(j) < (sumlength-lengthlist.get(i))/2.0 ; j++){
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
