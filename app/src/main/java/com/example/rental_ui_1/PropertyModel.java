package com.example.rental_ui_1;

public class PropertyModel {
    private int refId;
    private String propertyType;
    private String bedroom;
    private String price;
    private String location;
    private String furnitureType;
    private String remark;
    private String reporterName;
    private String assignDate;

    public PropertyModel(int refId, String propertyType, String bedroom, String price, String location, String furnitureType, String remark, String reporterName, String assignDate) {
        this.refId = refId;
        this.propertyType = propertyType;
        this.bedroom = bedroom;
        this.price = price;
        this.location = location;
        this.furnitureType = furnitureType;
        this.remark = remark;
        this.reporterName = reporterName;
        this.assignDate = assignDate;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(int reporterId) {
        this.reporterName = reporterName;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }
}
