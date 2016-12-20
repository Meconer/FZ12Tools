/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author matsandersson
 */
class Utilities {

    private static final boolean DEBUG = true;
    
    static File getHomeDirectory() {
        if ( DEBUG ) return new File( System.getProperty("user.home") + "\\Documents");
        String meconaHomeDir = "J:\\NCDOK\\";
        if ( Files.exists( Paths.get( meconaHomeDir ))) {
            return new File( meconaHomeDir );
        } else {
            return new File( System.getProperty("user.home") + "\\Documents");
        }
    }

    static String removeComment(String s) {
        return s.replaceAll(";.*", "");
    }

    static File getZollerFileDirectory() {
        if ( DEBUG ) return new File( System.getProperty("user.home") + "\\Documents");
        String meconaHomeDir = "J:\\verktyg\\";
        if ( Files.exists( Paths.get( meconaHomeDir ))) {
            return new File( meconaHomeDir );
        } else {
            return new File( System.getProperty("user.home") + "\\Documents");
        }
    }
    
}
