/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import irrgarten.Game;
import irrgarten.UI.GraphicalUI;
import irrgarten.controller.Controller;


public class MainGUI{
    
public static void main(String[] args) throws InterruptedException {

    GraphicalUI vista = new GraphicalUI();
    Game game = new Game(1);
    Controller controller = new Controller (game, vista);
    controller.play();
    
    }
}
