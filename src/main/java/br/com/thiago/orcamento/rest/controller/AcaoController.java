package br.com.thiago.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.AcaoDto;
import br.com.thiago.orcamento.rest.form.AcaoForm;
import br.com.thiago.orcamento.service.AcaoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;


@RestController
@RequestMapping("/acao")
public class AcaoController {
    @Autowired
    AcaoService acaoService;

    @GetMapping
    public ResponseEntity<Page<AcaoDto>> findAll(Pageable page) {
        Page<AcaoDto> acaoDtoPage = acaoService.findAll(page);
        return ResponseEntity.ok().body(acaoDtoPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AcaoDto>> findAllData() {
        List<AcaoDto> acaoDtoList = acaoService.findAllData();
        return ResponseEntity.ok().body(acaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoDto> find(@PathVariable("id") Integer id) {        
        AcaoDto acaoDto = acaoService.findById(id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PostMapping
    public ResponseEntity<AcaoDto> insert(@Valid @RequestBody AcaoForm acaoForm, BindingResult br) {
            
        if (br.hasErrors()) {
            List<String> errors = br.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            throw new ConstraintException("Restrição de Dados", errors);
        }

        AcaoDto acaoDto = acaoService.insert(acaoForm);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoDto> update(@Valid @RequestBody
        AcaoForm acaoForm, @PathVariable("id") Integer id, BindingResult br) {
       
        if (br.hasErrors()) {
            List<String> errors = new ArrayList<>();
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });

            throw new ConstraintException("Restrição de Dados", errors);
        }
     
        AcaoDto acaoDto = acaoService.updateById(acaoForm, id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        acaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


