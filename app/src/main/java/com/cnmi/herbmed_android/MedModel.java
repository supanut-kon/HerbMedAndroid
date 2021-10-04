package com.cnmi.herbmed_android;

public class MedModel {
    private int id;
    private int rowid;
    private String drugname;
    private int is_active;

    public MedModel(int id, int rowid, String drugname, int is_active) {
        this.id = id;
        this.rowid = rowid;
        this.drugname = drugname;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public int getRowid() {
        return rowid;
    }

    public String getDrugname() {
        return drugname;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
