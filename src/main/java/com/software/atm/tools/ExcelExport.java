package com.software.atm.tools;

import com.software.atm.credit_card.Card;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ExcelExport {
    public static class ExcelGenerator {

        private List<Card> listRecords;
        private XSSFWorkbook workbook;
        private XSSFSheet sheet;

        public ExcelGenerator(List<Card> listRecords) {
            this.listRecords = listRecords;
            workbook = new XSSFWorkbook();
        }

        private void writeHeader() {
            sheet = workbook.createSheet("Exam Records");

            Row row = sheet.createRow(0);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);

            for (int i=0;i<4;i++) {
                sheet.setColumnWidth(i, 25 * 200);
            }



            createCell(row, 0, "Date", style);
            createCell(row, 1, "Name", style);
            createCell(row, 2, "lastname", style);
            createCell(row, 3, "Amount", style);

        }

        private void createCell(Row row, int columnCount, Object value, CellStyle style) {
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof BigDecimal) {
                cell.setCellValue(String.valueOf((BigDecimal) value));
            } else if (value instanceof Long) {
                cell.setCellValue((Long) value);
            } else if (value instanceof java.sql.Date) {
                cell.setCellValue((Date) value);
            } else {
                cell.setCellValue((String) value);
            }
            cell.setCellStyle(style);
        }

        private void write() {
            int rowCount = 1;

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);

            for (Card record : listRecords) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;

                createCell(row, columnCount++, record.getDate(),style);
                createCell(row, columnCount++, record.getUser().getName(), style);
                createCell(row, columnCount++, record.getUser().getLastName(), style);
                createCell(row, columnCount++, record.getAccount().getBalance(), style);

            }
        }

        public void generate(HttpServletResponse response) throws IOException {
            writeHeader();
            write();
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();

        }


    }
}