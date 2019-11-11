package com.hadoop.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class CountReduce extends Reducer<Text,LongWritable,Text,LongWritable> {

    private LongWritable result=new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> iter, Context context) throws IOException, InterruptedException {
        long total=0;

        for (LongWritable longWritable : iter) {
            total+= longWritable.get();
        }
        result.set(total);
        context.write(key,result);
    }
}
