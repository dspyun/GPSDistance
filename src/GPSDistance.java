import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GPSDistance {

    public static void main(String[] args){

        MyExcel myexcel = new MyExcel();
        //String s = argss[0];
        //System.out.println(args[0]);
        //String[] args = {"test.xls"};
        if (args.length >= 1) {
            String result = myexcel.GPSExcel2Distance(args[0]);
            save_file(result);
        }else {
            System.err.println("no filename supplied");
        }
    }

    public static void save_file(String data) {
        File file = new File("distance.txt");

        try {
            //파일에 문자열을 쓴다.
            //하지만 이미 파일이 존재하면 모든 내용을 삭제하고 그위에 덮어쓴다
            //파일이 손산될 우려가 있다.
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
