package com.example.EcoSafe.config;

import com.example.EcoSafe.model.Alerta;
import com.example.EcoSafe.model.Evento;
import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.model.Sensor;
import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.repository.AlertaRepository;
import com.example.EcoSafe.repository.EventoRepository;
import com.example.EcoSafe.repository.LocalRepository;
import com.example.EcoSafe.repository.SensorRepository;
import com.example.EcoSafe.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseSeeder {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (usuarioRepository.count() > 0) {
            System.out.println("=== DADOS JÁ EXISTEM NO BANCO ===");
            return;
        }

        // Criando locais
        var locais = List.of(
            Local.builder()
                .nome("Centro de São Paulo")
                .cidade("São Paulo")
                .estado("SP")
                .coordenadas("-23.5505,-46.6333")
                .build(),
            Local.builder()
                .nome("Zona Sul do Rio")
                .cidade("Rio de Janeiro")
                .estado("RJ")
                .coordenadas("-22.9068,-43.1729")
                .build(),
            Local.builder()
                .nome("Região Central BH")
                .cidade("Belo Horizonte")
                .estado("MG")
                .coordenadas("-19.9208,-43.9378")
                .build()
        );

        localRepository.saveAll(locais);

        var usuarios = List.of(
            Usuario.builder()
                .nome("Administrador EcoSafe")
                .email("admin@ecosafe.com")
                .cpf("12345678901")
                .senha(passwordEncoder.encode("123456"))
                .localizacao(locais.get(0))
                .build(),
            Usuario.builder()
                .nome("João Silva")
                .email("joao@ecosafe.com")
                .cpf("98765432100")
                .senha(passwordEncoder.encode("123456"))
                .localizacao(locais.get(1))
                .build(),
            Usuario.builder()
                .nome("Maria Santos")
                .email("maria@ecosafe.com")
                .cpf("11122233344")
                .senha(passwordEncoder.encode("123456"))
                .localizacao(locais.get(2))
                .build()
        );

        usuarioRepository.saveAll(usuarios);

        // Criando sensores com tipos do frontend
        var sensores = List.of(
            Sensor.builder()
                .tipo("Temperatura")
                .localizacao(locais.get(0))
                .unidadeMedida("°C")
                .status("Ativo")
                .build(),
            Sensor.builder()
                .tipo("Umidade")
                .localizacao(locais.get(0))
                .unidadeMedida("%")
                .status("Ativo")
                .build(),
            Sensor.builder()
                .tipo("Pressão")
                .localizacao(locais.get(1))
                .unidadeMedida("mmHg")
                .status("Manutenção")
                .build(),
            Sensor.builder()
                .tipo("Pluviometria")
                .localizacao(locais.get(2))
                .unidadeMedida("mm")
                .status("Inativo")
                .build()
        );

        sensorRepository.saveAll(sensores);
        var eventos = List.of(
            Evento.builder()
                .tipoEvento("Enchente")
                .local(locais.get(0))
                .nivelRisco("Alto")
                .detalhes("Previsão de chuva intensa com possibilidade de alagamentos no centro da cidade")
                .build(),
            Evento.builder()
                .tipoEvento("Incêndio Florestal")
                .local(locais.get(1))
                .nivelRisco("Crítico")
                .detalhes("Focos de incêndio detectados na região serrana com vento forte")
                .build(),
            Evento.builder()
                .tipoEvento("Vento Forte")
                .local(locais.get(2))
                .nivelRisco("Médio")
                .detalhes("Rajadas de vento de até 80km/h previstas para a região")
                .build(),
            Evento.builder()
                .tipoEvento("Tempestade")
                .local(locais.get(0))
                .nivelRisco("Alto")
                .detalhes("Tempestade elétrica com granizo e ventos fortes")
                .build()
        );

        eventoRepository.saveAll(eventos);

        // Criando alertas com níveis do frontend
        var alertas = List.of(
            Alerta.builder()
                .evento(eventos.get(0))
                .mensagem("ALERTA ENCHENTE: Evite áreas baixas e próximas aos rios. Risco de alagamento iminente!")
                .nivelUrgencia("Alto")
                .build(),
            Alerta.builder()
                .evento(eventos.get(1))
                .mensagem("EMERGÊNCIA INCÊNDIO: Evacuação imediata necessária! Procure abrigo seguro.")
                .nivelUrgencia("Crítico")
                .build(),
            Alerta.builder()
                .evento(eventos.get(2))
                .mensagem("Ventos fortes na região. Evite áreas abertas e cuidado com objetos soltos.")
                .nivelUrgencia("Médio")
                .build(),
            Alerta.builder()
                .evento(eventos.get(3))
                .mensagem("Tempestade se aproximando. Procure abrigo e evite áreas descampadas.")
                .nivelUrgencia("Alto")
                .build()
        );

        alertaRepository.saveAll(alertas);

        System.out.println("=== DADOS INICIAIS CARREGADOS ===");
        System.out.println("Locais: " + locais.size() + " (Centro de São Paulo, Zona Sul do Rio, Região Central BH)");
        System.out.println("Usuários: " + usuarios.size() + " (admin, joao, maria)");
        System.out.println("Sensores: " + sensores.size() + " (Temperatura, Umidade, Pressão, Pluviometria)");
        System.out.println("Eventos: " + eventos.size() + " (Enchente, Incêndio Florestal, Vento Forte, Tempestade)");
        System.out.println("Alertas: " + alertas.size() + " (Alto, Crítico, Médio)");
        System.out.println("Status dos sensores: Ativo, Manutenção, Inativo");
        System.out.println("Níveis de risco: Baixo, Médio, Alto, Crítico");
        System.out.println("================================");
    }
}