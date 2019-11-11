package com.hadoop.mapreduce;


import com.hadoop.mapper.CountMapper;
import com.hadoop.reducer.CountReduce;
import com.hadoop.utils.Tools;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class CountMain {

    /*static {
        try {
            //强制系统加载 hadoop.dll 文件
            System.load("D:/software/hadoop/hadoop-common-2.2.0-bin-master/bin/hadoop.dll");
        }catch (Exception e){
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }*/

    /**
     * 这是mapreduce的入口,前提是先把文件传到hdfs上
     * input：是你要计算的文件
     * output：是计算结果输出
     * @param args
     */
    public static void main(String[] args) {
        try {
            Job job =Tools.getJob();
            job.setJarByClass(CountMain.class);
            Tools.setMapper(job, CountMapper.class, Text.class, LongWritable.class);
            Tools.setReduce(job, CountReduce.class,Text.class,LongWritable.class);

            Tools.setInput(job,"/hdfs/mapred_input");
            Tools.setOutput(job,"/hdfs/mapred_output");
            job.waitForCompletion(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
