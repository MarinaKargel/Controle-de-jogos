package howvi.springbootbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jogoSeqId")
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "genero", length = 20)
    private String genero;

    @Column(name = "midia")
    private Boolean midia;

    @ManyToOne
    @JoinColumn(name = "plataformaSeqId")
    private Plataforma plataforma;

}
