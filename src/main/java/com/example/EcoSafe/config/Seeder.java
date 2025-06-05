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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Seeder implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() > 0) {
            System.out.println("=== DADOS JÁ EXISTEM NO BANCO ===");
            return;
        }

        // Criando locais com listas inicializadas
        Local sp = new Local();
        sp.setNome("Centro de Monitoramento SP");
        sp.setCidade("São Paulo");
        sp.setEstado("SP");
        sp.setCoordenadas("-23.5505,-46.6333");
        sp.setEventos(new ArrayList<>());
        sp.setUsuarios(new ArrayList<>());
        sp.setSensores(new ArrayList<>());
        sp = localRepository.save(sp);

        Local rj = new Local();
        rj.setNome("Estação Rio de Janeiro");
        rj.setCidade("Rio de Janeiro");
        rj.setEstado("RJ");
        rj.setCoordenadas("-22.9068,-43.1729");
        rj.setEventos(new ArrayList<>());
        rj.setUsuarios(new ArrayList<>());
        rj.setSensores(new ArrayList<>());
        rj = localRepository.save(rj);

        Local mg = new Local();
        mg.setNome("Base Belo Horizonte");
        mg.setCidade("Belo Horizonte");
        mg.setEstado("MG");
        mg.setCoordenadas("-19.9191,-43.9386");
        mg.setEventos(new ArrayList<>());
        mg.setUsuarios(new ArrayList<>());
        mg.setSensores(new ArrayList<>());
        mg = localRepository.save(mg);

        // Criando usuários
        Usuario admin = new Usuario();
        admin.setNome("Administrador EcoSafe");
        admin.setEmail("admin@ecosafe.com");
        admin.setCpf("12345678901");
        admin.setSenha(passwordEncoder.encode("123456"));
        admin.setLocalizacao(sp);
        usuarioRepository.save(admin);

        Usuario operador = new Usuario();
        operador.setNome("João Silva");
        operador.setEmail("joao@ecosafe.com");
        operador.setCpf("98765432100");
        operador.setSenha(passwordEncoder.encode("123456"));
        operador.setLocalizacao(rj);
        usuarioRepository.save(operador);

        Usuario tecnico = new Usuario();
        tecnico.setNome("Maria Santos");
        tecnico.setEmail("maria@ecosafe.com");
        tecnico.setCpf("11122233344");
        tecnico.setSenha(passwordEncoder.encode("123456"));
        tecnico.setLocalizacao(mg);
        usuarioRepository.save(tecnico);

        // Criando sensores para SP
        Sensor pluviometroSP = new Sensor();
        pluviometroSP.setTipo("Pluviômetro");
        pluviometroSP.setLocalizacao(sp);
        pluviometroSP.setUnidadeMedida("mm");
        pluviometroSP.setStatus("ATIVO");
        sensorRepository.save(pluviometroSP);

        Sensor termometroSP = new Sensor();
        termometroSP.setTipo("Termômetro");
        termometroSP.setLocalizacao(sp);
        termometroSP.setUnidadeMedida("°C");
        termometroSP.setStatus("ATIVO");
        sensorRepository.save(termometroSP);

        // Criando sensores para RJ
        Sensor anemometroRJ = new Sensor();
        anemometroRJ.setTipo("Anemômetro");
        anemometroRJ.setLocalizacao(rj);
        anemometroRJ.setUnidadeMedida("km/h");
        anemometroRJ.setStatus("ATIVO");
        sensorRepository.save(anemometroRJ);

        // Criando eventos e alertas
        Evento eventoSP = new Evento();
        eventoSP.setTipoEvento("Chuva Forte");
        eventoSP.setLocal(sp);
        eventoSP.setNivelRisco("ALTO");
        eventoSP.setDetalhes("Previsão de chuva intensa com possibilidade de alagamentos");
        eventoSP.setAlertas(new ArrayList<>());
        eventoSP = eventoRepository.save(eventoSP);

        Evento eventoRJ = new Evento();
        eventoRJ.setTipoEvento("Vendaval");
        eventoRJ.setLocal(rj);
        eventoRJ.setNivelRisco("MÉDIO");
        eventoRJ.setDetalhes("Previsão de ventos fortes na região costeira");
        eventoRJ.setAlertas(new ArrayList<>());
        eventoRJ = eventoRepository.save(eventoRJ);

        // Criando alertas
        Alerta alertaSP = new Alerta();
        alertaSP.setEvento(eventoSP);
        alertaSP.setMensagem("Alerta de possível alagamento na região");
        alertaSP.setNivelUrgencia("ALTO");
        alertaRepository.save(alertaSP);

        Alerta alertaRJ = new Alerta();
        alertaRJ.setEvento(eventoRJ);
        alertaRJ.setMensagem("Alerta de ventos fortes - Proteja-se");
        alertaRJ.setNivelUrgencia("MÉDIO");
        alertaRepository.save(alertaRJ);

        System.out.println("=== DADOS INICIAIS CARREGADOS ===");
        System.out.println("Locais: 3 (SP, RJ, MG)");
        System.out.println("Usuários: 3 (admin, joao, maria)");
        System.out.println("Sensores: 3 (diversos tipos)");
        System.out.println("Eventos: 2 (SP, RJ)");
        System.out.println("Alertas: 2 (SP, RJ)");
        System.out.println("================================");
    }
}