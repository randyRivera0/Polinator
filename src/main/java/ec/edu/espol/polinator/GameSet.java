/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author leoza
 */
import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class GameSet {

    private static GameSet instance;
    private List<Game> games;

    private GameSet() {
        games = new ArrayList<>();
    }

    public static GameSet getInstance() {
        if (instance == null) {
            instance = new GameSet();
        }
        return instance;
    }

    public void addGame(File preguntasFile, File respuestasFile, String gameName) {
        Game game = new Game(preguntasFile, respuestasFile, gameName);
        games.add(game);
    }

    public List<Game> getGames() {
        return games;
    }
}
