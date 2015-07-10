package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Random {
    public static void main(String[] args) {
        int num_of_stick = 1000000;
        int sum_of_length = 1000000;

        List<Integer> list = new ArrayList<Integer>();

        for(int i = 1; i <= num_of_stick ; i++){
            list.add(i);
        }

        Collections.shuffle(list);

        PrintWriter writer;

        try{
            writer = new PrintWriter(new FileWriter("input_rand_1000000.txt"));

            writer.println(sum_of_length);
            writer.println(num_of_stick);

            for(int i = 0;i < num_of_stick; i++){
                writer.println(list.get(i));
            }

            writer.close();

        }catch(IOException e){
            System.out.println(e);
          }

        System.out.println("書き込み終了");

    }
}
