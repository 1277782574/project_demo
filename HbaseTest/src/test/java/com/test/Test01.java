package com.test;


import com.alibaba.fastjson.JSONArray;
import com.test.pojo.HBaseBean;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private HBaseAdmin hBaseAdmin;

    /**
     * 创建表
     * @throws IOException
     */
    @Test
    public void createTable() throws IOException {
        HTableDescriptor desc=new HTableDescriptor(TableName.valueOf("Student"));
        desc.addFamily(new HColumnDescriptor("info".getBytes()));
        if(hBaseAdmin.tableExists("Student")){
            System.err.println("===========================table====exists====================================");
        }
        hBaseAdmin.createTable(desc);
    }


    /**
     * 添加数据
     */
    @Test
    public void insert()  {
        hbaseTemplate.put("Student","row1","abc","subject","数学".getBytes());
        hbaseTemplate.put("Student","row2","abc","score","99".getBytes());
        hbaseTemplate.put("Student","row3","abc","name","和凯凯".getBytes());
    }



    /**
     * 查询所有数据
     */
    @Test
    public void findAll(){
        Object obj= hbaseTemplate.find("Student",new Scan(),(Result result, int i)->{
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
        System.out.println(json);
    }




    /**
     * 添加列,族(family)
     * @throws IOException
     */
    @Test
    public void addFamily() throws IOException {
        TableName tableName = TableName.valueOf("Student");
        hBaseAdmin.disableTable(tableName);
        HTableDescriptor desc = hBaseAdmin.getTableDescriptor(tableName);
        HColumnDescriptor column=new HColumnDescriptor("abc");
        desc.addFamily(column);
        hBaseAdmin.modifyTable("Student",desc);
        hBaseAdmin.enableTable(tableName);
        hBaseAdmin.close();
    }

}
