package com.hadoop.hdfs;

import com.hadoop.utils.Tools;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsTest {
    /**
     * 一个测试hdfs的方法
     * @param args
     */
    public static void main(String[] args) {
        FileSystem fileSystem=null;
        try {
             fileSystem = FileSystem.get(Tools.configuration);
             // 把  a.txt 上传到 hdfs 上
            fileSystem.copyFromLocalFile(new Path("C:\\Users\\hekaikai\\Desktop\\a.txt"),new Path("/hdfs/mapred_input/a.txt"));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileSystem.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
