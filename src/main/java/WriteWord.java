import org.apache.poi.xwpf.usermodel.*;
import pojo.Fund;

import java.io.*;
import java.util.*;
/*
* 做本次项目遇到的问题
* 1. Excel表格读取数据问题 每个Excel表格因为有不同的CellType 对不同的CellType不同的处理方法
* 2. double精度问题，使用BigDecimal类进行解决
* 3. 数据使用缩写而项目需要展开写的问题
* 4. Word数据定位问题
**/
public class WriteWord {
    public static void main(String[] args) throws Exception {
    out("E:\\system_files\\Desktop\\中江城投季度报表\\2023年2季度苏银棚改基金试算（城投）.xlsx","E:\\system_files\\Desktop\\中江城投季度报表\\2023年2季度","E:\\system_files\\Desktop\\中江城投季度报表模板\\新建 Microsoft Word 文档.docx");
    }
    public static ArrayList<Fund> truedata = new ArrayList<>();
    public static void insert(String templateFilePath, String outFilePath, Map<String, String> map) throws IOException {
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
//                            System.out.println(str);
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
    /*
    * 得到处理后的数据，将其写入模板并输出通知书
    * */
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
                    String sum = fund.getSum_three();
                    String money1 = fund.getRepay_capital();
                    Map<String, String> map = new HashMap<>();
                    map.put("year",year);
                    map.put("month", "5");
                    map.put("day", "30");
                    map.put("quarter", quarter1);
                    map.put("company", company);
                    map.put("proname", proname);
                    map.put("money", money);
                    map.put("servic", service);
                    map.put("anagemen", management);
                    map.put("tax", tax);
                    map.put("sum", sum);
                    map.put("money1", money1);
                    insert(templatePath, out+"\\"+year+"年"
                            +quarter1+"季度服务费管理费通知"+i+"-"+proname+".docx", map);
                }
            }
            i=i+1;
        }

    }
}
