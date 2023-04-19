
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xwpf.usermodel.*;
import pojo.Fund;

import java.io.*;
import java.util.*;


/**
 *问题1：GUI界面文件读取路径、输出路径的自定义化
 *问题2：一三季度 二四季度模板不一样
 */
public class WriteWord {
    public static ArrayList<Fund> truedata = new ArrayList<>();
    public static void insertAndOutFile(String templateFilePath, String outFilePath, Map<String, String> map) throws IOException {
//        生成docx对象
        InputStream is = new FileInputStream(templateFilePath);
        XWPFDocument docx = new XWPFDocument(is);
//        获取表格
        List<XWPFTable> tables = docx.getTables();
        for (XWPFTable table : tables) {
            //        定位到第一个表格
            //        遍历该表格所有的行
            for (int i = 0; i < table.getRows().size(); i++) {
                XWPFTableRow row = table.getRow(i);
//        遍历该行所有的列
                for (int k = 0; k < row.getTableCells().size(); k++) {
                    XWPFTableCell cell = row.getTableCells().get(k);
//                获取该格子里所有的段
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph p : paragraphs) {
//                    遍历该格子里面所有的段
                        List<XWPFRun> runs = p.getRuns();
                        for (XWPFRun run : runs) {
                            String str = run.toString();
                            System.out.println(str);
                            Set<String> keySet = map.keySet();
                            for (String key : keySet) {
                                if (str.trim().equals(key))
                                    run.setText(map.get(key), 0);
                            }
                        }
                    }
                }

            }
            OutputStream os = new FileOutputStream(outFilePath);
            docx.write(os);
            is.close();
            os.close();
        }

    }
    public static void out(String in,String out,String templatePath) throws Exception {
        truedata = ReadExcel.readexcel(in);
        int i = 1;
        for(String s:Sequence.proSequence){
            if (s.equals("")){
                i++;
                continue;
            }
            for(Fund fund:truedata){
                if(fund.getProName().equals(s)){
                    Calendar date = Calendar.getInstance();
                    String year = String.valueOf(date.get(Calendar.YEAR));
                    int month = date.get(Calendar.MONTH);
                    int quarter = month%3==0?month/3:(month/3)+1;
                    String quarter1 = String.valueOf(quarter);
                    String company = fund.getCompany();
                    String proname = fund.getProName();
                    String money = fund.getFcycle_investment();
                    String service = fund.getService_charge();
                    String management = fund.getManagement_fee();
                    String tax = fund.getValue_added_tax_and_additional_taxes();
                    String sum = fund.getSum();
                    Map<String, String> map = new HashMap<>();
                    map.put("year",year);
                    map.put("month", "3");
                    map.put("day", "21");
                    map.put("quarter", quarter1);
                    map.put("company", company);
                    map.put("proname", proname);
                    map.put("money", money);
                    map.put("servic", service);
                    map.put("anagemen", management);
                    map.put("tax", tax);
                    map.put("sum", sum);
                    insertAndOutFile(templatePath, out+"\\"+year+"年"+quarter1+"季度服务费管理费通知"+i+"-"+proname+".docx", map);
                }
            }
            i=i+1;
        }

    }
}
