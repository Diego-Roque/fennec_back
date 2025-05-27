package com.itesm.fennec.infrastructure.persistence.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@ApplicationScoped
public class PropertyEstimatorRepositoryImpl implements PropertyEstimatorRepository {

    private static final String PREDICT_URL_DEPARTAMENTO = "http://localhost:8000/departamentos/predict";
    private static final String PREDICT_URL_CASA = "http://localhost:8000/casa/predict";

    @Override
    public PredictionResult estimarValorDepartamento(PropertyEstimator propertyEstimator) {
        try {
            URL url = new URL(PREDICT_URL_DEPARTAMENTO);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(propertyEstimator);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
            }

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                String response = scanner.useDelimiter("\\A").next();
                return objectMapper.readValue(response, PredictionResult.class);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al llamar al modelo de predicción", e);
        }
    }

    @Override
    public PredictionResult estimarValorCasa(PropertyEstimator propertyEstimator) {
        try {
            URL url = new URL(PREDICT_URL_CASA);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(propertyEstimator);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
            }

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                String response = scanner.useDelimiter("\\A").next();
                return objectMapper.readValue(response, PredictionResult.class);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al llamar al modelo de predicción", e);
        }
    }
}
