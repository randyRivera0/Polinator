/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gecko
 */
public class Utility {
    

    public static List<String> cargarTemas(String directoryPath) {
        List<String> temasList = new ArrayList<>();
        File directory = new File(directoryPath);

        // Check if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // List all files in the directory
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Check if the file is a .txt file
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        // Add the name of the file to the list
                        temasList.add(file.getName());
                    }
                }
            }
            return temasList;
        } else {
            System.out.println("The specified path is not a directory or does not exist.");
            return null;
        }
    }
    
    public static List<String> loadanswers(String archivo){
        
        List<String> nombres = new ArrayList<>();

        
        // Leer el archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en palabras (el primer elemento será el nombre)
                String[] partes = linea.split(" ");
                String nombre = partes[0];  // Obtener el primer elemento como nombre

                // Agregar el nombre a la lista
                nombres.add(nombre);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nombres;

    }
    
    
    public static <E> Node<String> cargarArchivoPreguntas(String subject){
             
        Path path = Paths.get("questions" + subject + ".txt");
        
        try{
            List<String> questions = Files.readAllLines(path);
            Node<String> root = new Node(questions.get(0));
            for(int i=1; i < questions.size(); i++){
                String question = questions.get(i);
                root.addChildrenQuestion(question);
            }
            
            return root;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public static void cargarArchivoRespuestas(Node<String> root, String subject){
        Path path = Paths.get("answers" + subject + ".txt");
        
        try{
            List<String> questions = Files.readAllLines(path);
            for(String question : questions){
                root.addChildrenAnswer(question);
            }
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static <E> Node<String> cargarArchivoPreguntasUser(String subject){
             
        Path path = Paths.get( subject );
        
        try{
            List<String> questions = Files.readAllLines(path);
            Node<String> root = new Node(questions.get(0));
            for(int i=1; i < questions.size(); i++){
                String question = questions.get(i);
                root.addChildrenQuestion(question);
            }
            
            return root;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public static void cargarArchivoRespuestasUser(Node<String> root, String subject){
        Path path = Paths.get(subject);
        
        try{
            List<String> questions = Files.readAllLines(path);
            for(String question : questions){
                root.addChildrenAnswer(question);
            }
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
