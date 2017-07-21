package com.shinyleo.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhugh on 17/7/20.
 */
public class FileUtil {

    public static final String URF8 = "UTF-8";

    public FileUtil() {
    }

    public static String readFile(String filePath) {
        String fileContent = "";

        try {
            File e = new File(filePath);
            if(e.isFile() && e.exists()) {
                FileInputStream fin = new FileInputStream(e);
                InputStreamReader read = new InputStreamReader(fin, "UTF-8");

                BufferedReader reader;
                String line;
                for(reader = new BufferedReader(read); (line = reader.readLine()) != null; fileContent = fileContent + line + "\n") {
                    ;
                }

                reader.close();
                read.close();
                fin.close();
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }finally {

        }

        return fileContent;
    }

    public static List readFolder(List<Map<String, String>> list, String folderPath) {
        if(list == null) {
            return null;
        } else {
            File file = new File(folderPath);
            File[] tempList = file.listFiles();
            if(tempList != null && tempList.length > 0) {
                File[] var7 = tempList;
                int var6 = tempList.length;

                for(int var5 = 0; var5 < var6; ++var5) {
                    File tmpFile = var7[var5];
                    HashMap map = new HashMap();
                    map.put("name", file.getName());
                    map.put("path", file.getPath());
                    list.add(map);
                    if(tmpFile.isDirectory()) {
                        readFolder(list, tmpFile.getPath());
                    }
                }
            }

            return list;
        }
    }

    public static void writeFile(String content, String writePath, String charCoder) {
        try {
            File e = new File(writePath);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(e), charCoder);
            BufferedWriter reader = new BufferedWriter(osw);
            reader.write(content);
            osw.flush();
            reader.close();
            osw.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public static void createFolder(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

    }

    public static void delFolders(String folderPath) {
        File file = new File(folderPath);
        if(!file.isDirectory()) {
            file.delete();
        } else {
            File[] tempList = file.listFiles();
            if(tempList != null && tempList.length > 0) {
                File[] var6 = tempList;
                int var5 = tempList.length;

                for(int var4 = 0; var4 < var5; ++var4) {
                    File tmpFile = var6[var4];
                    if(tmpFile.isDirectory()) {
                        delFolders(tmpFile.getPath());
                    } else {
                        tmpFile.delete();
                    }
                }
            } else {
                file.delete();
            }

            delFolders(file.getPath());
        }
    }

    public static void delFile(String path) {
        File file = new File(path);
        if(file.exists()) {
            file.delete();
        }

    }

    public static void writeTmpFile(String fileName, String content) {
        String strDir = System.getProperty("user.dir");
        String filepath = strDir + File.separatorChar + fileName + ".tmp";
        writeFile(content, filepath, "utf-8");
    }

    public static String readTmpFile(String fileName) {
        String strDir = System.getProperty("user.dir");
        String filepath = strDir + File.separatorChar + fileName + ".tmp";
        return readFile(filepath);
    }
}
