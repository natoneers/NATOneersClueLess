/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natoneers.client;


/**
 *
 * @author jermukuokkanen
 */
public class TestGame {
    public static void main(String[] args) {
        new GUIServer().setVisible(true);
        new ClueLessClientMinimal("localhost", 59001).setVisible(true);
    }
}
    


