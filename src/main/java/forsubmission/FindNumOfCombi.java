package forsubmission;

import java.util.Scanner;

public class FindNumOfCombi {

    /*
     * 組み合わせる本数
     */
    private static final int NUM_TO_BE_COMBINED = 3;

    /*
     * 棒の長さは、1以上の自然数で重複なしなので
     * 2本の合計の最小値は 1 + 2 = 3である。
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

    /*
     * mainメソッド
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SUM_OF_THREE= sc.nextInt();
        NUM_TO_BE_ENTERD = sc.nextInt();

        //keyを格納する配列を作ります。
        int[] keyarray= new int[SUM_OF_THREE+1];

        //入力される数字を格納します。
        int[] lengtharray = new int[NUM_TO_BE_ENTERD];

        //入力を受け付け、リストとマップを生成。
        listenToLength(sc,lengtharray,keyarray);

        //組み合わせを数え上げる。
        int count =countCombi(lengtharray,keyarray);

        System.out.println(count);

    }//メインメソッドの終了

    /*
     * 重複した数字が入力されないことが前提のアルゴリズム
     *
     * 標準入力からの入力を受け付け、
     * 入力された数字が3文字目として必要にされるときの値をindexとし、
     * 値を1として格納する配列を生成する。
     *
     * @param SUM_OF_THREE     //3本の合計値
     * @param NUM_TO_BE_ENTERD        //入力を受け付ける本数
     * @param lengtharray       //3本目として必要とされるときの値をindexとし、値には1を代入する。
     *                          //前提がカウントするだけではなく組み合わせの表示もしたい場合は1ではなく、lengthを入れる。
     * @param key           //3本の合計値から入力された数字を引いたもの、すなわち2本目までの合計値を示す。
     * @param max_key_value //キーとしての最大値を示す(※)。
     *
     * ※ 3本の組み合わせ(A,B,C)に対して A < B < C の条件が付いているとき
     *
     */
    private static void listenToLength(Scanner sc,int[] lengtharray,int[] keyarray){

        int length;
        int key;
        int max_key_value = (SUM_OF_THREE/NUM_TO_BE_COMBINED)*2-1;

        int zerocount=0;
        int[] tmp = new int[SUM_OF_THREE];

        for(int i = 0 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();
            key = SUM_OF_THREE -length;

            if(1 <= length && length <= SUM_OF_THREE - MIN_SUM_OF_TWO){
                tmp[length]=length;
            }

            if(MIN_SUM_OF_TWO <= key && key <= max_key_value){
//                System.out.println("length:"+length+"key:"+key);
                keyarray[key]=1;
            }
        }
        for(int i =0; i < SUM_OF_THREE; i++){
            if(tmp[i] == 0){
                zerocount++;
            }else{
                lengtharray[i-zerocount] = tmp[i];
            }
        }
    }

    /*
     * @param SUM_OF_THREE  //3本の合計値
     * @param lengthlist    //3本目として必要とされる数字がindexとして格納され、値は1が格納された配列
     * return       //3本の合計がSUM_OF_THREEのものをカウントし、返す。
     *
     * ■for文の処理回数を絞る条件
     * 3本の組み合わせ(A,B,C)に対して、A < B < C の条件を付与。
     * また、Aが合計の1/3を超える組み合わせはあり得ないので、超えた時点でbreak。
     *
     * ■カウント条件
     * 以上の制約内ですべて足す(3本目が存在していなければ0を返すので条件判定をせずにすべて足している)
     *
     */
    private static int countCombi(int[] lengtharray,int[] keyarray){
        int count=0;
        for(int i = 0 ; lengtharray[i] < SUM_OF_THREE / NUM_TO_BE_COMBINED;i++){
            for(int j = i+1; lengtharray[j] < (SUM_OF_THREE-lengtharray[i]) /2.0 ; j++){
                count += keyarray[lengtharray[i]+lengtharray[j]];
            }
        }
        return count;
    }
}//クラスの終了

