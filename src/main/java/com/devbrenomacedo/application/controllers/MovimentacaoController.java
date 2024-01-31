package com.devbrenomacedo.application.controllers;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.Normalizer.Form;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.devbrenomacedo.application.interfaces.MovimentacaoService;
import com.devbrenomacedo.application.services.GastoService;
import com.devbrenomacedo.application.services.ReceitaService;
import com.devbrenomacedo.domain.entities.Gasto;
import com.devbrenomacedo.domain.entities.Movimentacao;
import com.devbrenomacedo.domain.entities.Receita;
import com.devbrenomacedo.domain.enums.Categoria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MovimentacaoController {

    @FXML
    private TextField origemTextField;

    @FXML
    private TextField valorTextField;

    @FXML
    private DatePicker dataDatePicker;

    @FXML
    private ChoiceBox<String> categoriaChoiceBox;

    @FXML
    private CheckBox recorrenteCheckBox;

    @FXML
    private Button registrarButton;

    @FXML
    private Button cancelarButton;

    private MovimentacaoService service;

    public MovimentacaoService getService() {
        return service;
    }

    public void setService(ReceitaService service) {
        this.service = service;
    }

    public void setService(GastoService service) {
        this.service = service;
    }

    public void initialize() {

        List<String> categorias = new ArrayList<>();
        categorias.add("Salário");
        categorias.add("Extra");
        categoriaChoiceBox.getItems().addAll(categorias);

        clearFields();
    }

    @FXML
    private void handleRegistrarMovimentacao(ActionEvent event) {

        String origem = origemTextField.getText();
        String valor = valorTextField.getText();
        String categoria = categoriaChoiceBox.getSelectionModel().getSelectedItem();
        boolean recorrente = recorrenteCheckBox.isSelected();

        if (valor.contains("R$")) {
            valor = valor.replace("R$", "");
        }

        Pattern brlPattern = Pattern.compile("(\\d{1,3}\\.)*(\\d{1,3})\\.?(\\,\\d+)$");
        Pattern usPattern = Pattern.compile("(\\d{1,3}\\,)*(\\d{1,3})\\,?(\\.\\d+)$");
        Pattern anotherPattern = Pattern.compile("(\\d+)(\\,\\d+)$");

        boolean isBrlFormat = brlPattern.matcher(valor).find();
        boolean isUsFormat = usPattern.matcher(valor).find();
        boolean isAnotherFormat = anotherPattern.matcher(valor).find();

        if (isBrlFormat) {
            valor = valor.replace(".", "");
            valor = valor.replace(",", ".");
        } else if (isUsFormat) {
            valor = valor.replace(",", "");
        } else if (isAnotherFormat) {
            valor = valor.replace(",", ".");
        }

        valor = valor.trim();
        Double valorAsDouble = Double.parseDouble(valor);

        LocalDate localDate = dataDatePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

        String normalizer = Normalizer.normalize(categoria, Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        categoria = pattern.matcher(normalizer).replaceAll("");

        categoria = categoria.toUpperCase();

        Categoria categoriaEnum = Categoria.valueOf(categoria);

        if (service instanceof ReceitaService) {
            ReceitaService receitaService = (ReceitaService) service;
            receitaService.registrar(new Receita(null, origem, valorAsDouble,
                    categoriaEnum, recorrente, instant));
        } else if (service instanceof GastoService) {
            GastoService gastoService = (GastoService) service;
            gastoService.registrar(new Gasto(null, origem, valorAsDouble, categoriaEnum,
                    recorrente, instant));
        }
        clearFields();
    }

    @FXML
    private void handleCancelarMovimentacao(ActionEvent event) {
        Node source = (Node) event.getSource();

        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    private void clearFields() {
        origemTextField.setText(null);
        valorTextField.setText("0,00");
        dataDatePicker.setValue(null);
        recorrenteCheckBox.setSelected(false);
        categoriaChoiceBox.getSelectionModel().clearSelection();

    }

}