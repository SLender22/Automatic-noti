import Utils.StringCope;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.Fund;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

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

    private static String sum_three;

    public static ArrayList<Fund> data = new ArrayList<>();
    private static ArrayList<String> funddata = new ArrayList<>();

    public static void main(String[] args) {
/*    String是引用类型 所以创建的对象都是类似于一个指针的形式
      所以如下四行代码中，s在第二次定义的时候，指向了内存空间一个新的地址2，它的内容是"world"
      所以t指向的还是第一次定义的地址，值就是"hello"
 */
        String s = "hello";
        String t = s;
        s = "world";
        System.out.println(t);

        int l = 90;
        System.out.println((char) l);
        int i = 2147483647;
        long j = 123124124141L;
        float a = 3.4e38f;
        double b = 1.79e308;
        System.out.println(a);


        int old = 78;
        int newdata = 90;
        System.out.printf("成绩的百分比%.2f%%", ((newdata - old) * 1.0 / old) * 100);


        double x = 1 - 9.0 / 10;
        System.out.println(x);
        if (Math.abs(x - 0.1) < 0.00001)
            System.out.println("x is 0.1");
        int[] ns = {1, 2};
        ns = new int[5];
        System.out.println(Arrays.toString(ns));

    }
    public static ArrayList<Fund> readexcel(String path) throws Exception {
        if (path.endsWith(".xlsx")) {
            File file = new File(path);
            if (!file.exists())
                throw new Exception("文件不存在");
            FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
            datacopeX(sheets);

        }
        else{
            File file = new File(path);
            if(!file.exists())
                throw new Exception("文件不存在");
            FileInputStream inputStream = new FileInputStream(file);
            HSSFWorkbook sheets = new HSSFWorkbook(inputStream);
            datacopeH(sheets);
      }
        return data;
    }
