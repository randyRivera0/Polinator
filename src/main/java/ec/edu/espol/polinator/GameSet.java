/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author leoza
 */

import java.util.ArrayList;
import java.util.List;

public class GameSet {
    
    private static GameSet instance;
    private List<Round> rounds;

    private GameSet() {
        rounds = new ArrayList<>();
        
    }
    
    
    public static GameSet getInstance() {
        if (instance == null) {
            instance = new GameSet();
        }
        return instance;
    }
    

    public void addGame(Round round) {
        rounds.add(round);
    }

    public List<Round> getGames() {
        return rounds;
    }
}
