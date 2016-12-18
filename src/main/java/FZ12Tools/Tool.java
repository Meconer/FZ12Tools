/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author matsandersson
 */
public class Tool {
    static final public int MAX_TOOL_NUMBER = 46;
    
    private final SimpleIntegerProperty tNo = new SimpleIntegerProperty();          // Tool number. 1 to MAX_TOOL_NUMBER
    private final SimpleIntegerProperty dNo = new SimpleIntegerProperty();          // Offset number.
    private final SimpleStringProperty l1Value = new SimpleStringProperty();   // Geometry values for the tool.
    private final SimpleStringProperty l2Value = new SimpleStringProperty();
    private final SimpleStringProperty l3Value = new SimpleStringProperty();
    private final SimpleStringProperty rValue = new SimpleStringProperty();
    private final SimpleIntegerProperty slValue = new SimpleIntegerProperty();      // Cut location
    private final SimpleIntegerProperty toolType = new SimpleIntegerProperty();
    private final SimpleStringProperty l1_ofs = new SimpleStringProperty();     // Wear values for geometry.
    private final SimpleStringProperty l2_ofs = new SimpleStringProperty();
    private final SimpleStringProperty l3_ofs = new SimpleStringProperty();
    private final SimpleStringProperty r_ofs = new SimpleStringProperty();

    private final String TNO_TOA_VAR = "TC_TPC1[";
    private final String L1VAL_TOA_VAR = "TC_DP3[";
    private final String L2VAL_TOA_VAR = "TC_DP4[";
    private final String L3VAL_TOA_VAR = "TC_DP5[";
    private final String RVAL_TOA_VAR = "TC_DP6[";
    private final String L1OFS_TOA_VAR = "TC_DP12[";
    private final String L2OFS_TOA_VAR = "TC_DP13[";
    private final String L3OFS_TOA_VAR = "TC_DP14[";
    private final String ROFS_TOA_VAR = "TC_DP15[";
    
    public Tool() {
        
    }
    
    public Tool( int tNo, int dNo, String l1Value, String lValue, String hValue, String rValue, int slValue) {
        this.tNo.set(tNo);
        this.dNo.set(dNo);
        this.l1Value.set(l1Value);
        this.l2Value.set(lValue);
        this.l3Value.set(hValue);
        this.rValue.set(rValue);
        this.slValue.set(slValue);
    }

    public Tool( int tNo, int dNo ) {
        this.tNo.set(tNo);
        this.dNo.set(dNo);
    }

    public int getTNo() {
        return tNo.get();
    }

    public void setTNo(int tNo) {
        this.tNo.set(tNo);
    }

    public int getType() {
        return toolType.get();
    }

    public void setType(int type) {
        this.toolType.set(type);
    }

    public int getDNo() {
        return dNo.get();
    }

    public void setdNo(int dNo) {
        this.dNo.set(dNo);
    }

    public String getqValue() {
        return l1Value.get();
    }

    public void setqValue(String qValue) {
        this.l1Value.set(qValue);
    }

    public String getlValue() {
        return l2Value.get();
    }

    public void setlValue(String lValue) {
        this.l2Value.set(lValue);
    }

    public String gethValue() {
        return l3Value.get();
    }

    public void sethValue(String hValue) {
        this.l3Value.set(hValue);
    }

    public String getrValue() {
        return rValue.get();
    }

    public void setrValue(String rValue) {
        this.rValue.set(rValue);
    }

    public int getSlValue() {
        return slValue.get();
    }

    public void setSlValue(int slValue) {
        this.slValue.set(slValue);
    }

    public String getL1_ofs() {
        return l1_ofs.get();
    }

    public void setL1_ofs(String l1_ofs) {
        this.l1_ofs.set(l1_ofs);
    }

    public String getL2_ofs() {
        return l2_ofs.get();
    }

    public void setL_ofs(String l2_ofs) {
        this.l2_ofs.set(l2_ofs);
    }

    public String getL3_ofs() {
        return l3_ofs.get();
    }

    public void setL3_ofs(String l3_ofs) {
        this.l3_ofs.set(l3_ofs);
    }

    public String getR_ofs() {
        return r_ofs.get();
    }

    public void setR_ofs(String r_ofs) {
        this.r_ofs.set(r_ofs);
    }
    
    @Override
    public String toString() {
        return "T=" + getTNo() + " D=" + getDNo();
    }
    
    
}
