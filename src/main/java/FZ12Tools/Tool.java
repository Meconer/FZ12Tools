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
    private  int toolType;     // Tool type
    private  String l1Ofs;     // Wear values for geometry.
    private  String l2Ofs;
    private  String l3Ofs;
    private  String rOfs;

    private static final String TNO_TOA_VAR = "\\$TC_TPC1\\[(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String TYPE_TOA_VAR = "\\$TC_DP1\\[(\\d+),(\\d+)\\]\\s*=\\s*([0-9]+)";
    private static final String TYPE_SL_VAR = "\\$TC_DP2\\[(\\d+),(\\d+)\\]\\s*=\\s*([0-9]+)";
    private static final String L1VAL_TOA_VAR = "\\$TC_DP3\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String L2VAL_TOA_VAR = "\\$TC_DP4\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String L3VAL_TOA_VAR = "\\$TC_DP5\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String RVAL_TOA_VAR = "\\$TC_DP6\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String L1OFS_TOA_VAR = "\\$TC_DP12\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String L2OFS_TOA_VAR = "\\$TC_DP13\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String L3OFS_TOA_VAR = "\\$TC_DP14\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    private static final String ROFS_TOA_VAR = "\\$TC_DP15\\[(\\d+),(\\d+)\\]\\s*=\\s*([+-]?([0-9]*[.])?[0-9]+)";
    
    private static final Pattern TNO_TOA_PATTERN = Pattern.compile(TNO_TOA_VAR);
    private static final Pattern TYPE_TOA_PATTERN = Pattern.compile(TYPE_TOA_VAR);
    private static final Pattern TYPE_SL_PATTERN = Pattern.compile(TYPE_SL_VAR);
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

    public int getToolType() {
        return toolType;
    }

    public void setToolType(int toolType) {
        this.toolType = toolType;
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

    public String getRValue() {
        return rValue;
    }

    public void setRValue(String rValue) {
        this.rValue = rValue;
    }

    public int getSlValue() {
        return slValue;
    }

    public void setSlValue(int slValue) {
        this.slValue = slValue;
    }

    public String getL1Ofs() {
        return l1Ofs;
    }

    public void setL1Ofs(String l1Ofs) {
        this.l1Ofs = l1Ofs;
    }

    public String getL2Ofs() {
        return l2Ofs;
    }

    public void setL2Ofs(String l2Ofs) {
        this.l2Ofs = l2Ofs;
    }

    public String getL3Ofs() {
        return l3Ofs;
    }

    public void setL3Ofs(String l3Ofs) {
        this.l3Ofs = l3Ofs;
    }

    public String getROfs() {
        return rOfs;
    }

    public void setROfs(String rOfs) {
        this.rOfs = rOfs;
    }
    
    @Override
    public String toString() {
        return "T=" + getTNo() + " D=" + getDNo();
    }
    
    static int getTNoFromToaLine(String toaLine) {
        Pattern tNoPattern = Pattern.compile(".*\\[(\\d+).*");
        Matcher m = tNoPattern.matcher(toaLine);
        if ( m.matches()) {
            String s = m.group(1);
            int tNo = Integer.parseInt(s);
            return tNo;
        }
        return -1;
    }
    
    static int getDNoFromToaLine(String toaLine) {
        Pattern dNoPattern = Pattern.compile(".*,(\\d+)\\].*");
        Matcher m = dNoPattern.matcher(toaLine);
        if ( m.matches()) {
            String s = m.group(1);
            int dNo = Integer.parseInt(s);
            return dNo;
        }
        return -1;
    }

    void addParameterFromToaLine(String toaLine) {
        toaLine = toaLine.replaceAll(" ", "");
        Matcher m = L1VAL_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l1Value = m.group(3);
            return;
        }
        
        m = TYPE_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            String s = m.group(3);
            toolType = Integer.parseInt(m.group(3));
            return;
        }
        
        m = TYPE_SL_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            String s = m.group(3);
            slValue = Integer.parseInt(m.group(3));
            return;
        }
        
        m = L2VAL_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l2Value = m.group(3);
            return;
        }
        
        m = L3VAL_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l3Value = m.group(3);
            return;
        }
        
        m = RVAL_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            rValue = m.group(3);
            return;
        }
        
        m = L1OFS_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l1Ofs = m.group(3);
            return;
        }
        
        m = L2OFS_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l2Ofs = m.group(3);
            return;
        }
        
        m = L3OFS_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            l3Ofs = m.group(3);
            return;
        }
        
        m = ROFS_TOA_PATTERN.matcher(toaLine);
        if ( m.matches()) {
            rOfs = m.group(3);
        }
        
    }
    
    
}
