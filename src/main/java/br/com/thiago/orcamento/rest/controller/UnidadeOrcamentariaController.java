package br.com.thiago.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.service.UnidadeOrcamentariaService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import br.com.thiago.orcamento.rest.dto.UnidadeOrcamentariaDto;
import br.com.thiago.orcamento.rest.form.UnidadeOrcamentariaForm;

@RestController
@RequestMapping("/unidade-orcamentaria")
public class UnidadeOrcamentariaController {
    @Autowired
    UnidadeOrcamentariaService unidadeOrcamentariaService;

    @GetMapping
    public ResponseEntity<Page<UnidadeOrcamentariaDto>> findAll(Pageable page) {
        Page<UnidadeOrcamentariaDto> unidadeOrcamentariaDtoPage = unidadeOrcamentariaService.findAll(page);
        return ResponseEntity.ok().body(unidadeOrcamentariaDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> find(@PathVariable("id") Integer id) {
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.findById(id);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeOrcamentariaDto> insert(@Valid @RequestBody UnidadeOrcamentariaForm unidadeOrcamentariaForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.insert(unidadeOrcamentariaForm);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> update(@Valid @RequestBody
        UnidadeOrcamentariaForm unidadeOrcamentariaForm, @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }
        
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.updateById(unidadeOrcamentariaForm, id);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        unidadeOrcamentariaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
