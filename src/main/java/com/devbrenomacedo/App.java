package com.devbrenomacedo;

import java.io.IOException;
import java.util.Locale;

import com.devbrenomacedo.application.controllers.MainController;
import com.devbrenomacedo.application.repositories.MovimentacaoRepository;
import com.devbrenomacedo.domain.entities.Gasto;
import com.devbrenomacedo.domain.entities.Receita;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Carregar o arquivo FXML
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            // // Obter o controlador associado ao FXML
            MainController controller = loader.getController();

            // Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            primaryStage.setScene(scene);
        } catch (

        RuntimeException err) {
            System.out.println(err);
        } finally {
            primaryStage.setTitle("Minha Aplicação JavaFX");

            // Mostrar a janela
            primaryStage.show();
        }
    }

}
