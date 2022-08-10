package com.pertemuan09.teori09;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pertemuan09.teori09.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.regex.Pattern;

public class HelloController {
    public ListView<User> listComment;
    public TextField username;
    public TextArea comment;
    public Button add;
    public Button saveIO;
    public Button loadIO;
    public Button saveNIO;
    public Button loadNIO;

    private String directory = "C:\\Users\\Master\\Documents\\David\\Kuliah\\SA 2\\prog Terapan\\pertemuan 09\\Teori09";
    private ObservableList<User> listUser = FXCollections.observableArrayList();


    public void initialize(){

        Path p = Paths.get(directory + "/data/comment.txt");

//      Delete file
//        try {
//            Files.delete(p);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Move File
//        try {
//            Files.move(p, Path.of(directory + "/coba pindah/comment.txt"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        cek file
        String filename = directory + "/data/comment.txt";
        File f = new File(filename);
        if(f.isFile()) {
            System.out.println("ada");
        }

//        cek directory
        File d = new File(directory);
        if(f.exists()) {
            System.out.println("ada uga");
        }

    }
    public void refresh() {

        username.setText("");
        comment.setText("");
        listComment.refresh();

    }

    public void add(ActionEvent actionEvent) {

//        Alert kalau field kosong
        if (username.getText() == "" || comment.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "isi semua field");
            alert.showAndWait();
        } else {
            String nama = username.getText();
            String komentar = comment.getText();
            listUser.add(new User(nama, komentar));
            listComment.setItems(listUser);
            refresh();
        }

    }

    public void saveIO(ActionEvent actionEvent) {

        BufferedWriter writer;
        String filename = directory + "/data/comment.txt";
        try {
            writer = new BufferedWriter(new FileWriter(filename));

////        Cara 1 jackson
//            for(int i = 0; i < listUser.size(); i++ ) {
//                writer.write(toJSON(listUser.get(i)));
//                writer.newLine();
//            }

//            Cara 2 Gson
            Gson gson = new Gson();
            String jsonGson = gson.toJson(listComment.getItems());
            writer.write(jsonGson);

            writer.close();
            refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadIO(ActionEvent actionEvent) {

        BufferedReader reader;
        String filename = directory + "/data/comment.txt";
        try {
            reader = new BufferedReader(new FileReader(filename));
            String json = reader.readLine();
            Gson gson = new Gson();
            User[] users = gson.fromJson(json, User[].class);
            listComment.getItems().addAll(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveNIO(ActionEvent actionEvent) {

        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(username.getScene().getWindow());
        Path p = Paths.get(f.toURI());

//        Path p = Paths.get(directory + "/data/comment.txt");

        Gson gson = new Gson();
        String jsonGson = gson.toJson(listComment.getItems());
        try {
            Files.write(p, Collections.singleton(jsonGson));
            refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadNIO(ActionEvent actionEvent) {

        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(username.getScene().getWindow());
//        kalo pilih file baru dijalankan
        if (f != null) {
            Path p = Paths.get(f.toURI());
//        Path p = Paths.get(directory + "/data/comment.txt");

            Gson gson = new Gson();
            User[] users = new User[0];
            try {
                for (String something : Files.readAllLines(p)) {
                    users = gson.fromJson(something, User[].class);
                }
                listComment.getItems().addAll(users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //    buat jackson
    public String toJSON(User tambahUser) throws JsonProcessingException {

        User user = tambahUser;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);
        System.out.println(jsonString);
        return jsonString;

    }

}