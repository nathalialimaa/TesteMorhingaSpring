/**
 *
 * Este é o controlador principal da API REST da aplicação, responsável por expor os endpoints
 * que permitem acessar e manipular os dados coletados pelos diversos sensores do sistema.
 *
 * Utiliza o framework Spring Boot com as anotações:
 * - @RestController: define que a classe é um controlador REST.
 * - @RequestMapping("/api"): define a URL base para todos os endpoints dessa classe.
 * - @CrossOrigin: permite chamadas de outras origens (CORS), útil em aplicações front-end separadas.
 *
 * Esta classe injeta serviços relacionados aos sensores e dispositivos, como:
 * - Estação de coleta (DadosEstacaoService)
 * - Pluviômetro (PluviometroService)
 * - Sensores de pH e solo
 * - Reservatórios
 * - Serviço de análise de dados (AnaliseService)
 *
 * O objetivo dessa API é fornecer uma camada de acesso aos dados que pode ser consumida
 * por interfaces web, sistemas de monitoramento ou análises estatísticas.
 */

package com.gpads.moringa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gpads.moringa.entities.*;
import com.gpads.moringa.service.*;
import com.gpads.moringa.statistics.AnaliseService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MainController {

    private final AnaliseService analiseService;
    private final DadosEstacaoService dadosEstacaoService;
    private final PluviometroService pluviometroService;
    private final ReservatoriosService reservatoriosService;
    private final SensorDePhService sensorDePhService;
    private final SensorDeSoloService sensorDeSoloService;

    public MainController(
            AnaliseService analiseService,
            DadosEstacaoService dadosEstacaoService,
            PluviometroService pluviometroService,
            ReservatoriosService reservatoriosService,
            SensorDePhService sensorDePhService,
            SensorDeSoloService sensorDeSoloService) {

        this.analiseService = analiseService;
        this.dadosEstacaoService = dadosEstacaoService;
        this.pluviometroService = pluviometroService;
        this.reservatoriosService = reservatoriosService;
        this.sensorDePhService = sensorDePhService;
        this.sensorDeSoloService = sensorDeSoloService;

    }

    // Endpoint para listar todos os dados da estação
    @GetMapping("/DadosEstacao")
    public ResponseEntity<List<DadosEstacao>> listarDadosEstacao() {
        List<DadosEstacao> lista = dadosEstacaoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/Pluviometro")
    public ResponseEntity<List<Pluviometro>> listarDadosPluviometro() {
        List<Pluviometro> lista = pluviometroService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/SensorDePh")
    public ResponseEntity<List<SensorDePh>> listarDadosSensorDePh() {
        List<SensorDePh> lista = sensorDePhService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/Reservatorios")
    public ResponseEntity<List<Reservatorios>> listarDadosResservatorios() {
        List<Reservatorios> lista = reservatoriosService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/SensorDeSolo")
    public ResponseEntity<List<SensorDeSolo>> listarDadosSensorDeSolo() {
        List<SensorDeSolo> lista = sensorDeSoloService.findAll();
        return ResponseEntity.ok(lista);
    }

}
