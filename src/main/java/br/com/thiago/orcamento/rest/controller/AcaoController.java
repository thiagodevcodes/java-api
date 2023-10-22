package br.com.thiago.orcamento.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.AcaoDto;
import br.com.thiago.orcamento.rest.form.AcaoForm;
import br.com.thiago.orcamento.rest.form.AcaoUpdateForm;
import br.com.thiago.orcamento.service.AcaoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/acao")
public class AcaoController {
    @Autowired
    AcaoService acaoService;

    @GetMapping
    public ResponseEntity<List<AcaoDto>> findAll() {
        List<AcaoDto> acaoDtoList = acaoService.findAll();
        return ResponseEntity.ok().body(acaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoDto> find(@PathVariable("id") Integer id) {
        AcaoDto acaoDto = acaoService.findById(id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PostMapping
    public ResponseEntity<AcaoDto> insert(@Valid @RequestBody AcaoForm acaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.insert(acaoForm);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoDto> update(@Valid @RequestBody
        AcaoUpdateForm acaoUpdateForm, @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        
        AcaoDto acaoDto = acaoService.updateById(acaoUpdateForm, id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        acaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
