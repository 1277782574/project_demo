package com.test.pojo;

public class HBaseBean {
    /**
     * 列名
     */
    private String familyName;

    /**
     * 行名
     */
    private String rowName;

    /**
     * Qualifier
     */
    private String key;

    /**
     * 值
     */
    private String value;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HBaseBean(String familyName, String rowName, String key, String value) {
        this.familyName = familyName;
        this.rowName = rowName;
        this.key = key;
        this.value = value;
    }

    public HBaseBean() {
    }
}
