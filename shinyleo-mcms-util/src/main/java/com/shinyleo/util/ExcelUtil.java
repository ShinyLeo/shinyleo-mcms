package com.shinyleo.util;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhugh on 17/7/20.
 */
public class ExcelUtil {

    public ExcelUtil() {
    }

    public static final String exportExcel(String fileName, String[] titles, List<Object> listContent, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";

        try {
            ServletOutputStream e = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setContentType("application/msexcel");
            WritableWorkbook workbook = Workbook.createWorkbook(e);
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf_center.setAlignment(Alignment.CENTRE);
            wcf_center.setWrap(false);
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN);
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf_left.setAlignment(Alignment.LEFT);
            wcf_left.setWrap(false);

            int i;
            for(i = 0; i < titles.length; ++i) {
                sheet.addCell(new Label(i, 0, titles[i], wcf_center));
            }

            i = 1;

            for(Iterator var15 = listContent.iterator(); var15.hasNext(); ++i) {
                Object obj = var15.next();
                int j = 0;
                Object[] var20;
                int var19 = (var20 = (Object[])obj).length;

                for(int var18 = 0; var18 < var19; ++var18) {
                    Object field = var20[var18];
                    if(field instanceof Map) {
                        if(field != null) {
                            Map temp = (Map)field;
                            if(temp.get("format") != null && temp.get("format") instanceof WritableCellFormat) {
                                sheet.addCell(new Label(j, i, field != null?String.valueOf(temp.get("value")):"", (WritableCellFormat)temp.get("format")));
                            } else {
                                sheet.addCell(new Label(j, i, field != null?String.valueOf(field):"", wcf_left));
                            }
                        }
                    } else {
                        sheet.addCell(new Label(j, i, field != null?String.valueOf(field):"", wcf_left));
                    }

                    ++j;
                }
            }

            workbook.write();
            workbook.close();
        } catch (Exception var22) {
            result = "系统提示：Excel文件导出失败，原因：" + var22.toString();
            System.out.println(result);
            var22.printStackTrace();
        }

        return result;
    }
}
