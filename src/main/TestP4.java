/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import irrgarten.Game;
import irrgarten.UI.TextUI;
import irrgarten.Player;
import irrgarten.controller.Controller;
public class TestP4 {

    public static void main(String[] args) {
        Game game = new Game(1);
        Player p = new Player('1',1,1);
        game.AddPlayer(p);
        TextUI vista = new TextUI();
        Controller c = new Controller(game,vista);
        c.play();
    }
    
}