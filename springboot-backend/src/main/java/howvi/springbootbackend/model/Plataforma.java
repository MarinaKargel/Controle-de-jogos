package howvi.springbootbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "plataforma")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plataformaSeqId")
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "marca", length = 20)
    private String marca;

    @Column(name = "portatil")
    private Boolean portatil;

}
