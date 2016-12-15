/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import javax.swing.JFrame;

/**
 *
 * @author matsandersson
 */
class Tool {
    static final public int MAX_PLACE_NUMBER = 46;
    
    protected int placeNo;      // Place number. 1 to MAX_PLACE_NUMBER

    protected int dNo;          // Offset number.
    protected String qValue;    // Geometry values for the tool.
    protected String lValue;
    protected String hValue;
    protected String rValue;
    protected int slValue;      // Cut location
    protected int toolType;
    protected String q_ofs;     // Wear values for geometry.
    protected String l_ofs;
    protected String h_ofs;
    protected String r_ofs;

    
    public Tool() {
        
    }
    
    public Tool(String id, int placeNo, int dNo, String qValue, String lValue, String hValue, String rValue, int slValue) {
        this.id = id;
        this.placeNo = placeNo;
        this.dNo = dNo;
        this.qValue = qValue;
        this.lValue = lValue;
        this.hValue = hValue;
        this.rValue = rValue;
        this.slValue = slValue;
    }

    public Tool(String id, int placeNo, int dNo ) {
        this.id = id;
        this.placeNo = placeNo;
        this.dNo = dNo;
    }
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(int placeNo) {
        this.placeNo = placeNo;
    }

    public int getType() {
        return toolType;
    }

    public void setType(int type) {
        this.toolType = type;
    }

    public int getdNo() {
        return dNo;
    }

    public void setdNo(int dNo) {
        this.dNo = dNo;
    }

    public String getqValue() {
        return qValue;
    }

    public void setqValue(String qValue) {
        this.qValue = qValue;
    }

    public String getlValue() {
        return lValue;
    }

    public void setlValue(String lValue) {
        this.lValue = lValue;
    }

    public String gethValue() {
        return hValue;
    }

    public void sethValue(String hValue) {
        this.hValue = hValue;
    }

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    public int getSlValue() {
        return slValue;
    }

    public void setSlValue(int slValue) {
        this.slValue = slValue;
    }

    public String getQ_ofs() {
        return q_ofs;
    }

    public void setQ_ofs(String q_ofs) {
        this.q_ofs = q_ofs;
    }

    public String getL_ofs() {
        return l_ofs;
    }

    public void setL_ofs(String l_ofs) {
        this.l_ofs = l_ofs;
    }

    public String getH_ofs() {
        return h_ofs;
    }

    public void setH_ofs(String h_ofs) {
        this.h_ofs = h_ofs;
    }

    public String getR_ofs() {
        return r_ofs;
    }

    public void setR_ofs(String r_ofs) {
        this.r_ofs = r_ofs;
    }
    
    @Override
    public String toString() {
        return id + " \n"
                + "T=" + placeNo
                + " D=" + dNo;
    }
    
    
}
