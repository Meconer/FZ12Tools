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
    static final public int MAX_TOOL_NUMBER = 48;

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
        this.tNo = -1;
        this.dNo = -1;
        this.toolType = 120;
        this.slValue = 3;
        this.l1Value = "0";
        this.l2Value = "0";
        this.l3Value = "0";
        this.rValue = "0";
        this.l1Ofs = "0";
        this.l2Ofs = "0";
        this.l3Ofs = "0";
        this.rOfs = "0";
    }
    
    public Tool( int tNo, int dNo, String l1Value, String lValue, String hValue, String rValue, int slValue) {
        this();
        this.tNo = tNo;
        this.dNo = dNo;
        this.l1Value = l1Value;
        this.l2Value = lValue;
        this.l3Value = hValue;
        this.rValue = rValue;
        this.slValue = slValue;
    }

    public Tool( int tNo, int dNo ) {
        this();
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
    
    private static int valueToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch ( NumberFormatException e ) {
            return -1;
        }
    }

        static Tool getToolFromZollerLine(String zollerLine) {
        String[] paramAssignments = zollerLine.split(" ");
        Tool tool = new Tool();
        for (String paramAssignment : paramAssignments ) {
            String[] assignment = paramAssignment.split("=");
            if ( assignment.length == 2 ) {
                String key = assignment[0];
                String value = assignment[1];

                switch ( key ) {
                    case "T" : 
                        tool.setTNo( valueToInt( value ) );
                        break;

                    case "D" :
                        tool.setdNo( valueToInt( value ) ); 
                        break;

                    case "TYP" :
                        tool.setToolType( valueToInt( value ) ); 
                        break;

                    case "SL" :
                        tool.setSlValue( valueToInt( value ) );
                        break;
                        
                    case "L1" :
                        tool.setL1Value(value);
                        break;
                        
                    case "L2" :
                        tool.setL2Value(value);
                        break;
                        
                    case "R" :
                        tool.setRValue(value);
                        break;
                        
                }
            }
        }
        return tool;
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

    void copyFromZollerValues(Tool zollerTool) {
        this.tNo = zollerTool.tNo;
        this.dNo = zollerTool.dNo;
        this.toolType = zollerTool.toolType;
        this.slValue = zollerTool.slValue;
        this.l1Value = zollerTool.l1Value;
        this.l2Value = zollerTool.l2Value;
        this.l3Value = zollerTool.l3Value;
        this.rValue = zollerTool.rValue;
    }

    public String getToaLines() {
        StringBuilder sb = new StringBuilder();
        String ls = System.lineSeparator();
        String tolerance = "5.0";
        
        sb.append("$TC_TPC1[").append(tNo).append("]=").append(tNo).append(ls); // Id 1
        sb.append("$TC_TPC2[").append(tNo).append("]=").append(tNo).append(ls); // Id 2
        sb.append("$TC_TPC3[").append(tNo).append("]=0").append(ls); // Max matning
        sb.append("$TC_TPC4[").append(tNo).append("]=0").append(ls); // Max varvtal
        sb.append("$TC_TPC5[").append(tNo).append("]=0").append(ls); // Attr A
        sb.append("$TC_TPC6[").append(tNo).append("]=0").append(ls); // Attr B
        sb.append("$TC_TPC7[").append(tNo).append("]=0").append(ls); // Attr C
        sb.append("$TC_TPC8[").append(tNo).append("]=0").append(ls); 
        sb.append("$TC_TPC9[").append(tNo).append("]=3").append(ls); // Attr D. Ingen övervakning eller räkning
        sb.append("$TC_TPC10[").append(tNo).append("]=0").append(ls);
        
        sb.append("$TC_DP1[").append(tNo).append(",").append(dNo)
                .append("]=").append(toolType).append(ls); // Tool type
        sb.append("$TC_DP2[").append(tNo).append(",").append(dNo)
                .append("]=").append(slValue).append(ls); // cut location
        sb.append("$TC_DP3[").append(tNo).append(",").append(dNo)
                .append("]=").append(l1Value).append(ls); // Length 1
        sb.append("$TC_DP4[").append(tNo).append(",").append(dNo)
                .append("]=").append(l2Value).append(ls); // Length 2
        sb.append("$TC_DP5[").append(tNo).append(",").append(dNo)
                .append("]=").append(l3Value).append(ls); // Length 3
        sb.append("$TC_DP6[").append(tNo).append(",").append(dNo)
                .append("]=").append(rValue).append(ls); // Radius
        
        sb.append("$TC_DP8[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP9[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP10[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP11[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        
        sb.append("$TC_DP12[").append(tNo).append(",").append(dNo)
                .append("]=").append(l1Ofs).append(ls); // Wear L1
        sb.append("$TC_DP13[").append(tNo).append(",").append(dNo)
                .append("]=").append(l2Ofs).append(ls); // Wear L1
        sb.append("$TC_DP14[").append(tNo).append(",").append(dNo)
                .append("]=").append(l3Ofs).append(ls); // Wear L1
        sb.append("$TC_DP15[").append(tNo).append(",").append(dNo)
                .append("]=").append(rOfs).append(ls); // Wear L1
        
        sb.append("$TC_DP16[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP17[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP18[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP19[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP20[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP21[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP22[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP23[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP24[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DP25[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        
        sb.append("$TC_DPC1[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DPC2[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DPC3[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        
        sb.append("$TC_DPC4[").append(tNo).append(",").append(dNo)
                .append("]=").append(l1Value).append(ls); // Base length
        sb.append("$TC_DPC5[").append(tNo).append(",").append(dNo)
                .append("]=").append(tolerance).append(ls); // Length tolerance
        sb.append("$TC_DPC6[").append(tNo).append(",").append(dNo)
                .append("]=").append(rValue).append(ls); // Base radius
        sb.append("$TC_DPC7[").append(tNo).append(",").append(dNo)
                .append("]=").append(tolerance).append(ls); // Length tolerance
        
        sb.append("$TC_DPC8[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DPC9[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        sb.append("$TC_DPC10[").append(tNo).append(",").append(dNo)
                .append("]=").append("0").append(ls); // Not used
        
        return sb.toString();

    }

    void copyValuesFromTool(Tool toaTool) {
        tNo = toaTool.tNo;
        dNo = toaTool.dNo;
        toolType = toaTool.toolType;
        slValue = toaTool.slValue;
        l1Value = toaTool.l1Value;
        l2Value = toaTool.l2Value;
        l3Value = toaTool.l3Value;
        rValue = toaTool.rValue;
        l1Ofs = toaTool.l1Ofs;
        l2Ofs = toaTool.l2Ofs;
        l3Ofs = toaTool.l3Ofs;
        rOfs = toaTool.rOfs;
        
    }
    
    
}
