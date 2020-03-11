package com.ttnhat.shop.Tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tools {
    static public String convertStringToOrder(String type, String sort){
        String sqlFinal = "";
        if (type.length() == 0){
            sqlFinal = "";
        } else {
            String[] sqlArrType = type.split(",");
            String[] sqlArrSort = sort.split(",");
            List<String> sqlTypeSort = new ArrayList<>();
            for (int i = 0 ; i < sqlArrType.length; i++){
                String tempStr = sqlArrType[i] + " "+ sqlArrSort[i];
                sqlTypeSort.add(tempStr);
            }
            String sqlOrder = String.join(",", sqlTypeSort);
            sqlFinal = "order by "+ sqlOrder;
        }
        return sqlFinal;
    }

    static public Date findBeforeDays(Integer numDays){
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        long timeBefore = time - numDays * 24 * 60 * 60 *1000;
        Date newDate = new Date(timeBefore);
        System.out.println(newDate);
        return  newDate;
    }

    static public String exactNameToFullText(String name){
        int length = name.length();
        String[] arrRet = new String[length];
        String[] arrayStr = name.split(" ");
        for (int i = 0; i< arrayStr.length; i++){
            arrRet[i] = "+" +arrayStr[i] + "*";
        }
        String ret = String.join(" ", arrRet);
        return ret;
    }
}
