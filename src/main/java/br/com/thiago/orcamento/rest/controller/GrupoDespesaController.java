package br.com.thiago.orcamento.rest.controller;

import br.com.thiago.orcamento.rest.dto.GrupoDespesaDto;
import br.com.thiago.orcamento.rest.form.GrupoDespesaForm;
import br.com.thiago.orcamento.rest.form.GrupoDespesaUpdateForm;
import br.com.thiago.orcamento.service.GrupoDespesaService;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/grupo-despesa")
public class GrupoDespesaController {
    @Autowired
    GrupoDespesaService grupoDespesaService;

    @GetMapping
    public ResponseEntity<List<GrupoDespesaDto>> findAll() {
        List<GrupoDespesaDto> grupoDespesaDtoList = grupoDespesaService.findAll();
        return ResponseEntity.ok().body(grupoDespesaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> find(@PathVariable("id") Integer id) {
        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.findById(id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @PostMapping
    public ResponseEntity<GrupoDespesaDto> insert(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.insert(grupoDespesaForm);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> update(@Valid @RequestBody
        GrupoDespesaUpdateForm grupoDespesaUpdateForm, @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        
        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.updateById(grupoDespesaUpdateForm, id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        grupoDespesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
