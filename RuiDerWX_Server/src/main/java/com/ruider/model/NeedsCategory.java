package com.ruider.model;

/**
 * Created by mahede on 2018/12/2.
 */
public class NeedsCategory {

    private int id;

    private String categoryName;

    private String description;

    private String extendField1;

    private String extendField2;

    private String extendField3;

    private String extendField4;

    private String extendField5;

    private String extendField6;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtendField1() {
        return extendField1;
    }

    public void setExtendField1(String extendField1) {
        this.extendField1 = extendField1;
    }

    public String getExtendField2() {
        return extendField2;
    }

    public void setExtendField2(String extendField2) {
        this.extendField2 = extendField2;
    }

    public String getExtendField3() {
        return extendField3;
    }

    public void setExtendField3(String extendField3) {
        this.extendField3 = extendField3;
    }

    public String getExtendField4() {
        return extendField4;
    }

    public void setExtendField4(String extendField4) {
        this.extendField4 = extendField4;
    }

    public String getExtendField5() {
        return extendField5;
    }

    public void setExtendField5(String extendField5) {
        this.extendField5 = extendField5;
    }

    public String getExtendField6() {
        return extendField6;
    }

    public void setExtendField6(String extendField6) {
        this.extendField6 = extendField6;
    }

    @Override
    public String toString() {
        return "NeedsCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", extendField1='" + extendField1 + '\'' +
                ", extendField2='" + extendField2 + '\'' +
                ", extendField3='" + extendField3 + '\'' +
                ", extendField4='" + extendField4 + '\'' +
                ", extendField5='" + extendField5 + '\'' +
                ", extendField6='" + extendField6 + '\'' +
                '}';
    }
}
