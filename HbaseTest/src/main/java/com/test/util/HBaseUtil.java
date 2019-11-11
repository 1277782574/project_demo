package com.test.util;

import com.alibaba.fastjson.JSONArray;
import com.test.pojo.HBaseBean;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * hbase工具类
 */
@Component
public class HBaseUtil {

    @Autowired
    private  HbaseTemplate hbaseTemplate;

    @Autowired
    private  HBaseAdmin hBaseAdmin;


    /**
     * 创建一个表,一个列(族)
     * @throws IOException
     */
    public   void createTable(String tableName,String family) throws IOException {
        HTableDescriptor desc=new HTableDescriptor(TableName.valueOf(tableName));
        desc.addFamily(new HColumnDescriptor(family.getBytes()));
        if(hBaseAdmin.tableExists(tableName)){

            System.err.println("===========================table====exists====================================");
            throw new RuntimeException("该表已存在!!");
        }
        hBaseAdmin.createTable(desc);
        hBaseAdmin.close();
    }


    /**
     * 给 指定表 添加列,族(family)
     * 可以添加多个
     * @throws IOException
     */
    public   void addFamily(String tablename,String[] familyName) throws IOException {
        TableName tableName = TableName.valueOf(tablename);
        hBaseAdmin.disableTable(tableName);
        HTableDescriptor desc = hBaseAdmin.getTableDescriptor(tableName);
        for (int i = 0; i < familyName.length; i++) {
            HColumnDescriptor column=new HColumnDescriptor(familyName[i]);
            desc.addFamily(column);
        }
        hBaseAdmin.modifyTable(tablename,desc);
        hBaseAdmin.enableTable(tableName);
        hBaseAdmin.close();
    }

    /**
     * 给 指定表,指定行,指定列,指定qualifier 添加指定数据
     */
    public   void insert(String tableName,String rowName,String familyName,String qualifter,String value)  {
        hbaseTemplate.put(tableName,rowName,familyName,qualifter,value.getBytes());
    }


    /**
     * 查看 表中所有数据,以json形式输出
     * @param tableName
     * @return
     */
    public String findAll(String tableName){
       Object obj= hbaseTemplate.find(tableName,new Scan(),(Result result, int i)->{
           Cell[] cells = result.rawCells();
           List<HBaseBean> list=new ArrayList<>();
            for (Cell cell : cells) {
                String columnFamily= new String(CellUtil.cloneFamily(cell));
                String rowName = new String(CellUtil.cloneRow(cell));
                String key = new String(CellUtil.cloneQualifier(cell ));
                String value = new String(CellUtil.cloneValue(cell));
                list.add(new HBaseBean(columnFamily,rowName,key,value));
            }
            return list;
        });
        String json = JSONArray.toJSONString(obj);
        return json;
    }
}
