package br.itb.projeto.vitalususPlus.rest.controller;

import br.itb.projeto.vitalususPlus.model.entity.Admin;
import br.itb.projeto.vitalususPlus.model.entity.Comentario;
import br.itb.projeto.vitalususPlus.service.AdminService;
import br.itb.projeto.vitalususPlus.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vitalusus/comentario")
public class ComentarioController {
    private ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        super();
        this.comentarioService = comentarioService;
    }
    @GetMapping("findAll")
    public ResponseEntity<List<Comentario>> findAll(){
        List<Comentario> comentarios = this.comentarioService.findAll();
        return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
    }
    @GetMapping("findById/{id}")
    public ResponseEntity<Comentario> findById(@PathVariable long id){
        Comentario comentario = this.comentarioService.findById(id);
        return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
    }
    @PostMapping("post")
    public ResponseEntity<Comentario> salvarComentario(@RequestBody Comentario comentario){
        Comentario comentarioSalvo = this.comentarioService.save(comentario);
        return new ResponseEntity<Comentario>(comentarioSalvo, HttpStatus.OK);
    }
    @DeleteMapping("delete")
    public void deletarComentario(@RequestBody Comentario comentario){
        this.comentarioService.delete(comentario);
    }
    @PutMapping("update")
    public ResponseEntity<Comentario> updateComentario(@RequestBody Comentario comentario){
        Comentario comentarioUpdatado = this.comentarioService.update(comentario);
        return new ResponseEntity<Comentario>(comentarioUpdatado, HttpStatus.OK);
    }
}
