package controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DepartamentoDTO;
import repository.RepoDepartamento;
import service.DepartamentoService;

import java.sql.SQLException;


public class DepartamentoController {
    private static DepartamentoController controller = null;

    // Mi Servicio unido al repositorio
    private final DepartamentoService departamentoService;

    // Implementamos nuestro Singleton para el controlador
    private DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }
    /**
     * Patron singleton
     * @author Dylan Hurtado
     * @version 11/12/2021 - 1.0
     */
    public static DepartamentoController getInstance() {
        if (controller == null) {
            controller = new DepartamentoController(new DepartamentoService(new RepoDepartamento()));
        }
        return controller;
    }
    /**
     * Printea  todos los departamentos en JSON
     * @author Dylan Hurtado
     * @version 11/12/2021 - 1.0
     */
    public String getAllDepartamentos() {
        try {
            // Vamos a devolver el JSON de los departamentos
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(departamentoService.getAllDepartamentos());
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en getAll: " + e.getMessage());
            return "Error DepartamentoController en getAll: " + e.getMessage();
        }
    }
    /**
     * Printea  departamento por id en JSON
     * @author Dylan Hurtado
     * @version 11/12/2021 - 1.0
     */
    public String getDepartamentoByIdJSON(Long id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(departamentoService.getDepartamentoById(id));
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en getDepartamentoById: " + e.getMessage());
            return "Error DepartamentoController en getDepartamentoById: " + e.getMessage();
        }
    }
    /**
     * Printea  el save de departamento en JSON
     * @author Dylan Hurtado
     * @version 11/12/2021 - 1.0
     */
    public String postDepartamento(DepartamentoDTO departamentoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(departamentoService.postDepartamento(departamentoDTO));
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en postDepartamento: " + e.getMessage());
            return "Error DepartamentoController en postDepartamento: " + e.getMessage();
        }
    }
    /**
     * Printea  el update de departamento en JSON
     * @author Dylan Hurtado
     * @version 11/12/2021 - 1.0
     */
    public String updateDepartamento(DepartamentoDTO departamentoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(departamentoService.updateDepartamento(departamentoDTO));
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en updateDepartamento: " + e.getMessage());
            return "Error DepartamentoController en updateDepartamento: " + e.getMessage();
        }
    }
    /**
     * Printea  el delete de departamento en JSON
     * @author Dylan Hurtado
     * @version 11/12/2021 - 1.0
     */
    public String deleteDepartamento(DepartamentoDTO departamentoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(departamentoService.deleteDepartamento(departamentoDTO));
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en deleteDepartamento: " + e.getMessage());
            return "Error DepartamentoController en deleteDepartamento: " + e.getMessage();
        }
    }
}