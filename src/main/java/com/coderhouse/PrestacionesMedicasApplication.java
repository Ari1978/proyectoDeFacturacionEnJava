package com.coderhouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Categoria;
import com.coderhouse.models.Paciente;
import com.coderhouse.models.Practica;
import com.coderhouse.models.Turno;

@EnableJpaAuditing
@SpringBootApplication
public class PrestacionesMedicasApplication implements CommandLineRunner {

    @Autowired
    private DaoFactory dao;

    public static void main(String[] args) {
        SpringApplication.run(PrestacionesMedicasApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // 1. Crear y persistir categorías (usa la instancia retornada por persistirCategoria)
            Categoria categoria1 = dao.persistirCategoria(new Categoria("Laboratorio"));
            Categoria categoria2 = dao.persistirCategoria(new Categoria("Estudios por imagenes"));
            Categoria categoria3 = dao.persistirCategoria(new Categoria("Estudios complementarios"));

            // 2. Crear y persistir prácticas (asocia las categorías persistidas, no los objetos originales)
            Practica practica1 = new Practica("Electrocardiograma", "Registro eléctrico del corazón", 3500.0, categoria2);
            Practica practica2 = new Practica("Análisis de orina", "Análisis clínico de orina", 1800.0, categoria1);
            Practica practica3 = new Practica("Análisis de sangre", "Estudio de sangre completo", 2500.0, categoria1);
            Practica practica4 = new Practica("Radiografías", "Imágenes por rayos X", 4000.0, categoria2);
            Practica practica5 = new Practica("Audiometría", "Medición de la audición", 2000.0, categoria3);
            Practica practica6 = new Practica("Tomografía", "Imagen médica por secciones", 8000.0, categoria2);
            Practica practica7 = new Practica("Electroencefalograma", "Actividad cerebral", 4500.0, categoria3);
            Practica practica8 = new Practica("Ecografía", "Imagen con ultrasonido", 3200.0, categoria2);
            Practica practica9 = new Practica("Resonancia magnética", "Imágenes por campos magnéticos", 9000.0, categoria2);
            Practica practica10 = new Practica("Endoscopia", "Visualización del interior del cuerpo", 5000.0, categoria2);

            dao.persistirPractica(practica1);
            dao.persistirPractica(practica2);
            dao.persistirPractica(practica3);
            dao.persistirPractica(practica4);
            dao.persistirPractica(practica5);
            dao.persistirPractica(practica6);
            dao.persistirPractica(practica7);
            dao.persistirPractica(practica8);
            dao.persistirPractica(practica9);
            dao.persistirPractica(practica10);

            // 3. Crear y persistir pacientes
            Paciente paciente1 = new Paciente("Carlos", "Lopez", 34550623, 35, "Masculino", "1156989325", "Swiss Medical");
            Paciente paciente2 = new Paciente("Pedro", "Juarez", 32557628, 33, "Masculino", "1133989329", "Medife");
            Paciente paciente3 = new Paciente("Gabriela", "Mangioni", 30444623, 29, "Femenino", "1144882233", "Galeno");
            Paciente paciente4 = new Paciente("Carla", "Gomez", 26555623, 46, "Femenino", "1169993332", "Osde");
            Paciente paciente5 = new Paciente("Federico", "Mazur", 44555623, 22, "Masculino", "1168775523", "Sancor Salud");
            Paciente paciente6 = new Paciente("Lorena", "Ferreyra", 24555623, 48, "Femenino", "1133311125", "Premedic");
            Paciente paciente7 = new Paciente("Mariano", "Arguello", 34111212, 35, "Masculino", "1198522559", "Medical");
            Paciente paciente8 = new Paciente("Marcelo", "Rodriguez", 29595999, 41, "Masculino", "1121218932", "Medife");
            Paciente paciente9 = new Paciente("Abigail", "Gutierrez", 383333623, 30, "Femenino", "1148852361", "Galeno");
            Paciente paciente10 = new Paciente("Facundo", "Soto", 34666878, 35, "Masculino", "1152643289", "Swiss Medical");
            Paciente paciente11 = new Paciente("Juan", "Lopez", 31222653, 32, "Masculino", "1166332458", "Premedic");

            dao.persistirPaciente(paciente1);
            dao.persistirPaciente(paciente2);
            dao.persistirPaciente(paciente3);
            dao.persistirPaciente(paciente4);
            dao.persistirPaciente(paciente5);
            dao.persistirPaciente(paciente6);
            dao.persistirPaciente(paciente7);
            dao.persistirPaciente(paciente8);
            dao.persistirPaciente(paciente9);
            dao.persistirPaciente(paciente10);
            dao.persistirPaciente(paciente11);

            // 4. Crear y persistir turnos
            Turno turno1 = new Turno(paciente1, practica5, LocalDate.now());
            Turno turno2 = new Turno(paciente2, practica4, LocalDate.now());
            Turno turno3 = new Turno(paciente3, practica2, LocalDate.now());
            Turno turno4 = new Turno(paciente4, practica8, LocalDate.now());
            Turno turno5 = new Turno(paciente5, practica5, LocalDate.now());

            dao.persistirTurno(turno1);
            dao.persistirTurno(turno2);
            dao.persistirTurno(turno3);
            dao.persistirTurno(turno4);
            dao.persistirTurno(turno5);

            // 5. Crear y persistir un nuevo paciente y asociar prácticas
            Paciente paciente = new Paciente("Carlos", "Gomez", 36570880, 32, "Masculino", "1156989325", "Swiss Medical");
            dao.persistirPaciente(paciente);

            // 6. Asignar prácticas al nuevo paciente
            List<Long> practicasParaPaciente = new ArrayList<>();
            if (practica1.getId() != null) practicasParaPaciente.add(practica1.getId());
            if (practica2.getId() != null) practicasParaPaciente.add(practica2.getId());
            if (practica3.getId() != null) practicasParaPaciente.add(practica3.getId());

            if (paciente.getId() != null && !practicasParaPaciente.isEmpty()) {
                dao.asignarPacienteAPractica(paciente.getId(), practicasParaPaciente);
            }

        } catch (Exception err) {
            System.out.println("Error al iniciar datos: " + err.getMessage());
            err.printStackTrace();
        }

}
}