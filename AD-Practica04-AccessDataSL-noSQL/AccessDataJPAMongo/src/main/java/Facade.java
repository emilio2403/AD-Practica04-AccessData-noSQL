import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.*;
import dao.*;
import dto.*;
import manager.HibernateController;
import manager.MongoDBController;
import utils.Cifrador;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Fachada del programa
 * @author Dylan & Emilio
 * @verion 1.0 03/02/2022
 */
public class Facade {
    private static Facade instance;

    /**
     * Constructor privado
     */
    private Facade() {}

    /**
     * Patron Singleton
     */
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    /**
     * Datos por defecto al inicializar el programa
     */
    public void initDataBase() {
        removeData();

        HibernateController hc = HibernateController.getInstance();
        hc.open();

        Commit c1 = new Commit("Titulo1", "Texto1", Timestamp.from(Instant.now()));
        Commit c2 = new Commit("Titulo2", "Texto2", Timestamp.from(Instant.now()));
        Departamento d1 = new Departamento("Pepe Perez", 100d, 1000d);
        Departamento d2 = new Departamento("Ana Anaya", 200d, 2000d);
        Issue i1 = new Issue("Issue 1", "Textoooo1", Timestamp.from(Instant.now()), true);
        Issue i2 = new Issue("Issue 2", "Textoooo2", Timestamp.from(Instant.now()), false);
        JefeDepartamento jd1 = new JefeDepartamento();
        JefeDepartamento jd2 = new JefeDepartamento();
        JefeProyecto jp1 = new JefeProyecto();
        JefeProyecto jp2 = new JefeProyecto();
        Login login1 = new Login(1L, LocalDate.now(), UUID.randomUUID(), false);
        Login login2 = new Login(2L, LocalDate.now(), UUID.randomUUID(), false);
        Programador pro1 = new Programador();
        Programador pro2 = new Programador();
        Proyecto proy1 = new Proyecto("Proyecto X", 100d, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));//15
        Proyecto proy2 = new Proyecto("Proyecto Y", 300d, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));//15
        Repositorio rep1 = new Repositorio("Repo 1", Timestamp.from(Instant.now()));//15
        Repositorio rep2 = new Repositorio("Repo 2", Timestamp.from(Instant.now()));//15

        //Commit
        c1.setIssue(i1);
        c2.setIssue(i2);
        c1.setRepositorio(rep1);
        c2.setRepositorio(rep2);
        c1.setProgramador(pro1);
        c2.setProgramador(pro2);

        //Departamento

        d1.setJefeDepartamento(jd1);
        d2.setJefeDepartamento(jd2);
        d1.setProyDesarrollo(List.of(proy1));
        d2.setProyFinalizados(List.of(proy2));
        d1.setTrabajadores(List.of(pro2));
        d2.setTrabajadores(List.of(pro1));

        //Issue

        i1.setJefe(jp1);
        i2.setJefe(jp2);
        i1.setProgramadores(List.of(pro1));
        i2.setProgramadores(List.of(pro2));
        i1.setRepositorio(rep1);
        i2.setRepositorio(rep2);
        i1.setCommit(c1);
        i2.setCommit(c2);

        //JefeDepartamento

        jd1.setNombre("Adolfo");
        jd2.setNombre("JoseLuis perro apruebame");
        jd1.setFechaAlta(Timestamp.from(Instant.now()));
        jd2.setFechaAlta(Timestamp.from(Instant.now()));
        jd1.setSalario(100d);
        jd2.setSalario(3000000d);
        jd1.setTecnologias(List.of("Python", "Java"));
        jd2.setTecnologias(List.of("Kotlin", "Kotlin", "Y MAS KOTLIN"));
        jd1.setDepartamento(d1);
        jd2.setDepartamento(d2);

        //JefeProyecto

        jp1.setNombre("Andres Iniesta");
        jp2.setNombre("El bicho SIUiUIUIUUUU xD");
        jp1.setFechaAlta(Timestamp.from(Instant.now()));
        jp2.setFechaAlta(Timestamp.from(Instant.now()));
        jp1.setSalario(100d);
        jp2.setSalario(3000000d);
        jp1.setTecnologias(List.of("Angular", "Javascript"));
        jp2.setTecnologias(List.of("Pascal", "PHP", "WHITESPACE"));
        jp1.setProyecto(proy1);
        jp2.setProyecto(proy2);
        jp1.setIssues(List.of(i1));
        jp1.setIssues(List.of(i2));

        //Programador

        pro1.setNombre("Manolo");
        pro2.setNombre("Casemiro");
        pro1.setFechaAlta(Timestamp.from(Instant.now()));
        pro2.setFechaAlta(Timestamp.from(Instant.now()));
        pro1.setSalario(1000d);
        pro2.setSalario(1022d);
        pro1.setEmail("programador1@gmail.com");
        pro2.setEmail("programador2@gmail.com");
        pro1.setPassword("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");//1234
        pro2.setPassword("f8638b979b2f4f793ddb6dbd197e0ee25a7a6ea32b0ae22f5e3c5d119d839e75");//5678
        pro1.setTecnologias(List.of("PHP", "DJango"));
        pro2.setTecnologias(List.of("Vue", "SpringBoot"));
        pro1.setDepartamento(d1);
        pro2.setDepartamento(d2);
        pro1.setProyectosParticipa(List.of(proy1));
        pro2.setProyectosParticipa(List.of(proy2));
        pro1.setCommits(List.of(c1));
        pro2.setCommits(List.of(c2));
        pro1.setIssues(List.of(i1));
        pro2.setIssues(List.of(i2));

        //Proyecto

        proy1.setTecnologias(List.of("Flutter", "MongoDB"));
        proy2.setTecnologias(List.of("Angular", "Spring"));
        proy1.setJefe(jp1);
        proy2.setJefe(jp2);
        proy1.setRepositorio(rep1);
        proy2.setRepositorio(rep2);
        proy1.setProgramadores(List.of(pro1));
        proy2.setProgramadores(List.of(pro2));
        proy1.setDepartamento(d1);
        proy2.setDepartamento(d2);

        //Repositorio

        rep1.setProyecto(proy1);
        rep2.setProyecto(proy2);
        rep1.setIssues(List.of(i1));
        rep2.setIssues(List.of(i2));
        rep1.setCommits(List.of(c1));
        rep2.setCommits(List.of(c2));

        hc.getTransaction().begin();

        hc.getManager().persist(d1);
        hc.getManager().persist(d2);

        hc.getManager().persist(jd1);
        hc.getManager().persist(jd2);

        hc.getManager().persist(jp1);
        hc.getManager().persist(jp2);

        hc.getManager().persist(pro1);
        hc.getManager().persist(pro2);

        hc.getManager().persist(proy1);
        hc.getManager().persist(proy2);

        hc.getManager().persist(rep1);
        hc.getManager().persist(rep2);

        hc.getManager().persist(c1);
        hc.getManager().persist(c2);

        hc.getManager().persist(i1);
        hc.getManager().persist(i2);

        hc.getTransaction().commit();

        hc.close();

        //Login

        MongoDBController mongoController = MongoDBController.getInstance();
        mongoController.open();
        MongoCollection<Login> loginCollection = mongoController.getCollection("mongodb", "login", Login.class);
        loginCollection.insertMany(List.of(login1, login2));
        mongoController.close();
    }

    /**
     * Vacía la base de datos
     */
    private void removeData() {
        ConnectionString connectionString = new ConnectionString("mongodb://mongoadmin:mongopass@localhost/mongodb?authSource=admin");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase mongoDB = mongoClient.getDatabase("mongodb");
        mongoDB.drop();
    }

    /**
     * Cuerpo principal del programa
     */
    public void body() {
        Scanner sc = new Scanner(System.in);
        boolean login = login();
        if (login) {
            int opt = 0;
            do {
                menu();
                opt = sc.nextInt();
                switch (opt) {
                    case 1:
                        departamentoCompleto();
                        break;
                    case 2:
                        issuesPorProyecto();
                        break;
                    case 3:
                        programadoresProyectoOrdenados();
                        break;
                    case 4:
                        programadoresCompletos();
                        break;
                    case 5:
                        proyectosMasCaros();
                        break;
                    case 6:
                        proyectosCompletos();
                        break;
                    case 7:
                        loginCompletos();
                        break;
                    case 8:
                        System.out.println("Saliste con éxito.");
                        break;
                    default:
                        System.out.println("Opción incorrecta.");
                }
            } while (opt != 8);
        } else {
            System.out.println("Email o password incorrectos, vuelve a intentarlo");
            body();
        }
    }

    /**
     * Manejo de Login del programa
     * @return boolean
     */
    private boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu email: ");
        String email = sc.next();
        System.out.println("Introduce tu password: ");
        String passwd = sc.next();
        long cont = 3L;
        LoginController loginController = LoginController.getInstance();
        ProgramadorController programadorController = ProgramadorController.getInstance();
        Cifrador cifrador = new Cifrador();
        List<ProgramadorDTO> programadores = programadorController.getAllProgramadores();
        System.out.println(cifrador.toSHA256(passwd));
        for (ProgramadorDTO x : programadores) {
            if (x.getEmail().equals(email) && x.getPassword().equals(cifrador.toSHA256(passwd))) {
                loginController.postLogin(new LoginDTO(cont + 1L, LocalDate.now(), UUID.randomUUID(), true));
                return true;
            }
        }
        return false;
    }

    /**
     * Menu de opciones del programa
     */
    private void menu() {
        System.out.println("\t---   MENU    ---");
        System.out.println("1.- Información departamento completo.");
        System.out.println("2.- Lista de issues abiertas por proyecto.");
        System.out.println("3.- Programador por proyecto ordenados por nº commits.");
        System.out.println("4.- Programadores y su productividad completa.");
        System.out.println("5.- Los 3 proyectos más caros y salarios.");
        System.out.println("6.- Proyectos con información completa.");
        System.out.println("7.- Login completos.");
        System.out.println("8.- Salir.");
        System.out.println("\tELIGA UNA OPCIÓN: ");

    }

    /**
     * Información del departamento completa
     */
    private void departamentoCompleto() {
        System.out.println("Departamento con información completa.");
        DepartamentoController controller = DepartamentoController.getInstance();
        System.out.println(controller.getAllDepartamentos().get(0).getDepartamentoCompleto());
    }

    /**
     * Issues abiertas por proyecto
     */
    private void issuesPorProyecto() {
        System.out.println("Issues abiertas por proyecto.");
        ProyectoController controller = ProyectoController.getInstance();
        controller.getAllProyectos().stream()
                .filter(p -> p.getRepositorio().getIssues().stream().filter(i -> i.getResuelta() == false).collect(Collectors.toList()).size() != 0)
                .forEach(p -> System.out.println(p.issuesAbiertas()));
    }

    /**
     * Programadores por proyecto ordenados
     */
    private void programadoresProyectoOrdenados() {
        System.out.println("Programadores de un proyecto ordenados por número de commits.");
        ProyectoController controller = ProyectoController.getInstance();
        controller.getAllProyectos().forEach(p -> System.out.println(p.ordenarProgramadoresCommit()));
    }

    /**
     * Información completa de los programadores
     */
    private void programadoresCompletos() {
        System.out.println("Programadores con productividad completa.");
        ProgramadorController controller = ProgramadorController.getInstance();
        controller.getAllProgramadores().forEach(p -> System.out.println(p.programadorCompleto()));
    }

    /**
     * Los 3 proyectos más caros
     */
    private void proyectosMasCaros() {
        System.out.println("Los 3 proyectos mas caros y el salario de sus programadores.");
        ProyectoController controller = ProyectoController.getInstance();
        controller.getAllProyectos().stream().sorted(Comparator.comparing(ProyectoDTO::getPresupuesto))
                .limit(3).collect(Collectors.toList()).forEach(p -> System.out.println(p.programadoresSalario()));
    }

    /**
     * Información de los proyectos completa
     */
    private void proyectosCompletos() {
        System.out.println("Proyectos con información completa.");
        ProyectoController controller = ProyectoController.getInstance();
        controller.getAllProyectos().forEach(p -> System.out.println(p.proyectoCompleto()));
    }

    /**
     * Login ordenados por programador
     */
    private void loginCompletos() {
        LoginController controller = LoginController.getInstance();
        System.out.println("Numero de sesiones: " +controller.getAllLogin().size());
        System.out.println("Información completa : " + controller.getAllLogin());
    }

    /**
     * Comprobación del funcionamiento del programa
     */
    public void testingJSON() {
        System.out.println("-------------------------------\n" +
                "\t\tOPERACIONES CRUD\n" +
                "-------------------------------\n");

        System.out.println("**********************\n" +
                "\tDEPARTAMENTO\n" +
                "**********************");
        //this.departamentoJSON();

        System.out.println("**********************\n" +
                "\t\tPROYECTO\n" +
                "**********************");
        //this.proyectoJSON();

        System.out.println("**********************\n" +
                "\t\tPROGRAMADOR\n" +
                "**********************");
        //this.programadorJSON();
        System.out.println("**********************\n" +
                "\tREPOSITORIO\n" +
                "**********************");
        //this.repositorioJSON();
        System.out.println("**********************\n" +
                "\t\tCOMMIT\n" +
                "**********************");
        //this.commitJSON();
        System.out.println("**********************\n" +
                "\t\tISSUE\n" +
                "**********************");
        //this.issueJSON();
        System.out.println("**********************\n" +
                "\tJEFE DEPARTAMENTO\n" +
                "**********************");
        //this.jefeDepartamentoJSON();
        System.out.println("**********************\n" +
                "\tJEFE PROYECTO\n" +
                "**********************");
        //this.jefeProyectoJSON();
    }

    /**
     * Comprobación de Departamento
     */
    private void departamentoJSON() {
        DepartamentoController departamentoController = DepartamentoController.getInstance();

        System.out.println("GET Todos los Departamentos");
        System.out.println(departamentoController.getAllDepartamentos());


        System.out.println("GET Departamento con ID = 3");
        System.out.println(departamentoController.getDepartamentoById(3L));

        System.out.println("POST Insertando Departamento");
        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .nombre("DepartamentoPrueba")
                .programadores(List.of(new Programador()))
                .proyDesarrollo(List.of(new Proyecto()))
                .proyFinalizados(List.of(new Proyecto()))
                .presupuesto(10000.0)
                .presupuestoAnual(100000.0)
                .build();
        System.out.println(departamentoController.postDepartamento(departamentoDTO));

        System.out.println("UPDATE Departamento con ID = 3");
        departamentoDTO = DepartamentoDTO.builder()
                .id(3L)
                .nombre("DepartamentoPruebaUpdated")
                .programadores(List.of(new Programador()))
                .proyDesarrollo(List.of(new Proyecto()))
                .proyFinalizados(List.of(new Proyecto()))
                .presupuesto(10000.0)
                .presupuestoAnual(100000.0)
                .build();
        System.out.println(departamentoController.updateDepartamento(departamentoDTO));

        System.out.println("DELETE Departamento con ID = 3");
        departamentoDTO = DepartamentoDTO.builder()
                .id(3L)
                .build();
        System.out.println(departamentoController.deleteDepartamento(departamentoDTO));
    }

    /**
     * Comprobación de Programador
     */
    private void programadorJSON() {
        ProgramadorController programadorController = ProgramadorController.getInstance();

        System.out.println("GET Todos los Programadores");
        System.out.println(programadorController.getAllProgramadores());

        System.out.println("GET Programador con ID = 8");
        System.out.println(programadorController.getProgramadorById(8L));

        System.out.println("POST Insertando Programador");
        ProgramadorDTO programadorDTO = ProgramadorDTO.builder()
                .nombre("Prueba")
                .tecnologias(List.of(""))
                .salario(1000.0)
                .fechaAlta(Timestamp.valueOf(LocalDateTime.now()))
                .issues(List.of(new Issue()))
                .commits(List.of(new Commit()))
                .proyectosParticipa(List.of(new Proyecto()))
                .departamento(new Departamento())
                .build();
        System.out.println(programadorController.postProgramador(programadorDTO));

        System.out.println("UPDATE Programador con ID = 8");
        programadorDTO = ProgramadorDTO.builder()
                .id(8L)
                .nombre("Prueba2")
                .fechaAlta(Timestamp.valueOf(LocalDateTime.now()))
                .proyectosParticipa(List.of(new Proyecto()))
                .salario(1350.0)
                .departamento(new Departamento())
                .issues(List.of(new Issue()))
                .commits(List.of(new Commit()))
                .build();
        System.out.println(programadorController.updateProgramador(programadorDTO));

        System.out.println("DELETE Programador con ID = 8");
        Departamento d = new Departamento();
        d.setId(10L);
        programadorDTO = ProgramadorDTO.builder()
                .id(8L)
                .departamento(d)
                .proyectosParticipa(List.of(new Proyecto()))
                .issues(List.of(new Issue()))
                .commits(List.of(new Commit()))
                .build();
        System.out.println(programadorController.deleteProgramador(programadorDTO));
    }

    /**
     * Comprobación de Proyecto
     */
    private void proyectoJSON() {
        ProyectoController proyectoController = ProyectoController.getInstance();

        System.out.println("GET Todos los Proyectos");
        System.out.println(proyectoController.getAllProyectos());

        System.out.println("GET Proyecto con ID = 12");
        System.out.println(proyectoController.getProyectoById(12L));

        System.out.println("POST Insertando Proyecto");
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .nombre("Prueba")
                .presupuesto(100.0)
                .fechaInicio(Timestamp.from(Instant.now()))
                .fechaFin(Timestamp.from(Instant.now()))
                .tecnologias(new ArrayList<>())
                .jefe(new JefeProyecto())
                .departamento(new Departamento())
                .repositorio(new Repositorio())
                .programadores(new ArrayList<>())
                .build();
        System.out.println(proyectoController.postProyecto(proyectoDTO));

        System.out.println("UPDATE Proyecto con ID = 18");
        proyectoDTO = ProyectoDTO.builder()
                .id(18L)
                .nombre("Prueba")
                .presupuesto(100.0)
                .departamento(new Departamento())
                .programadores(List.of(new Programador()))
                .jefe(new JefeProyecto())
                .repositorio(new Repositorio())
                .build();
        System.out.println(proyectoController.updateProyecto(proyectoDTO));

        System.out.println("DELETE Proyecto con ID = 12");
        proyectoDTO = ProyectoDTO.builder()
                .id(12L)
                .build();
        System.out.println(proyectoController.deleteProyecto(proyectoDTO));
    }

    /**
     * Comprobación de JefeProyecto
     */
    private void jefeProyectoJSON() {
        JefeProyectoController jefeProyectoController = JefeProyectoController.getInstance();

        System.out.println("GET Todos los Jefes de Proyecto");
        System.out.println(jefeProyectoController.getAllJefeProyecto());

        System.out.println("GET JefeProyecto con ID = 5");
        System.out.println(jefeProyectoController.getJefeProyectoById(5L));

        System.out.println("POST Insertando JefeProyecto");
        JefeProyectoDTO jefeProyectoDTO = JefeProyectoDTO.builder()
                .nombre("jefeProyPrueba")
                .fechaAlta(Timestamp.from(Instant.now()))
                .salario(1000.0)
                .tecnologias(List.of("Tecnologia1", "Tecnologia2"))
                .proyecto(new Proyecto())
                .issues(List.of(new Issue()))
                .build();
        System.out.println(jefeProyectoController.postJefeProyecto(jefeProyectoDTO));

        System.out.println("UPDATE JefeProyecto con ID = 6");
        jefeProyectoDTO = JefeProyectoDTO.builder()
                .id(6L)
                .nombre("jefeProyPrueba")
                .fechaAlta(Timestamp.from(Instant.now()))
                .salario(1000.0)
                .tecnologias(List.of("Tecnologia1", "Tecnologia2"))
                .proyecto(new Proyecto())
                .issues(List.of(new Issue()))
                .build();
        System.out.println(jefeProyectoController.updateJefeProyecto(jefeProyectoDTO));

        System.out.println("DELETE JefeProyecto con ID = 5");
        jefeProyectoDTO = JefeProyectoDTO.builder()
                .id(5L)
                .build();
        System.out.println(jefeProyectoController.deleteJefeProyecto(jefeProyectoDTO));
    }

    /**
     * Comprobación de JefeDepartamento
     */
    private void jefeDepartamentoJSON() {
        JefeDepartamentoController jefeDepartamentoController = JefeDepartamentoController.getInstance();

        System.out.println("GET Todos los Jefes de Departamento");
        System.out.println(jefeDepartamentoController.getAllJefesDepartamento());

        System.out.println("GET JefeDepartamento con ID = 2");
        System.out.println(jefeDepartamentoController.getJefeDepartamentoById(2L));

        System.out.println("POST Insertando JefeDepartamento");
        JefeDepartamentoDTO jefeDepartamentoDTO = JefeDepartamentoDTO.builder()
                .nombre("jefeDepPrueba")
                .fechaAlta(Timestamp.from(Instant.now()))
                .salario(1000.0)
                .tecnologias(List.of("Tecnologia1", "Tecnologia2"))
                .departamento(new Departamento())
                .build();
        System.out.println(jefeDepartamentoController.postJefeDepartamento(jefeDepartamentoDTO));

        System.out.println("UPDATE JefeDepartamento con ID = 2");
        jefeDepartamentoDTO = JefeDepartamentoDTO.builder()
                .id(2L)
                .nombre("jefeDepPruebaUpdated")
                .fechaAlta(Timestamp.from(Instant.now()))
                .salario(1000.0)
                .tecnologias(List.of("Tecnologia1", "Tecnologia2"))
                .departamento(new Departamento())
                .build();
        System.out.println(jefeDepartamentoController.updateJefeDepartamento(jefeDepartamentoDTO));

        System.out.println("DELETE JefeDepartamento con ID = 2");
        jefeDepartamentoDTO = JefeDepartamentoDTO.builder()
                .id(2L)
                .build();
        System.out.println(jefeDepartamentoController.deleteJefeDepartamento(jefeDepartamentoDTO));
    }

    /**
     * Comprobación de Issue
     */
    private void issueJSON() {
        IssueController issueController = IssueController.getInstance();

        System.out.println("GET Todos las Issues");
        System.out.println(issueController.getAllIssue());


        System.out.println("GET Issue con ID = 6");
        System.out.println(issueController.getIssueById(6L));

        System.out.println("POST Insertando Issue");
        IssueDTO issueDTO = IssueDTO.builder()
                .titulo("IssuePrueba")
                .texto("textoooPrueba")
                .fecha(Timestamp.from(Instant.now()))
                .resuelta(true)
                .jefe(new JefeProyecto())
                .programadores(List.of(new Programador()))
                .repositorio(new Repositorio())
                .commit(new Commit())
                .build();
        System.out.println(issueController.postIssue(issueDTO));

        System.out.println("UPDATE Issue con ID = 16");
        issueDTO = IssueDTO.builder()
                .id(16L)
                .titulo("IssuePruebaUpdated")
                .texto("textoooPrueba")
                .fecha(Timestamp.from(Instant.now()))
                .resuelta(true)
                .jefe(new JefeProyecto())
                .programadores(List.of(new Programador()))
                .repositorio(new Repositorio())
                .commit(new Commit())
                .build();
        System.out.println(issueController.updateIssue(issueDTO));

        System.out.println("DELETE Issue con ID = 16");
        issueDTO = IssueDTO.builder()
                .id(16L)
                .build();
        System.out.println(issueController.deleteIssue(issueDTO));
    }

    /**
     * Comprobación de Commit
     */
    private void commitJSON() {
        CommitController commitController = CommitController.getInstance();

        System.out.println("GET Todos los Commits");
        System.out.println(commitController.getAllCommit().toString());


        System.out.println("GET Commit con ID = 7");
        System.out.println(commitController.getCommitById(7L));

        System.out.println("POST Insertando Commit");
        CommitDTO commitDTO = CommitDTO.builder()
                .titulo("CommitPrueba")
                .texto("textoooPrueba")
                .fecha(Timestamp.from(Instant.now()))
                .issue(new Issue())
                .repositorio(new Repositorio())
                .programador(new Programador())
                .build();
        System.out.println(commitController.postCommit(commitDTO));

        System.out.println("UPDATE Commit con ID = 7");
        commitDTO = CommitDTO.builder()
                .id(7L)
                .titulo("CommitPruebaUpdated")
                .texto("textoooPrueba")
                .fecha(Timestamp.from(Instant.now()))
                .issue(new Issue())
                .repositorio(new Repositorio())
                .programador(new Programador())
                .build();
        System.out.println(commitController.updateCommit(commitDTO));

        System.out.println("DELETE Commit con ID = 7");
        commitDTO = CommitDTO.builder()
                .id(7L)
                .build();
        System.out.println(commitController.deleteCommit(commitDTO));
    }

    /**
     * Comprobación de Repositorio
     */
    private void repositorioJSON() {
        RepositorioController repositorioController = RepositorioController.getInstance();

        System.out.println("GET Todos los Repositorios");
        System.out.println(repositorioController.getAllRepositorio());


        System.out.println("GET Repositorio con ID = 11");
        System.out.println(repositorioController.getRepositorioById(11L));

        System.out.println("POST Insertando Repositorio");
        RepositorioDTO repositorioDTO = RepositorioDTO.builder()
                .nombre("RepositorioPrueba")
                .fechaCreacion(Timestamp.from(Instant.now()))
                .proyecto(new Proyecto())
                .issues(List.of(new Issue()))
                .commits(List.of(new Commit()))
                .build();
        System.out.println(repositorioController.postRepositorio(repositorioDTO));

        System.out.println("UPDATE Repositorio con ID = 11");
        repositorioDTO = RepositorioDTO.builder()
                .id(11L)
                .nombre("RepositorioPruebaUpdated")
                .fechaCreacion(Timestamp.from(Instant.now()))
                .proyecto(new Proyecto())
                .issues(List.of(new Issue()))
                .commits(List.of(new Commit()))
                .build();
        System.out.println(repositorioController.updateRepositorio(repositorioDTO));

        System.out.println("DELETE Repositorio con ID = 11");
        repositorioDTO = RepositorioDTO.builder()
                .id(11L)
                .build();
        System.out.println(repositorioController.deleteRepositorio(repositorioDTO));

    }
}
