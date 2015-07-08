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
    private static final int NUM_TO_BE_COMBINED = 3;

    /*
     * 棒の長さは、1以上の自然数で重複なしなので
     * 2本の最小値は 1 + 2 = 3である。
     */
    private static final int MIN_NUM_OF_SUM = 3;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //3本の合計値を入力。
        final int SUM_OF_THREE = sc.nextInt();

        //入力される本数を入力。
        final int NUM_TO_BE_ENTERD = sc.nextInt();

        //それぞれの長さを格納するリストを作成。
        List<Integer> lengthList = new ArrayList<Integer>();

        //3つ目の数字を見つけるためのマップを作成。
        Map<Integer,Integer> find3rdMap = new HashMap<Integer,Integer>();

        //入力を受け付け、リストとマップを生成。
        listenToLength(sc,SUM_OF_THREE,NUM_TO_BE_ENTERD,lengthList,find3rdMap);

        //生成されたリストのソート
        Collections.sort(lengthList);

        //組み合わせを数え上げる。
        int count = countCombi(SUM_OF_THREE,lengthList,find3rdMap);

        System.out.println(count);

    }//メインメソッドの終了

    /*
     * 標準入力からの入力を受け付け、入力された数字を格納するリストと、
     * 入力された数字が3文字目として必要にされるときの値をキーとしたマップを生成する。
     *
     * @param SUM_OF_THREE     //3本の合計値
     * @param NUM_TO_BE_ENTERD        //入力を受け付ける本数
     * @param lengthlist    //入力された数字を格納する。のちにソートされる。
     * @param find3rdMap    //入力された数字が3本目となるときの、2本目までの合計値をキーとしている
     * @param key           //3本の合計値から入力された数字を引いたもの、すなわち2本目までの合計値を示す
     * @param max_key_value //キーとしての最大値を示す(※)。
     *
     * ※ 3本の組み合わせ(A,B,C)に対して A < B < C の条件が付いているとき
     *
     */
    private static void listenToLength(Scanner sc,int SUM_OF_THREE,int NUM_TO_BE_ENTERD,List<Integer> lengthList, Map<Integer,Integer> find3rdMap){
        int length;
        int key;
        int max_key_value;

        for(int i = 0 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();
            lengthList.add(length);

            key = SUM_OF_THREE - length;
            max_key_value = length * 2 - MIN_NUM_OF_SUM;

            if( key <= max_key_value ){
                find3rdMap.put( key , length);
            }
        }
    }

    /*
     * ソートされていることが前提のアルゴリズム。
     *
     * @param SUM_OF_THREE  //3本の合計値
     * @param lengthlist    //入力された数字がソート済みのリスト
     * @param find3rdMap     //入力された数字を必要とする数字をキーとして、入力された値を格納されたマップ。
     *
     * return       //3本の合計がSUM_OF_THREEのものをカウントし、返す。
     *
     * ■for文の処理回数を絞る条件
     * 3本の組み合わせ(A,B,C)に対して、A < B < C の条件を付与。
     * また、Aが合計の1/3を超える組み合わせはあり得ないので、超えた時点でbreak。
     *
     * ■カウント条件
     * 以上の制約を潜り抜け、ハッシュマップがnullを返さなければカウントする。
     *
     */
    private static int countCombi(int SUM_OF_THREE,List<Integer> sortedLengthList, Map<Integer,Integer> find3rdMap){

        int count=0;

        for(int i = 0 ; i < sortedLengthList.size();i++){
            if(sortedLengthList.get(i) < SUM_OF_THREE / NUM_TO_BE_COMBINED){
                for(int j = i+1; sortedLengthList.get(j) < (SUM_OF_THREE-sortedLengthList.get(i)) / 2.0 ; j++){
                    if(find3rdMap.get(sortedLengthList.get(i)+sortedLengthList.get(j)) != null){
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

