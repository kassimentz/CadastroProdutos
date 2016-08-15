/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author kassi
 */
public class StringUtils {
    public static final boolean isNullOrEmpty(String str){
        return str == null ||  str.length() == 0;
    }
}