/*这个方法未来的优化点：读取Excel表的时候，因为财务表格是规范的，
只是每一次数据不同，所以读取数据时候顺序是固定的。后期可以加入根据读到条目的名称进行对应数值的存储
*/
    public static void datacopeX(XSSFWorkbook sheets) {

        BigDecimal sum1 = new BigDecimal("0");
//        获取工作薄下sheet的个数
        int sheetNum = sheets.getNumberOfSheets();
//        获取具体的sheet
        XSSFSheet sheet = sheets.getSheetAt(3);
//        sheet的名称
        String sheetName = sheet.getSheetName();
//        sheet的总行数
        int rows = sheet.getPhysicalNumberOfRows();
//        遍历特定行的每一列，将数据存储到链表data中
        for (int j = 1; j < rows; j++) {
            sum1 = new BigDecimal("0");
            //        获得sheet下的特定行
            XSSFRow row = sheet.getRow(j);
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                if(i == 3){
                    double fullinvest = (row.getCell(i).getNumericCellValue())+0.5;
                    StringBuffer s = new StringBuffer(fullinvest+"");
                    int k = s.indexOf(".");
                    s = s.delete(k,s.length());
                    System.out.println(s.toString());
                    funddata.add(s.toString());
                }
              /*
              * 因为通知书有个条目是三个数据之和，所以为了减少Excel操作，将三数据之和移到这里进行*/
                if (i == 5 || i == 6 || i == 12) {
//                    因为double精度问题，这里使用BigDecimal类进行处理
                    sum1 = new BigDecimal(
                            String.valueOf(sum1.add(new BigDecimal(row.getCell(i).toString()))));
                    if (i == 12) {
                        funddata.add(sum1.toPlainString());
                    }
                }
                if (row.getCell(i).getCellType().equals(CellType.NUMERIC)) {
                    BigDecimal bd = new BigDecimal(row.getCell(i).toString());
                    String str = bd.toPlainString();
                    funddata.add(str);
                } else
                    funddata.add(row.getCell(i).toString());
                Fund fund = new Fund();
//                存储到funddata中，这里没有使用数据库，是考虑到方便在多个电脑上快速使用
                if (i == row.getPhysicalNumberOfCells() - 1) {
                    area = funddata.get(0);
                    proName = funddata.get(1);
                    company = funddata.get(2);
                    fcycle_investment = funddata.get(3);
                    days = funddata.get(4);
                    service_charge = StringCope.format(new StringBuffer(funddata.get(5)));
                    management_fee = StringCope.format(new StringBuffer(funddata.get(6)));
                    value_added_tax = StringCope.format(new StringBuffer(funddata.get(7)));
                    urban_construction_tax = StringCope.format(new StringBuffer(funddata.get(8)));
                    surcharge = StringCope.format(new StringBuffer(funddata.get(9)));
                    local_tax = StringCope.format(new StringBuffer(funddata.get(10)));
                    value_added_tax_surcharge = StringCope.format(new StringBuffer(funddata.get(11)));
                    value_added_tax_and_additional_taxes = StringCope.format(new StringBuffer(funddata.get(13)));
                    repay_capital = StringCope.format(new StringBuffer(funddata.get(14)));
                    sum = StringCope.format(new StringBuffer(funddata.get(15)));
                    sum_three = StringCope.format(new StringBuffer(funddata.get(12)));
                    fund = new Fund(area, proName, company, fcycle_investment, days, service_charge, management_fee,
                            value_added_tax, urban_construction_tax, surcharge, local_tax, value_added_tax_surcharge,
                            value_added_tax_and_additional_taxes,
                            repay_capital, sum, sum_three);
                    data.add(fund);
                    funddata.clear();
                }
            }
        }
    }
    /*HSSFWorkbook方法是针对文件后缀名为.doc的word版本*/
    public static void datacopeH(HSSFWorkbook sheets) {
        BigDecimal sum1 = new BigDecimal("0");
//        获取工作薄下sheet的个数
        int sheetNum = sheets.getNumberOfSheets();
//        System.out.println(sheetNum);

//        获取具体的sheet
        HSSFSheet sheet = sheets.getSheetAt(3);
        String sheetName = sheet.getSheetName();
//        System.out.println(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
//        System.out.println(rows);

//        遍历特定行的每一列，将数据存储到链表data中
        for (int j = 1; j < rows; j++) {
            sum1 = new BigDecimal("0");
            //        获得sheet下的特定行
            HSSFRow row = sheet.getRow(j);
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                if(i == 3){
                    double fullinvest = (row.getCell(i).getNumericCellValue())+0.5;
                    StringBuffer s = new StringBuffer(fullinvest+"");
                    int k = s.indexOf(".");
                    s = s.delete(k,s.length());
                    System.out.println(s.toString());
                    funddata.add(s.toString());
                }
                /*
                 * 因为通知书有个条目是三个数据之和，所以为了减少Excel操作，将三数据之和移到这里进行*/
                if (i == 5 || i == 6 || i == 12) {
//                    因为double精度问题，这里使用BigDecimal类进行处理
                    sum1 = new BigDecimal(
                            String.valueOf(sum1.add(new BigDecimal(row.getCell(i).toString()))));
                    if (i == 12) {
                        funddata.add(sum1.toPlainString());
                    }
                }
                if (row.getCell(i).getCellType().equals(CellType.NUMERIC)) {
                    BigDecimal bd = new BigDecimal(row.getCell(i).toString());
                    String str = bd.toPlainString();
                    funddata.add(str);
                } else
                    funddata.add(row.getCell(i).toString());
                Fund fund = new Fund();
//                存储到funddata中，这里没有使用数据库，是考虑到方便在多个电脑上快速使用
                if (i == row.getPhysicalNumberOfCells() - 1) {
                    area = funddata.get(0);
                    proName = funddata.get(1);
                    company = funddata.get(2);
                    fcycle_investment = funddata.get(3);
                    days = funddata.get(4);
                    service_charge = StringCope.format(new StringBuffer(funddata.get(5)));
                    management_fee = StringCope.format(new StringBuffer(funddata.get(6)));
                    value_added_tax = StringCope.format(new StringBuffer(funddata.get(7)));
                    urban_construction_tax = StringCope.format(new StringBuffer(funddata.get(8)));
                    surcharge = StringCope.format(new StringBuffer(funddata.get(9)));
                    local_tax = StringCope.format(new StringBuffer(funddata.get(10)));
                    value_added_tax_surcharge = StringCope.format(new StringBuffer(funddata.get(11)));
                    value_added_tax_and_additional_taxes = StringCope.format(new StringBuffer(funddata.get(13)));
                    repay_capital = StringCope.format(new StringBuffer(funddata.get(14)));
                    sum = StringCope.format(new StringBuffer(funddata.get(15)));
                    sum_three = StringCope.format(new StringBuffer(funddata.get(12)));
                    fund = new Fund(area, proName, company, fcycle_investment, days, service_charge, management_fee,
                            value_added_tax, urban_construction_tax, surcharge, local_tax, value_added_tax_surcharge,
                            value_added_tax_and_additional_taxes,
                            repay_capital, sum, sum_three);
                    data.add(fund);
                    funddata.clear();
                }
            }
        }
    }
}


