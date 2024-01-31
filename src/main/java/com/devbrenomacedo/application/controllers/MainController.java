package com.devbrenomacedo.application.controllers;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.devbrenomacedo.application.interfaces.MovimentacaoService;
import com.devbrenomacedo.application.repositories.MovimentacaoRepository;
import com.devbrenomacedo.application.services.GastoService;
import com.devbrenomacedo.application.services.ReceitaService;
import com.devbrenomacedo.domain.entities.Gasto;
import com.devbrenomacedo.domain.entities.Receita;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    @Autowired
    private MovimentacaoRepository<Receita> receitaRepository;

    @Autowired
    private MovimentacaoRepository<Gasto> gastoRepository;

    @FXML
    private Label dataAtualLabel;

    @FXML
    private Label receitaAtualLabel;

    @FXML
    private Label gastoAtualLabel;

    @FXML
    private Label dataAnteriorLabel;

    @FXML
    private Label receitaAnteriorLabel;

    @FXML
    private Label gastoAnteriorLabel;

    @FXML
    private Label totalLabel;

    public void initialize() {

        String dataAtualText = this.getDataAtual();
        String receitaAtualText = this.getReceitaAtual();
        String gastoAtualText = this.getGastoAtual();

        String dataAnteriorText = this.getDataAnterior();
        String receitaAnteriorText = this.getReceitaAnterior();
        String gastoAnteriorText = this.getGastoAnterior();

        dataAtualLabel.setText(dataAtualText);
        receitaAtualLabel.setText(receitaAtualText);
        gastoAtualLabel.setText(gastoAtualText);

        dataAnteriorLabel.setText(dataAnteriorText);
        receitaAnteriorLabel.setText(receitaAnteriorText);
        gastoAnteriorLabel.setText(gastoAnteriorText);

        totalLabel.setText(Double.toString(123 + 456));
    }

    @FXML
    private void handleRegistrarReceita(ActionEvent event) {
        Stage stage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/registrar-movimentacao.fxml"));

            Parent root = loader.load();

            MovimentacaoController controller = loader.getController();

            System.out.println(receitaRepository);

            ReceitaService receitaService = new ReceitaService(this.receitaRepository);

            controller.setService(receitaService);
            // Configurar a cena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
        } catch (RuntimeException | IOException err) {
            System.out.println(err);
        } finally {
            stage.setTitle("Registrar receita");

            // Mostrar a janela
            stage.show();

        }
    }

    @FXML
    private void handleAtualizarReceita(ActionEvent event) {
        System.out.println("Receita atualizada!");
    }

    @FXML
    private void handleRemoverReceita(ActionEvent event) {

        System.out.println("Receita removida!");
    }

    @FXML
    private void handleRegistrarGasto(ActionEvent event) {
        Stage stage = new Stage();

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/registrar-movimentacao.fxml"));

            Parent root = loader.load();

            // // Obter o controlador associado ao FXML
            MovimentacaoController controller = loader.getController();

            GastoService gastoService = new GastoService(this.gastoRepository);

            controller.setService(gastoService);
            // Configurar a cena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
        } catch (RuntimeException | IOException err) {
            System.out.println(err);
        } finally {
            stage.setTitle("Registrar gasto");

            // Mostrar a janela
            stage.show();
        }
    }

    @FXML
    private void handleAtualizarGasto(ActionEvent event) {
        System.out.println("Gasto atualizado!");
    }

    @FXML
    private void handleRemoverGasto(ActionEvent event) {
        System.out.println("Gasto removido!");
    }

    @FXML
    private void handleGerarPDF(ActionEvent event) {
        System.out.println("PDF gerado!");
    }

    @FXML
    private void handleGerarXLSX(ActionEvent event) {
        System.out.println("XLSX gerado!");
    }

    @FXML
    private void handleGerarJSON(ActionEvent event) {
        System.out.println("JSON gerado!");
    }

    private String getDataAtual() {
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return formatter.format(now.atZone(ZoneId.systemDefault()));
    }

    private String getDataAnterior() {
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return formatter.format(now.minus(30, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()));
    }

    private String getReceitaAtual() {
        double valor = 1234.56;
        Locale locale = Locale.getDefault();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String valorFormatado = numberFormat.format(valor);

        return valorFormatado;
    }

    private String getGastoAtual() {
        double valor = 1234.56;
        Locale locale = Locale.getDefault();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String valorFormatado = numberFormat.format(valor);

        return valorFormatado;
    }

    private String getReceitaAnterior() {
        double valor = 1234.56;
        Locale locale = Locale.getDefault();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String valorFormatado = numberFormat.format(valor);

        return valorFormatado;
    }

    private String getGastoAnterior() {
        double valor = 1234.56;
        Locale locale = Locale.getDefault();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String valorFormatado = numberFormat.format(valor);

        return valorFormatado;
    }

    public Label getTotalLabel() {
        return totalLabel;
    }
}
