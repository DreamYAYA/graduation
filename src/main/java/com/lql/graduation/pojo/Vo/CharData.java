package com.lql.graduation.pojo.Vo;

import java.util.List;

public class CharData {

    private List<String> category;
    private List<Integer> datas;


    public CharData(List<String> category, List<Integer> datas) {
        this.category = category;
        this.datas = datas;
    }

    public CharData() {
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<Integer> getDatas() {
        return datas;
    }

    public void setDatas(List<Integer> datas) {
        this.datas = datas;
    }
}
