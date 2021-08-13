package com.example.dsljsontesting;

import com.dslplatform.json.CompiledJson;

@CompiledJson(onUnknown = CompiledJson.Behavior.IGNORE)
public class Data {
    private String itemName;
    private String itemCode;
    private String unitCode;
    private double quantity;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Data(String itemName, String itemCode, String unitCode, double quantity) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.unitCode = unitCode;
        this.quantity = quantity;
    }
}
