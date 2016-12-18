/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author matsandersson
 */
public class Tool {
    static final public int MAX_TOOL_NUMBER = 46;

    private  int tNo;          // Tool number. 1 to MAX_TOOL_NUMBER
    private  int dNo;          // Offset number.
    private  String l1Value;   // Geometry values for the tool.
    private  String l2Value;
    private  String l3Value;
    private  String rValue;
    private  int slValue;      // Cut location
    private  int toolType;
    private  String l1_ofs;     // Wear values for geometry.
    private  String l2_ofs;
    private  String l3_ofs;
    private  String r_ofs;

    private static final String TNO_TOA_VAR = "TC_TPC1\\[(\\d+)";
    private static final String L1VAL_TOA_VAR = "TC_DP3\\[(\\d+),(\\d+)\\]";
    private static final String L2VAL_TOA_VAR = "TC_DP4\\[(\\d+),(\\d+)";
    private static final String L3VAL_TOA_VAR = "TC_DP5\\[(\\d+),(\\d+)";
    private static final String RVAL_TOA_VAR = "TC_DP6\\[(\\d+),(\\d+)";
    private static final String L1OFS_TOA_VAR = "TC_DP12\\[(\\d+),(\\d+)";
    private static final String L2OFS_TOA_VAR = "TC_DP13\\[(\\d+),(\\d+)";
    private static final String L3OFS_TOA_VAR = "TC_DP14\\[(\\d+),(\\d+)";
    private static final String ROFS_TOA_VAR = "TC_DP15\\[(\\d+),(\\d+)";
    
    private static final Pattern TNO_TOA_PATTERN = Pattern.compile(TNO_TOA_VAR);
    private static final Pattern L1VAL_TOA_PATTERN = Pattern.compile(L1VAL_TOA_VAR);
    private static final Pattern L2VAL_TOA_PATTERN = Pattern.compile(L2VAL_TOA_VAR);
    private static final Pattern L3VAL_TOA_PATTERN = Pattern.compile(L3VAL_TOA_VAR);
    private static final Pattern RVAL_TOA_PATTERN = Pattern.compile(RVAL_TOA_VAR);
    
    private static final Pattern L1OFS_TOA_PATTERN = Pattern.compile(L1OFS_TOA_VAR);
    private static final Pattern L2OFS_TOA_PATTERN = Pattern.compile(L2OFS_TOA_VAR);
    private static final Pattern L3OFS_TOA_PATTERN = Pattern.compile(L3OFS_TOA_VAR);
    private static final Pattern ROFS_TOA_PATTERN = Pattern.compile(ROFS_TOA_VAR);
    
    public Tool() {
        
    }
    
    public Tool( int tNo, int dNo, String l1Value, String lValue, String hValue, String rValue, int slValue) {
        this.tNo = tNo;
        this.dNo = dNo;
        this.l1Value = l1Value;
        this.l2Value = lValue;
        this.l3Value = hValue;
        this.rValue = rValue;
        this.slValue = slValue;
    }

    public Tool( int tNo, int dNo ) {
        this.tNo = tNo;
        this.dNo = dNo;
    }

    public int getTNo() {
        return tNo;
    }

    public void setTNo(int tNo) {
        this.tNo = tNo;
    }

    public int getType() {
        return toolType;
    }

    public void setType(int type) {
        this.toolType = type;
    }

    public int getDNo() {
        return dNo;
    }

    public void setdNo(int dNo) {
        this.dNo = dNo;
    }

    public String getL1Value() {
        return l1Value;
    }

    public void setL1Value(String l1Value) {
        this.l1Value = l1Value;
    }

    public String getL2Value() {
        return l2Value;
    }

    public void setL2Value(String l2Value) {
        this.l2Value = l2Value;
    }

    public String getL3Value() {
        return l3Value;
    }

    public void setL3Value(String l3Value) {
        this.l3Value = l3Value;
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

    public String getL1_ofs() {
        return l1_ofs;
    }

    public void setL1_ofs(String l1_ofs) {
        this.l1_ofs = l1_ofs;
    }

    public String getL2_ofs() {
        return l2_ofs;
    }

    public void setL_ofs(String l2_ofs) {
        this.l2_ofs = l2_ofs;
    }

    public String getL3_ofs() {
        return l3_ofs;
    }

    public void setL3_ofs(String l3_ofs) {
        this.l3_ofs = l3_ofs;
    }

    public String getR_ofs() {
        return r_ofs;
    }

    public void setR_ofs(String r_ofs) {
        this.r_ofs = r_ofs;
    }
    
    @Override
    public String toString() {
        return "T=" + getTNo() + " D=" + getDNo();
    }
    
    static int getTNoFromToaLine(String toaLine) {
        Pattern tNoPattern = Pattern.compile(".*\\[(\\d+)");
        Matcher m = tNoPattern.matcher(toaLine);
        if ( m.matches()) {
            String s = m.group(1);
            int tNo = Integer.parseInt(s);
            return tNo;
        }
        return -1;
    }
    
    static int getDNoFromToaLine(String toaLine) {
        Pattern dNoPattern = Pattern.compile(".*,(\\d+)\\]");
        Matcher m = dNoPattern.matcher(toaLine);
        if ( m.matches()) {
            String s = m.group(1);
            int dNo = Integer.parseInt(s);
            return dNo;
        }
        return -1;
    }

    void addParameterFromToaLine(String toaLine) {
        Matcher m = L1VAL_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l1Value = m.group(1);
        }
    }
    
    
}
