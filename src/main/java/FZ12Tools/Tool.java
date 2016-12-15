/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

/**
 *
 * @author matsandersson
 */
class Tool {
    static final public int MAX_PLACE_NUMBER = 46;
    
    protected int placeNo;      // Place number. 1 to MAX_PLACE_NUMBER

    protected String id;
    protected int dNo;          // Offset number.
    protected String l1Value;    // Geometry values for the tool.
    protected String l2Value;
    protected String l3Value;
    protected String rValue;
    protected int slValue;      // Cut location
    protected int toolType;
    protected String q_ofs;     // Wear values for geometry.
    protected String l_ofs;
    protected String h_ofs;
    protected String r_ofs;

    
    public Tool() {
        
    }
    
    public Tool(String id, int placeNo, int dNo, String l1Value, String lValue, String hValue, String rValue, int slValue) {
        this.id = id;
        this.placeNo = placeNo;
        this.dNo = dNo;
        this.l1Value = l1Value;
        this.l2Value = lValue;
        this.l3Value = hValue;
        this.rValue = rValue;
        this.slValue = slValue;
    }

    public Tool(String id, int placeNo, int dNo ) {
        this.id = id;
        this.placeNo = placeNo;
        this.dNo = dNo;
    }

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
        return l1Value;
    }

    public void setqValue(String qValue) {
        this.l1Value = qValue;
    }

    public String getlValue() {
        return l2Value;
    }

    public void setlValue(String lValue) {
        this.l2Value = lValue;
    }

    public String gethValue() {
        return l3Value;
    }

    public void sethValue(String hValue) {
        this.l3Value = hValue;
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
