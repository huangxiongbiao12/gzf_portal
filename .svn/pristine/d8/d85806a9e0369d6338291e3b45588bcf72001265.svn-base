package com.gzf.util;

//生成Excel的类
import com.gzf.util.storage.StorageProperties;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

/**
 * Created by yangweiguang on 2017/6/15.
 */
public class ExcelUtil {
    private Workbook rworkbook;
    private Sheet rsheet;
    private WritableWorkbook wbook;
    private WritableSheet wsheet;

    public String getFileFullName() {
        return fileFullName;
    }

    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName;
    }

    private String fileFullName;

    public static void main(String[] argv) {
        ExcelUtil excelUtil = new ExcelUtil((new StorageProperties()).getLocation() + "/" + "abc.xls");
        try {
            excelUtil.createExcel("数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
        excelUtil.addCell(0, 0, "good");
        excelUtil.addDataToColumnLast(0, "fwe");
        excelUtil.addDataToColumnLast(0, "INSTANCE love you");
        excelUtil.addDataToRowLast(0, "中文");
        excelUtil.closeExcel();
    }

    public ExcelUtil(String fileFullName) {
        this.fileFullName = fileFullName ;
    }

    //创建excel文件或者
    public void createExcel( String defaultSheetName) {
        try {
            File file = new File(fileFullName);

            // 只获得文件名，无路径
            String fileName = file.getName();

            // 获取文件名和路径
            String fileFront = fileFullName.substring(0, fileFullName.lastIndexOf("."));

            // 获取文件后缀
            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (!fileName.equals("xls")) {
                System.out.println("后缀名错误，一定要xls，不是" + fileName);
                return;
            }

            if (!file.exists()) {
                // 创建文件
                wbook = Workbook.createWorkbook(new File(fileFullName));
            } else {
                rworkbook = Workbook.getWorkbook(new File(fileFullName));
                wbook = Workbook.createWorkbook(new File(fileFront + "_modified.xls"), rworkbook);
            }

            // 0表示第一个sheet,需首先创建sheet名字
            wsheet = wbook.createSheet(defaultSheetName, 0);
            // this.rsheet = rworkbook.getSheet(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //修改当前sheet名字
    public void setSheetName(String name) {
        wsheet.setName(name);
    }

    public void createNewSheet(String sheetName, int index) {
        try {
            wbook.createSheet(sheetName, index);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 关闭excel
    public void closeExcel() {
        try {
            // write函数必须要调用
            wbook.write();
            wbook.close();
            rworkbook.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 在任意一行添加数据
    public void addCell(int row, int column, String content) {
        try {
            Label label = new Label(row, column, content);
            wsheet.addCell(label);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 在新的一行添加数据
    public void addDataToColumnLast(int row, String Data) {
        try {
            int column = wsheet.getRows();
            wsheet.addCell(new Label(row, column, Data));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 在新的一列添加数据
    public void addDataToRowLast(int column, String Data) {
        try {
            int row = wsheet.getColumns();
            wsheet.addCell(new Label(row, column, Data));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void correctProperty() {
        try {
            // wbook.removeSheet(2); // 移除多余的标签页
            // wbook.removeSheet(3);
            //
            // wsheet.mergeCells(0, 0, 4, 0); // 合并单元格
            wsheet.setRowView(1, 600); // 设置行的高度
            wsheet.setColumnView(0, 30); // 设置列的宽度
            wsheet.setColumnView(1, 100); // 设置列的宽度
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readExcel() {
        try {
            wbook.close();
            rworkbook.close();
            // 选取指定的excel
            rworkbook = Workbook.getWorkbook(new File(this.fileFullName));
            // 选取制定的sheet
            Sheet sheet = rworkbook.getSheet(0);
            // 选取指定的cell
            // 遍历循环得到所要的cell值
            for (int j = 0; j < sheet.getRows(); j++)
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    // 获取该cell的值
                    String var1 = cell.getContents();
                    // 打印输出该值
                    System.out.println(var1);
                }
            rworkbook.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
