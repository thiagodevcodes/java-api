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

import br.com.thiago.orcamento.rest.dto.UnidadeDto;
import br.com.thiago.orcamento.rest.form.UnidadeForm;
import br.com.thiago.orcamento.service.UnidadeService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<Page<UnidadeDto>> findAll(Pageable page) {
        Page<UnidadeDto> unidadeDtoPage = unidadeService.findAll(page);
        return ResponseEntity.ok().body(unidadeDtoPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UnidadeDto>> findAllData() {
        List<UnidadeDto> uidadeDtoList = unidadeService.findAllData();
        return ResponseEntity.ok().body(uidadeDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDto> find(@PathVariable("id") Integer id) {
        UnidadeDto unidadeDto = unidadeService.findById(id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeDto> insert(@Valid @RequestBody UnidadeForm unidadeForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        UnidadeDto unidadeDto = unidadeService.insert(unidadeForm);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDto> update(@Valid @RequestBody UnidadeForm unidadeForm
            , @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        UnidadeDto unidadeDto = unidadeService.updateById(unidadeForm, id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
