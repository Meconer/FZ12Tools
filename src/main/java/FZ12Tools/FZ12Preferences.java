/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.util.prefs.Preferences;

/**
 *
 * @author Mats Andersson <mats.andersson@mecona.se>
 */
public class FZ12Preferences {
    private static FZ12Preferences instance = null;
    private final Preferences prefs = Preferences.userNodeForPackage(getClass());
    
    private final String DEFAULT_TOOL_DIRECTORY = "J:\\verktyg";
    private final String TOOL_DIRECTORY_KEY = "ToolDirectory";
    private String toolDirectory;
    
    private final String DEFAULT_DIRECTORY = "J:\\NCDOK";
    private final String DEFAULT_DIRECTORY_KEY = "DefaultDirectory";
    private String defaultDirectory;
    
    private final String DEFAULT_TEMPLATE_PATH = "J:\\NCPROG\\Chiron FZ12\\VLIST.ODS";
    private final String DEFAULT_TEMPLATE_PATH_KEY = "DefaultTemplatePath";
    private String defaultTemplatePath;
    
    protected FZ12Preferences() {
        initPrefs();
    }
    
    public static FZ12Preferences getInstance() {
        if (instance == null ) {
            instance = new FZ12Preferences();
        }
        return instance;
    }

    private void initPrefs() {
        toolDirectory = prefs.get(TOOL_DIRECTORY_KEY, DEFAULT_TOOL_DIRECTORY);
        defaultDirectory = prefs.get(DEFAULT_DIRECTORY_KEY, DEFAULT_DIRECTORY);
        defaultTemplatePath = prefs.get(DEFAULT_TEMPLATE_PATH_KEY, DEFAULT_TEMPLATE_PATH);
    }

    public String getToolDirectory() {
        return toolDirectory;
    }

    public void setToolDirectory(String toolDirectory) {
        this.toolDirectory = toolDirectory;
        prefs.put(TOOL_DIRECTORY_KEY, toolDirectory);
    }

    public String getDefaultDirectory() {
        return defaultDirectory;
    }

    public void setDefaultDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
        prefs.put(DEFAULT_DIRECTORY_KEY, defaultDirectory);
    }

    public String getDefaultTemplatePath() {
        return defaultTemplatePath;
    }

    public void setDefaultTemplatePath(String defaultTemplatePath) {
        this.defaultTemplatePath = defaultTemplatePath;
        prefs.put(DEFAULT_TEMPLATE_PATH_KEY, defaultTemplatePath);
    }

     
}
