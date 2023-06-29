package howvi.springbootbackend.dto;

import lombok.Data;

@Data
public class JogoDto {

    private String nome;
    private String genero;
    private Boolean midia;
    private Long plataformaId;

}
