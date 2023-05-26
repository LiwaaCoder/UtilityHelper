package com.example.UtilityHelper;

import java.io.File;

public class FileUtility {

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static boolean renameFile(String oldFilePath, String newFilePath) {
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        return oldFile.renameTo(newFile);
    }

    public static String getFileExtension(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}
