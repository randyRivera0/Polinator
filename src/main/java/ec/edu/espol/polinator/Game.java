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

public class Game {

    private File preguntasFile;
    private File respuestasFile;
    private String name;

    public Game(File preguntasFile, File respuestasFile, String name) {
        this.preguntasFile = preguntasFile;
        this.respuestasFile = respuestasFile;
        this.name = name;
    }

    public File getPreguntasFile() {
        return preguntasFile;
    }

    public File getRespuestasFile() {
        return respuestasFile;
    }

    public String getName() {
        return name;
    }
}
