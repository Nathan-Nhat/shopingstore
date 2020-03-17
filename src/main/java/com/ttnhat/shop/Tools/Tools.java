package com.ttnhat.shop.Tools;

import com.ttnhat.shop.Controller.ResponseObject.AnalystOrdersDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystRevenueDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

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
        long timeBefore = time - (long) numDays * 24 * 60 * 60 *1000;
        Date newDate = new Date(timeBefore);
        return  newDate;
    }
    static public Date getAfterDate(Date date, Integer numDays){
        long time = date.getTime();
        long timeAfter = time + (long)numDays*24*60*60*1000;
        Date newDate = new Date(timeAfter);
        return newDate;
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
    static public Date convertDateToNoTime(Date inputDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        return formatter.parse(formatter.format(inputDate));
    }
    static public int compareDate(Date date1, Date date2){
        try {
            Date first = convertDateToNoTime(date1);
            Date second = convertDateToNoTime(date2);
            return first.compareTo(second);
        } catch (ParseException e){
            throw new RuntimeException("Cannot parse to Date");
        }
    }

    static public List<AnalystOrdersDTO> fillEmptyDateOrders(List<AnalystOrdersDTO> analystOrdersDTOS, Date beginDate){
       List<AnalystOrdersDTO> result = new ArrayList<>();
        Date tempDate = beginDate;

        Date nowDate = new Date();
        int i = 0;
        int length = analystOrdersDTOS.size();
        while (compareDate(tempDate, nowDate) < 0) {
            AnalystOrdersDTO element = analystOrdersDTOS.get(i);
            if (compareDate(element.getDate(), tempDate) == 0 ){
                result.add(element);
                if (i < length - 1)
                i ++;
            } else {
                AnalystOrdersDTO temp = null;
                try {
                    temp = new AnalystOrdersDTO(convertDateToNoTime(tempDate), new Long(0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                result.add(temp);
            }
            tempDate = getAfterDate(tempDate, 1);
        }
        return result;
    }
    static public List<AnalystRevenueDTO> fillEmptyDateRevenue(List<AnalystRevenueDTO> analystRevenueDTOS, Date beginDate){
        List<AnalystRevenueDTO> result = new ArrayList<>();
        Date tempDate = beginDate;
        Date nowDate = new Date();
        if (analystRevenueDTOS.isEmpty()) analystRevenueDTOS.add(new AnalystRevenueDTO(nowDate, new Long(0)));
        int i = 0;
        int length = analystRevenueDTOS.size();
        while (compareDate(tempDate, nowDate) < 0) {
            AnalystRevenueDTO element = analystRevenueDTOS.get(i);
            if (compareDate(element.getDate(), tempDate) == 0 ){
                result.add(element);
                if (i < length - 1)
                    i ++;
            } else {
                AnalystRevenueDTO temp = null;
                try {
                    temp = new AnalystRevenueDTO(convertDateToNoTime(tempDate), new Long(0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                result.add(temp);
            }
            tempDate = getAfterDate(tempDate, 1);
        }
        return result;
    }
}
