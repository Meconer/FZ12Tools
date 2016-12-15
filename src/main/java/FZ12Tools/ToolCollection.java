/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author matsandersson
 */
class ToolCollection {

    ArrayList<Tool> collection = new ArrayList<>();

    public void addTool(String id, int placeNo, int dNo) {

        if (!toolExist(id, dNo)) {
            Tool tool = new Tool(id, placeNo, dNo);
            collection.add(tool);
        }
    }

    public void addTool(Tool tool) {
        collection.add(tool);
    }

    public boolean toolExist(String id , int dNo) {
        Iterator<Tool> toolIterator = collection.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            if (tool.getdNo() == dNo && tool.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void toolPrint() {
        Iterator<Tool> toolIterator = collection.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            System.out.println(tool);
        }
    }

    public ArrayList<String> getToolList() {
        ArrayList<String> toolList = new ArrayList<>();
        Iterator<Tool> toolIterator = collection.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            toolList.add(tool.toString());
        }
        return toolList;
    }

    public ArrayList<Tool> getToolsByPlace(int placeNo) {
        ArrayList<Tool> toolListByPlace = new ArrayList<>();
        Iterator<Tool> toolIterator = collection.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            if (tool.placeNo == placeNo) {
                toolListByPlace.add(tool);
            }
        }
        return toolListByPlace;
    }

}
