package com.itesm.fennec.infrastructure.persistence.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PropertyEstimatorRepositoryImpl implements PropertyEstimatorRepository {

    private static final String PREDICT_URL_DEPARTAMENTO = "http://localhost:8000/departamentos/predict";
    private static final String PREDICT_URL_CASA = "http://localhost:8000/casas/predict";

    @Override
    public PredictionResult estimarValorDepartamento(PropertyEstimator property) {
        try {
            // Armar el JSON correcto para el modelo
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("alcaldia", property.getAlcaldia());
            jsonMap.put("metros_cuadrados", property.getMetros_cuadrados());
            jsonMap.put("recamaras", property.getRecamaras());
            jsonMap.put("banos", property.getBanos());
            jsonMap.put("estacionamientos", property.getEstacionamientos());

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonInput = objectMapper.writeValueAsString(jsonMap);

            // Debug: imprimir el JSON que se va a enviar
            System.out.println("Enviando al modelo: " + jsonInput);

            // Conexión HTTP
            URL url = new URL(PREDICT_URL_DEPARTAMENTO);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
            }

            try (InputStream is = conn.getInputStream()) {
                return objectMapper.readValue(is, PredictionResult.class);
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

            try (InputStream is = conn.getInputStream()) {
                return objectMapper.readValue(is, PredictionResult.class);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al llamar al modelo de predicción", e);
        }
    }
}
