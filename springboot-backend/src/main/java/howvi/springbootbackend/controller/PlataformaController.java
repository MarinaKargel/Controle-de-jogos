package howvi.springbootbackend.controller;

import howvi.springbootbackend.dto.PlataformaDto;
import howvi.springbootbackend.exception.ResourceNotFoundException;
import howvi.springbootbackend.model.Plataforma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import howvi.springbootbackend.repository.PlataformaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plataforma")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @GetMapping
    public List<Plataforma> getPlataformas(){
        return plataformaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plataforma> getPlataformaById(@PathVariable Long id) throws ResourceNotFoundException {
        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não existe sob o id: " + id));
        return ResponseEntity.ok(plataforma);
    }

    @PostMapping
    public Plataforma createPlataforma(@RequestBody PlataformaDto plataformaDto) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(plataformaDto.getNome());
        plataforma.setMarca(plataformaDto.getMarca());
        plataforma.setPortatil(plataformaDto.getPortatil());
        return plataformaRepository.save(plataforma);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Plataforma> updatePlataforma(@PathVariable Long id, @RequestBody PlataformaDto plataformaDto) throws ResourceNotFoundException {
        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não existe sob o id: " + id));
        plataforma.setNome(plataformaDto.getNome());
        plataforma.setMarca(plataformaDto.getMarca());
        plataforma.setPortatil(plataformaDto.getPortatil());
        Plataforma updatedPlataforma = plataformaRepository.save(plataforma);
        return ResponseEntity.ok(updatedPlataforma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlataforma(@PathVariable Long id) throws ResourceNotFoundException {
        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não existe sob o id: " + id));
        plataformaRepository.delete(plataforma);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
