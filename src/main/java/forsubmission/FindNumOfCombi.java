package forsubmission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FindNumOfCombi {
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
         * キーの値は3つ目の数字として呼び出されるときの値を入れています。
         * 例)入力された数字が2の場合
         * 1本目、2本目の値の合計が8の時に3本目として2が必要とされる。
         * よって、キーを8、値を2としている。
         */
        int length;
        for(int i = 0 ; i < number;i++){
            length = sc.nextInt();
            lengthlist.add(length);
            //メモリを無駄に消費しないように絶対に呼ばれないマップを排除
            if(sumlength-length <= length*2 - 3){
                lengthmap.put(sumlength-length, length);
            }
        }

        /* 見つけるためのアルゴリズムの中核は
         * ソートされていることが前提のものなのでこれが大切
         */
//        System.out.println("入力された長さを昇順にソートしています。");
        Collections.sort(lengthlist);
//        System.out.println("ソートが完了しました。");

        int count=0;

        for(int i = 0 ; i < lengthlist.size();i++){
            if(lengthlist.get(i)>sumlength/3-1){
                break;
            }
            for(int j = i+1; lengthlist.get(j)<(sumlength-lengthlist.get(i))/2.0 ; j++){
                if(lengthlist.get(i)*2+lengthlist.get(j) ==sumlength){
                    continue;
                }
                if(lengthlist.get(i)+lengthlist.get(j)*2 ==sumlength){
                    continue;
                }
                if(lengthmap.get(lengthlist.get(i)+lengthlist.get(j)) == null){
                    continue;
                }

                    count++;
            }
        }

        System.out.println(count);
    }
}

