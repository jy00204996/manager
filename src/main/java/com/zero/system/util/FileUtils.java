package com.zero.system.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static Integer i = 0;

    /**
     * 保存文件
     * @param file 文件
     * @param realPath  实际目录
     * @return 文件名[集合]
     */
    public static List<String> saveFile(String realPath, MultipartFile... file) {
        List<String> list = new ArrayList<>();
        try{
            for (MultipartFile childFile: file) {
                String filename = childFile.getOriginalFilename();
                String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                File folder = new File(realPath);
                if (!folder.exists() && !folder.isDirectory()) {
                    folder.mkdirs();
                }
                String path = DateUtilNew.getNowTimeStr()+(i)+ "." + suffix;
                childFile.transferTo(new File(realPath + path));
                list.add(path);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


}
