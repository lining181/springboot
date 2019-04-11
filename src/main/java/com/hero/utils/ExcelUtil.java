package com.hero.utils;//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Excel工具类
 * @author suzhijie
 * @date 2018/4/10/下午4:43
 */
@Slf4j
public class ExcelUtil {
    /**
     * 创建Excel
     *
     * @param fileName 文件名称
     * @param fileFullPath         文件路径
     * @param dataMap 数据内容
     */
    public static void createExcel(String fileName, String fileFullPath, Map<String,Map<String,String>> dataMap,StringBuffer tableHeaderStr) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(fileFullPath,fileName));
            write(out, dataMap, tableHeaderStr);
            log.info("生成" + fileName + "成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.info("生成" + fileName + "失败");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成Excel文件的方法write
     *
     * @param outputStream
     * @throws
     */
    public static void write(OutputStream outputStream, Map<String,Map<String,String>> dataMap,StringBuffer tableHeaderStr) {
        // 初始一个workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建单个sheet
        HSSFSheet sheet = workbook.createSheet("sheet0");
        // 创建第一行，设置列名
        HSSFRow row0 = sheet.createRow(0);
        row0.setHeight((short) 400);             // 设置行高
        HSSFCell cell = null;                    // Excel的列
        HSSFFont font = workbook.createFont();          // 设置字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   // 粗体显示
        font.setColor(HSSFFont.COLOR_NORMAL);           // 设置单元格字体的颜色
        HSSFCellStyle style = workbook.createCellStyle();   // 样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置边框样式
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置边框颜色
        style.setTopBorderColor(HSSFColor.BLACK.index);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置文本格式
        HSSFDataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        String[] tableHeader = tableHeaderStr.toString().split(";");
        short cellNumber = (short) tableHeader.length;  // 表的列数
        for (int k = 0; k < cellNumber; k++) {
            int i = 0;
            i += k;
            short b = 5000;
            cell = row0.createCell(i);          // 创建第0行第k列
            cell.setCellValue(tableHeader[k]);  // 设置第0行第k列的值
            sheet.setColumnWidth(i, b);         // 设置列的宽度
            style.setFont(font);                // 设置字体风格
            cell.setCellStyle(style);
        }


        HSSFCellStyle styleContext = workbook.createCellStyle();
        styleContext.setWrapText(true);
        styleContext.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        // 设置边框样式
        styleContext.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleContext.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleContext.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleContext.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置边框颜色
        styleContext.setTopBorderColor(HSSFColor.BLACK.index);
        styleContext.setBottomBorderColor(HSSFColor.BLACK.index);
        styleContext.setLeftBorderColor(HSSFColor.BLACK.index);
        styleContext.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置文本格式
        styleContext.setDataFormat(format.getFormat("@"));

        if (dataMap.size() > 0) {
            Set<Map.Entry<String , Map<String,String>>> set=dataMap.entrySet();

            //1、获取Iterator对象，并用此对象的方法遍历键值对对象
            Iterator<Map.Entry<String , Map<String,String>>> iter=set.iterator();

            Map<String,String> vlMap = null;
            Iterator<Map.Entry<String , String>> vlIter=null;
            Set<Map.Entry<String , String>> vlSet = null;
            Integer rowIndex = 1;
            Integer cellIndex = 0;
            HSSFRow row = null;
            while(iter.hasNext()){
                // 2、创建剩余行
                row = sheet.createRow(rowIndex);
                Map.Entry<String , Map<String,String>> me=iter.next();
                //3、根据键值对对象获取键和值
                vlMap = me.getValue();
                for (int i=0;i<tableHeader.length;i++){
                    cell = row.createCell(i);
                    cell.setCellStyle(styleContext);// 设置风格
                    switch (i){
                        case 0:
                            cell.setCellValue(vlMap.get("wxOrderSn"));
                            break;
                        case 1:
                            cell.setCellValue(vlMap.get("wxOrderMoney"));
                            break;
                        case 2:
                            cell.setCellValue(vlMap.get("wxOrderTime"));
                            break;
                        case 3:
                            cell.setCellValue(vlMap.get("mgoOrderSn"));
                            break;
                        case 4:
                            cell.setCellValue(vlMap.get("mgoOrderMoney"));
                            break;
                        case 5:
                            cell.setCellValue(vlMap.get("mgoOrderTime"));
                            break;
                    }
                }
                rowIndex+=1;
            }
        } else {
            HSSFRow row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("没有对账信息");
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
