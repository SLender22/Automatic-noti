import Utils.StringCope;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.Fund;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadExcel {
    private static String area;
    private static String proName;
    private static String company;
    private static String fcycle_investment;
    private static String days;
    private static String service_charge;
    private static String management_fee;
    private static String value_added_tax;
    private static String urban_construction_tax;
    private static String surcharge;
    private static String local_tax;
    private static String value_added_tax_surcharge;
    private static String value_added_tax_and_additional_taxes;
    private static String repay_capital;
    private static String sum;
    public static ArrayList<Fund> data = new ArrayList<>();
    private static ArrayList<String> funddata = new ArrayList<>();



    public static ArrayList<Fund> readexcel(String path) throws Exception {
        if (path.endsWith(".xlsx")){
            File file = new File(path);
            if(!file.exists())
                throw new Exception("文件不存在");
            FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
            datacopeX(sheets);
        }
//        else{
//            File file = new File(path);
//            if(!file.exists())
//                throw new Exception("文件不存在");
//            FileInputStream inputStream = new FileInputStream(file);
//                    HSSFWorkbook sheets = new HSSFWorkbook(inputStream);
//            datacopeH(sheets);
//        }
        return data;
    }
    public static void datacopeX(XSSFWorkbook sheets){
//        获取工作薄下sheet的个数
        int sheetNum = sheets.getNumberOfSheets();
//        System.out.println(sheetNum);

//        获取具体的sheet
        XSSFSheet sheet = sheets.getSheetAt(3);
        String sheetName = sheet.getSheetName();
//        System.out.println(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
//        System.out.println(rows);

//        遍历特定行的每一行，将数据存储到链表data中
        for (int j = 1;j<rows;j++){
            //        获得sheet下的特定行
            XSSFRow row = sheet.getRow(j);
            for(int i = 0;i<row.getPhysicalNumberOfCells()-1;i++){
//                if(row.getCell(i).getCellType().toString().equals("FORMULA")){
//                    Object value = String.valueOf(row.getCell(i).getNumericCellValue());
//                    funddata.add((String) value);
////                System.out.println(value);
//                    continue;
//                }
                if (row.getCell(i).getCellType().equals(CellType.NUMERIC)){
                    String s = row.getCell(i).toString();
                    BigDecimal bd = new BigDecimal(s);
                    String str = bd.toPlainString();
                    funddata.add(str);
                }
                else{
                    funddata.add(row.getCell(i).toString());
                }
                Fund fund = new Fund();
                if (i==row.getPhysicalNumberOfCells()-2){
                    area = funddata.get(0);
                    proName = funddata.get(1);
                    company = funddata.get(2);
                    fcycle_investment = StringCope.formatNum(new StringBuffer(funddata.get(3)));
                    days = funddata.get(4);
                    service_charge = StringCope.format(new StringBuffer(funddata.get(5)));
                    management_fee = StringCope.format(new StringBuffer(funddata.get(6)));
                    value_added_tax = StringCope.format(new StringBuffer(funddata.get(7)));
                    urban_construction_tax = StringCope.format(new StringBuffer(funddata.get(8)));
                    surcharge = StringCope.format(new StringBuffer(funddata.get(9)));
                    local_tax = StringCope.format(new StringBuffer(funddata.get(10)));
                    value_added_tax_surcharge = StringCope.format(new StringBuffer(funddata.get(11)));
                    value_added_tax_and_additional_taxes = StringCope.format(new StringBuffer(funddata.get(12)));
                        repay_capital = StringCope.format(new StringBuffer(funddata.get(13)));
                    sum = StringCope.format(new StringBuffer(funddata.get(14)));
                    fund = new Fund(area,proName,company,fcycle_investment,days,service_charge,management_fee,value_added_tax,
                            urban_construction_tax,surcharge,local_tax,value_added_tax_surcharge,value_added_tax_and_additional_taxes,
                            repay_capital,sum);
                    data.add(fund);
                    funddata.clear();
                }
            }
        }

    }


    public static void datacopeH(HSSFWorkbook sheets){
//        获取工作薄下sheet的个数
        int sheetNum = sheets.getNumberOfSheets();
//        System.out.println(sheetNum);

//        获取具体的sheet
        HSSFSheet sheet = sheets.getSheetAt(3);
        String sheetName = sheet.getSheetName();
//        System.out.println(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
//        System.out.println(rows);

//        遍历特定行的每一行，将数据存储到链表data中
        for (int j = 0;j<rows;j++){
            //        获得sheet下的特定行
            HSSFRow row = sheet.getRow(j);
            for(int i = 0;i<row.getPhysicalNumberOfCells()-1;i++){
                if(row.getCell(i).getCellType().toString().equals("FORMULA")){
                    Object value = String.valueOf(row.getCell(i).getNumericCellValue());
                    funddata.add((String) value);
//                System.out.println(value);
                    continue;
                }
                funddata.add(row.getCell(i).toString());
//            System.out.println(row.getCell(i).toString());
                Fund fund = new Fund();
                if (i==row.getPhysicalNumberOfCells()-2){
                    area = funddata.get(0);
                    proName = funddata.get(1);
                    company = funddata.get(2);
                    fcycle_investment = funddata.get(3);
                    days = funddata.get(4);
                    service_charge = funddata.get(5);
                    management_fee = funddata.get(6);
                    value_added_tax = funddata.get(7);
                    urban_construction_tax = funddata.get(8);
                    surcharge = funddata.get(9);
                    local_tax = funddata.get(10);
                    value_added_tax_surcharge = funddata.get(11);
                    value_added_tax_and_additional_taxes = funddata.get(12);
                    repay_capital = funddata.get(13);
                    sum = funddata.get(14);
                    fund = new Fund(area,proName,company,fcycle_investment,days,service_charge,management_fee,value_added_tax,
                            urban_construction_tax,surcharge,local_tax,value_added_tax_surcharge,value_added_tax_and_additional_taxes,
                            repay_capital,sum);
                    data.add(fund);
                    funddata.clear();
                }
            }
        }

    }
    }


