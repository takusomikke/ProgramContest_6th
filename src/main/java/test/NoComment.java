package test;

import java.util.Scanner;

public class NoComment {

    private static final int NUM_TO_BE_COMBINED = 3;
    private static final int MIN_SUM_OF_TWO = 3;
    private static int SUM_OF_THREE;
    private static int NUM_TO_BE_ENTERD;
    private static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SUM_OF_THREE= sc.nextInt();
        NUM_TO_BE_ENTERD = sc.nextInt();
        int[] keyarray= new int[SUM_OF_THREE - MIN_SUM_OF_TWO];
        int[] sortedlengtharray = new int[NUM_TO_BE_ENTERD];
        listenToLength(sc,sortedlengtharray,keyarray);
        int count =countCombi(sortedlengtharray,keyarray);
        System.out.println(count);
    }

    private static void listenToLength(Scanner sc,int[] sortedlengtharray,int[] keyarray){
        int length;
        int key;
        int max_key_value = (SUM_OF_THREE/NUM_TO_BE_COMBINED)*2-1;
        int zerocount=0;

        int[] tmp_lengtharray = new int[SUM_OF_THREE+1];
        for(int i = 0 ; i < NUM_TO_BE_ENTERD;i++){
            length = sc.nextInt();
            key = SUM_OF_THREE -length;
            if(1 <= length && length <= SUM_OF_THREE - MIN_SUM_OF_TWO){
                tmp_lengtharray[length-1]=length;
            }
            if(MIN_SUM_OF_TWO <= key && key <= max_key_value){
                keyarray[key]=1;
            }
        }
        for(int i =0; i < SUM_OF_THREE; i++){
            if(tmp_lengtharray[i] == 0){
                zerocount++;
            }else{
                sortedlengtharray[i-zerocount] = tmp_lengtharray[i];
                max = tmp_lengtharray[i];
            }
        }
    }

    private static int countCombi(int[] sortedlengtharray,int[] keyarray){
        if(max < (double)SUM_OF_THREE / NUM_TO_BE_COMBINED +1){
            return 0;
        }
        int count=0;
        for(int i = 0 ; sortedlengtharray[i] < SUM_OF_THREE / NUM_TO_BE_COMBINED;i++){
            for(int j = i+1; sortedlengtharray[j] < (SUM_OF_THREE-sortedlengtharray[i]) /2.0; j++){
                count += keyarray[sortedlengtharray[i]+sortedlengtharray[j]];
            }
        }
        return count;
    }
}