package com.hadoop.utils;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 工具类
 */
public class Tools {

    public static  final Configuration configuration=new Configuration();

    static {
        configuration.set("fs.defaultFS","hdfs://192.168.100.11:9000");
        System.setProperty("HADOOP_USER_NAME","root");
    }


    public static Job getJob(){
        Job job=null;
        try {
             job = Job.getInstance(configuration);
        }catch (Exception e){
            e.printStackTrace();
        }
        return job;
    }

    public static void setMapper(Job job,Class mapperClass,Class keyClass,Class valueClass){
        job.setMapperClass(mapperClass);
        job.setOutputKeyClass(keyClass);
        job.setOutputValueClass(valueClass);
    }

    public static void setReduce(Job job,Class reduceClass,Class keyClass,Class valueClass){
        job.setReducerClass(reduceClass);
        job.setOutputKeyClass(keyClass);
        job.setOutputValueClass(valueClass);
    }

    public static void setInput(Job job,String path){
        try {
           FileInputFormat.addInputPath(job,new Path(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setOutput(Job job,String path){
        try {
            FileOutputFormat.setOutputPath(job,new Path(path));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
