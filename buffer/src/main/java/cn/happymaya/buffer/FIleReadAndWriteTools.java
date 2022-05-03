package cn.happymaya.buffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class FIleReadAndWriteTools {

    public Integer reader(String filePath) throws Exception{
        int result = 0;
        try(Reader reader = new FileReader(filePath)) {
            int value = 0;
            while((value = reader.read()) != -1){
                result += value;
            }
        }
        return result;
    }

    public Integer bufferReader(String filePath) throws Exception{
        int result = 0;
        try(Reader reader = new BufferedReader(new FileReader(filePath))) {
            int value = 0;
            while((value = reader.read()) != -1){
                result += value;
            }
        }
        return result;
    }

}
