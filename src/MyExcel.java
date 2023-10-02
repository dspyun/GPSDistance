import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class MyExcel {

    float lat1, lat2, lng1, lng2;
    String lat1_str, lat2_str, lng1_str, lng2_str;

    public MyExcel(String filename) {

    }

    public MyExcel() {
        //initInfo = readInitFile();
    }

    public String GPSExcel2Distance(String excelfile) {

        String result = "";

        try {
            InputStream is =  new FileInputStream(excelfile);
            Workbook wb = Workbook.getWorkbook(is);
            if(wb != null) {
                Sheet sheet = wb.getSheet(0);   // 시트 불러오기
                if(sheet != null) {
                    // line1, col1에서 contents를 읽는다.
                    // 현재 컬럼의 내용을 추가한다.
                    int size = sheet.getColumn(0).length;
                    for(int i=0; i < size; i++) {
                        lat1_str = sheet.getCell(0, i).getContents();
                        lng1_str = sheet.getCell(1, i).getContents();
                        lat2_str = sheet.getCell(2, i).getContents();
                        lng2_str = sheet.getCell(3, i).getContents();
                        lat1 = Float.parseFloat(lat1_str);
                        lng1 = Float.parseFloat(lng1_str);
                        lat2= Float.parseFloat(lat2_str);
                        lng2 = Float.parseFloat(lng2_str);
                        float distance = gpsdistance(lat1,lng1,lat2,lng2);
                        System.out.println(Float.toString(distance));
                        result += Float.toString(distance*1000) + "\n";
                    }
                }
            }
            wb.close();
            is.close();
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        return result;
    }

    public float gpsdistance(float lat1, float lon1, float lat2, float lon2) {
        float R = 6371; // 지구 반지름 (단위: km)
        float dLat = deg2rad(lat2 - lat1);
        float dLon = deg2rad(lng2 - lng1);
        float a = (float) (Math.sin(dLat/2) * Math.sin(dLat/2) +
                          Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                  Math.sin(dLon/2) * Math.sin(dLon/2));
        float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));
        float distance = R * c; // 두 지점 간의 거리 (단위: km)
        return distance;
    }

    public float deg2rad(float deg) {
        return (float) (deg * (Math.PI/180));
    }



}
