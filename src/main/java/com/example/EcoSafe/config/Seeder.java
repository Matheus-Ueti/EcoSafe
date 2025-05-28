package com.example.EcoSafe.config;

import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.model.Sensor;
import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.repository.LocalRepository;
import com.example.EcoSafe.repository.SensorRepository;
import com.example.EcoSafe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // Criar locais
        Local sp = new Local();
        sp.setNome("Centro de Monitoramento SP");
        sp.setCidade("São Paulo");
        sp.setEstado("SP");
        sp.setCoordenadas("-23.5505,-46.6333");
        sp = localRepository.save(sp);

        Local rj = new Local();
        rj.setNome("Estação Rio de Janeiro");
        rj.setCidade("Rio de Janeiro");
        rj.setEstado("RJ");
        rj.setCoordenadas("-22.9068,-43.1729");
        rj = localRepository.save(rj);

        Local mg = new Local();
        mg.setNome("Base Belo Horizonte");
        mg.setCidade("Belo Horizonte");
        mg.setEstado("MG");
        mg.setCoordenadas("-19.9191,-43.9386");
        mg = localRepository.save(mg);

        // Criar usuários
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

        // Criar sensores
        Sensor pluviometro = new Sensor();
        pluviometro.setTipo("Pluviômetro");
        pluviometro.setLocalizacao(sp);
        pluviometro.setUnidadeMedida("mm");
        pluviometro.setStatus("ATIVO");
        sensorRepository.save(pluviometro);

        Sensor termometro = new Sensor();
        termometro.setTipo("Termômetro");
        termometro.setLocalizacao(sp);
        termometro.setUnidadeMedida("°C");
        termometro.setStatus("ATIVO");
        sensorRepository.save(termometro);

        Sensor anemometro = new Sensor();
        anemometro.setTipo("Anemômetro");
        anemometro.setLocalizacao(rj);
        anemometro.setUnidadeMedida("km/h");
        anemometro.setStatus("ATIVO");
        sensorRepository.save(anemometro);

        Sensor higrometro = new Sensor();
        higrometro.setTipo("Higrômetro");
        higrometro.setLocalizacao(rj);
        higrometro.setUnidadeMedida("%");
        higrometro.setStatus("MANUTENCAO");
        sensorRepository.save(higrometro);

        Sensor barometro = new Sensor();
        barometro.setTipo("Barômetro");
        barometro.setLocalizacao(mg);
        barometro.setUnidadeMedida("hPa");
        barometro.setStatus("ATIVO");
        sensorRepository.save(barometro);

        System.out.println("=== DADOS INICIAIS CARREGADOS ===");
        System.out.println("Usuários: 3 (admin@ecosafe.com, joao@ecosafe.com, maria@ecosafe.com)");
        System.out.println("Senha padrão: 123456");
        System.out.println("Locais: 3 (SP, RJ, MG)");
        System.out.println("Sensores: 5 (diversos tipos)");
        System.out.println("================================");
    }
} 