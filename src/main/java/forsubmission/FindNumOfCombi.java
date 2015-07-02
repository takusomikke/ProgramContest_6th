package forsubmission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FindNumOfCombi {

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

        Scanner sc = new Scanner(System.in);

        //3本の合計値を入力。
        final int SUM_OF_THREE= sc.nextInt();

        //入力される本数を入力。
        int NUM_TO_BE_ENTERD = sc.nextInt();

        //それぞれの長さを格納するリストを作成。
        List<Integer> lengthlist= new ArrayList<Integer>();

        //3つ目の数字を見つけるためのマップを作成。
        Map<Integer,Integer> lengthmap = new HashMap<Integer,Integer>();

        //入力を受け付け、リストとマップを生成。
        createByHand(sc,SUM_OF_THREE,NUM_TO_BE_ENTERD,lengthlist,lengthmap);

        //生成されたリストのソート
        Collections.sort(lengthlist);

        //組み合わせを数え上げる。
        int count =countCombi(SUM_OF_THREE,lengthlist,lengthmap);

        System.out.println(count);

    }//メインメソッドの終了

    /*
     * 標準入力からの入力を受け付け、入力された数字を格納するリストと、
     * 入力された数字が3文字目として必要にされるときの値をキーとしたマップを生成する。
     *
     * @param SUM_OF_THREE     //3本の合計値
     * @param NUM_TO_BE_ENTERD        //入力を受け付ける本数
     * @param lengthlist    //入力された数字を格納する。のちにソートされる。
     * @param lengthmap     //入力された数字を必要とする数字をキーとして、入力された値を格納。
     *
     * 必要とする数字の例。SUM_OF_THREEが10で、入力された数字が7の場合。
     * 1本目、2本目の値の合計が3の時に3本目として7が必要とされる。
     * よって、キーを3、値を7としている。
     *
     * マップ格納時のif文は、キーが絶対に呼び出されることのない値のときに格納せず、
     * メモリーを無駄に使わないようにしている。
     */
    private static void createByHand(Scanner sc,int SUM_OF_THREE,int NUM_TO_BE_ENTERD,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int length;
        for(int i = 0 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();
            lengthlist.add(length);
            if(SUM_OF_THREE-length <= length * 2 - MIN_NUM_OF_SUM){
                lengthmap.put(SUM_OF_THREE-length, length);
            }
        }
    }

    /*
     * ソートされていることが前提のアルゴリズム。
     *
     * @param SUM_OF_THREE  //3本の合計値
     * @param lengthlist    //入力された数字がソート済みのリスト
     * @param lengthmap     //入力された数字を必要とする数字をキーとして、入力された値を格納されたマップ。
     *
     * return       //3本の合計がSUM_OF_THREEのものをカウントし、返す。
     *
     * ■for文の処理回数を絞る条件
     * 3本の組み合わせ(A,B,C)に対して
     * for(int j = i+1)でA < Bの条件を付与していて、
     * B < C の条件は言い換えると BがBとCの合計値の半分を超えない範囲なので
     * lengthlist.get(j)<(SUM_OF_THREE-lengthlist.get(i))/2.0 でその条件を付与している。
     * また、Aが合計の1/3を超える組み合わせはあり得ないので、超えた時点でbreak。
     *
     * ■カウント条件
     * 以上の制約を潜り抜け、ハッシュマップがnullを返さなければカウントする。
     *
     */
    private static int countCombi(int SUM_OF_THREE,List<Integer> lengthlist, Map<Integer,Integer> lengthmap){
        int count=0;
        for(int i = 0 ; i < lengthlist.size();i++){
            if(lengthlist.get(i) < SUM_OF_THREE / NUMB_TO_BE_COMBINED){
                for(int j = i+1; lengthlist.get(j) < (SUM_OF_THREE-lengthlist.get(i)) / 2.0 ; j++){
                    if(lengthmap.get(lengthlist.get(i)+lengthlist.get(j)) != null){
                        count++;
                    }
                }
            }
            else{
                break;
            }
        }
        return count;
    }
}//クラスの終了

