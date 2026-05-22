package com.fiap.descartecerto.service;


import com.fiap.descartecerto.model.Ecoponto;
import com.fiap.descartecerto.model.Usuario;
import com.fiap.descartecerto.repository.EcopontoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fiap.descartecerto.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CepService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EcopontoRepository ecopontoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Ecoponto> encontrarEcopontosProximosDoUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + idUsuario));

        String cepUsuario = usuario.getCepUsuario().replaceAll("\\D", "");


        double[] coordenadas = consumirApi(cepUsuario);

        return getEcopontos(coordenadas);
    }

    private double[] consumirApi(String cepUsuario) {
        System.out.println("CepUsuario: " + cepUsuario);
        String url = "https://cep.awesomeapi.com.br/json/" + cepUsuario;
        try {
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);

            if (root == null || root.get("lat") == null || root.get("lng") == null) {
                throw new RuntimeException("Coordenadas não encontradas para o CEP: " + cepUsuario);
            }

            return new double[]{
                    root.get("lat").asDouble(),
                    root.get("lng").asDouble()
            };
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar coordenadas na API: " + e.getMessage());
        }
    }

    private double calcularHaversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    @NonNull
    private List<Ecoponto> getEcopontos(double[] coordsUsuario) {
        double latUsuario = coordsUsuario[0];
        double lonUsuario = coordsUsuario[1];

        try {
            List<Ecoponto> todosEcopontos = ecopontoRepository.findAll();
            final double RAIO_MAXIMO = 8.0;

            return todosEcopontos.stream()
                    .peek(ecoponto -> {
                        double dist = calcularHaversine(latUsuario, lonUsuario, ecoponto.getLatitude(), ecoponto.getLongitude());
                        ecoponto.setDistanciaEmKm(Math.round(dist * 100.0) / 100.0);
                    })
                    .filter(ecoponto -> ecoponto.getDistanciaEmKm() <= RAIO_MAXIMO)
                    .sorted((e1, e2) -> Double.compare(e1.getDistanciaEmKm(), e2.getDistanciaEmKm()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar lista de ecopontos do banco: " + e.getMessage());
        }
    }
}