package howvi.springbootbackend.controller;

import howvi.springbootbackend.dto.JogoDto;
import howvi.springbootbackend.exception.ResourceNotFoundException;
import howvi.springbootbackend.model.Jogo;
import howvi.springbootbackend.model.Plataforma;
import howvi.springbootbackend.repository.JogoRepository;
import howvi.springbootbackend.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jogo")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private PlataformaRepository plataformaRepository;

    @GetMapping
    public List<Jogo> getJogos(){
        return jogoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getJogoById(@PathVariable Long id) throws ResourceNotFoundException {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não existe sob o id: " + id));
        return ResponseEntity.ok(jogo);
    }

    @PostMapping
    public Jogo createJogo(@RequestBody JogoDto jogoDto) throws ResourceNotFoundException {
        Plataforma plataforma = plataformaRepository.findById(jogoDto.getPlataformaId())
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não existe sob o id: " + jogoDto.getPlataformaId()));
        Jogo jogo = new Jogo();
        jogo.setNome(jogoDto.getNome());
        jogo.setGenero(jogoDto.getGenero());
        jogo.setMidia(jogoDto.getMidia());
        jogo.setPlataforma(plataforma);
        return jogoRepository.save(jogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> updateJogo(@PathVariable Long id, @RequestBody JogoDto jogoDto) throws ResourceNotFoundException {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não existe sob o id: " + id));
        Plataforma plataforma = plataformaRepository.findById(jogoDto.getPlataformaId())
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não existe sob o id: " + jogoDto.getPlataformaId()));
        jogo.setNome(jogoDto.getNome());
        jogo.setGenero(jogoDto.getGenero());
        jogo.setMidia(jogoDto.getMidia());
        jogo.setPlataforma(plataforma);
        Jogo updatedJogo = jogoRepository.save(jogo);
        return ResponseEntity.ok(updatedJogo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteJogo(@PathVariable Long id) throws ResourceNotFoundException {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não existe sob o id: " + id));
        jogoRepository.delete(jogo);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
