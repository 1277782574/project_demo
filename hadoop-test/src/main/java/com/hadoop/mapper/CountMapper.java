package com.hadoop.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;


public class CountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private LongWritable one=new LongWritable(1);
    private Text word=new Text();

    /**
     * 每次调用map方法都会传入split中的一行
     * @param key：该行数据在文件位置的下标
     * @param value：该行数据
     * @param context：输出
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        while (stringTokenizer.hasMoreTokens()){
            //正则去掉所有的符号
            word.set( stringTokenizer.nextToken().replaceAll("[\\pP\\p{Punct}]",""));
            context.write(word,one);
        }
    }
}
