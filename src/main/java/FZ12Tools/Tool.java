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
class Tool {
    static final public int MAX_TOOL_NUMBER = 46;
    
    private final SimpleIntegerProperty tNo = new SimpleIntegerProperty();          // Tool number. 1 to MAX_TOOL_NUMBER
    private final SimpleIntegerProperty dNo = new SimpleIntegerProperty();          // Offset number.
    private final SimpleStringProperty l1Value = new SimpleStringProperty();   // Geometry values for the tool.
    private final SimpleStringProperty l2Value = new SimpleStringProperty();
    private final SimpleStringProperty l3Value = new SimpleStringProperty();
    private final SimpleStringProperty rValue = new SimpleStringProperty();
    private final SimpleIntegerProperty slValue = new SimpleIntegerProperty();      // Cut location
    private final SimpleIntegerProperty toolType = new SimpleIntegerProperty();
    private final SimpleStringProperty q_ofs = new SimpleStringProperty();     // Wear values for geometry.
    private final SimpleStringProperty l_ofs = new SimpleStringProperty();
    private final SimpleStringProperty h_ofs = new SimpleStringProperty();
    private final SimpleStringProperty r_ofs = new SimpleStringProperty();

    
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

    public String getQ_ofs() {
        return q_ofs.get();
    }

    public void setQ_ofs(String q_ofs) {
        this.q_ofs.set(q_ofs);
    }

    public String getL_ofs() {
        return l_ofs.get();
    }

    public void setL_ofs(String l_ofs) {
        this.l_ofs.set(l_ofs);
    }

    public String getH_ofs() {
        return h_ofs.get();
    }

    public void setH_ofs(String h_ofs) {
        this.h_ofs.set(h_ofs);
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
