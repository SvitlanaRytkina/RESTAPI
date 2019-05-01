package com.rest.utils;

import com.rest.model.User;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XlsUtils {
    private static Logger LOG = Logger.getLogger(XlsUtils.class);

    public static List<User> read(String filePath) {
        LOG.info(String.format("Reading the users from file by path '%s'", filePath));
        List<User> users = new ArrayList<>();
        try {
            Workbook book = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = book.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                users.add(new User(getIntValue(row, 0), getStringValue(row, 1), getStringValue(row, 2),
                        getStringValue(row, 3), getStringValue(row, 4), getStringValue(row, 5),
                        getStringValue(row, 6), getStringValue(row, 7), getStringValue(row, 8), getStringValue(row, 9),
                        getStringValue(row, 10)));
            }
            book.close();
        } catch (IOException exception) {
            LOG.error(String.format("Users from file by path '%s' wasn't read", filePath));
        }
        return users;
    }

    public static User readNewUser(String filePath) {
        LOG.info(String.format("Reading the user from file by path '%s'", filePath));
        User user = new User();
        try {
            Workbook book = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = book.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                user = new User(getIntValue(row, 0), getStringValue(row, 1), getStringValue(row, 2), getDateValue(row, 3),
                        getStringValue(row, 4), getStringValue(row, 5), getStringValue(row, 6), getStringValue(row, 7),
                        getStringValue(row, 8), getStringValue(row, 9), getStringValue(row, 10));
            }
            book.close();
        } catch (IOException exception) {
            LOG.error(String.format("New user from file by path '%s' wasn't read", filePath));
        }
        return user;
    }

    public static String readAccessToken(String filePath) {
        LOG.info(String.format("Reading the access token from file by path '%s'", filePath));
        String token = "";
        try {
            Workbook book = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = book.getSheetAt(2);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            token = getStringValue(row, 0);
            book.close();
        } catch (IOException exception) {
            LOG.error(String.format("Access token from file by path '%s' wasn't read", filePath));
        }
        return token;
    }

    public static void updateSheet(String filePath, List<User> users) {
        LOG.info(String.format("Updating the users sheet from file by path '%s'", filePath));
        try {
            Workbook book = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = book.getSheetAt(0);
            users.forEach(u -> {
                Row row = sheet.createRow(u.getId());
                row.createCell(0).setCellValue(u.getId());
                row.createCell(1).setCellValue(u.getName());
                row.createCell(2).setCellValue(u.getGender());
                row.createCell(3).setCellValue(u.getDateOfBirth());
                row.createCell(4).setCellValue(u.getEmail());
                row.createCell(5).setCellValue(u.getPhone());
                row.createCell(6).setCellValue(u.getWebsite());
                row.createCell(7).setCellValue(u.getAddress());
                row.createCell(8).setCellValue(u.getStatus());
                row.createCell(9).setCellValue(u.getLink());
                row.createCell(10).setCellValue(u.getAvatarLink());
            });
            book.write(new FileOutputStream(filePath));
            book.close();
        } catch (IOException exception) {
            LOG.error(String.format("The file by path '%s' wasn't opened", filePath));
        }
    }

    private static int getIntValue(Row row, int cellNumber) {
        return (int) row.getCell(cellNumber).getNumericCellValue();
    }

    private static String getDateValue(Row row, int cellNumber) {
        return new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(cellNumber).getDateCellValue());
    }

    private static String getStringValue(Row row, int cellNumber) {
        return row.getCell(cellNumber).getStringCellValue();
    }
}
