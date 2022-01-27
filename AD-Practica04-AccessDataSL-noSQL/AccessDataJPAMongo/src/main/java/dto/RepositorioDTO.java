package dto;

import dao.Commit;
import dao.Issue;
import dao.Programador;
import dao.Proyecto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositorioDTO {
    private long id;
    private String nombre;
    private Timestamp fechaCreacion;
    private Proyecto proyecto;
    private List<Issue> issues;
    private List<Commit> commits;

    @Override
    public String toString(){
        return "Repositorio{id="+this.id
                +", nombre="+this.nombre
                +", fecha_creacion="+this.fechaCreacion
                +", proyecto="+this.proyecto.getId()
                +", issues="+issues.stream().map(Issue::getId).collect(Collectors.toList())
                +", commit="+commits.stream().map(Commit::getId).collect(Collectors.toList())
                +"}";
    }
}
