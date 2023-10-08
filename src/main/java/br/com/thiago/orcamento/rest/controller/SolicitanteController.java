package br.com.thiago.orcamento.rest.controller;

import br.com.thiago.orcamento.rest.dto.SolicitanteDto;
import br.com.thiago.orcamento.rest.form.SolicitanteForm;
import br.com.thiago.orcamento.rest.form.SolicitanteUpdateForm;
import br.com.thiago.orcamento.service.SolicitanteService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {

    @Autowired
    SolicitanteService solicitanteService;

    @GetMapping
    public ResponseEntity<List<SolicitanteDto>> findAll() {
        List<SolicitanteDto> solicitanteDtoList = solicitanteService.findAll();
        return ResponseEntity.ok().body(solicitanteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitanteDto> find(@PathVariable("id") Integer id) {
        SolicitanteDto unidadeDto = solicitanteService.findById(id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @PostMapping
    public ResponseEntity<SolicitanteDto> insert(@Valid @RequestBody SolicitanteForm solicitanteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        solicitanteForm.setDataCadastro(LocalDateTime.now().minusHours(3));
        solicitanteForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        SolicitanteDto solicitanteDto = solicitanteService.insert(solicitanteForm);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitanteDto> update(@Valid @RequestBody SolicitanteUpdateForm solicitanteUpdateForm
            , @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        solicitanteUpdateForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        SolicitanteDto solicitanteDto = solicitanteService.updateById(solicitanteUpdateForm, id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        solicitanteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
