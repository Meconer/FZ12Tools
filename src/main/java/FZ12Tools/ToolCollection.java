/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author matsandersson
 */
class ToolCollection {

    ObservableList<Tool> collection;

    ToolCollection() {
        this.collection = FXCollections.observableArrayList();
    }

    public void addTool(int tNo, int dNo) {

        if (!toolExist(tNo, dNo)) {
            Tool tool = new Tool(tNo, dNo);
            collection.add(tool);
        }
    }

    public void addTool(Tool tool) {
        collection.add(tool);
    }

    public boolean toolExist(int tNo, int dNo) {
        Iterator<Tool> toolIterator = collection.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            if (tool.getDNo() == dNo && tool.getTNo() == tNo) {
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
            if (tool.getTNo() == placeNo) {
                toolListByPlace.add(tool);
            }
        }
        return toolListByPlace;
    }

    Tool getTool(int tNo, int dNo) {
        Iterator<Tool> toolIterator = collection.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            if (tool.getDNo() == dNo && tool.getTNo() == tNo) {
                return tool;
            }
        }
        return null;

    }

    void removeTool(Tool tool) {
        collection.remove(tool);
    }

}
