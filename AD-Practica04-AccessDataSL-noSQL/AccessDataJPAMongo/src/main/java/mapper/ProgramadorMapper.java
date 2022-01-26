package mapper;

import dto.ProgramadorDTO;
import dao.Programador;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {

    @Override
    public Programador fromDTO(ProgramadorDTO item) {
        return Programador.builder()
                .departamento(item.getDepartamento())
                .proyectosParticipa(item.getProyectosParticipa())
                .commits(item.getCommits())
                .issues(item.getIssues())
                .build();
    }

    @Override
    public ProgramadorDTO toDTO(Programador item) {
        return ProgramadorDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .fechaAlta(item.getFechaAlta())
                .salario(item.getSalario())
                .tecnologias(item.getTecnologias())
                .departamento(item.getDepartamento())
                .proyectosParticipa(item.getProyectosParticipa())
                .commits(item.getCommits())
                .issues(item.getIssues())
                .build();
    }
}
