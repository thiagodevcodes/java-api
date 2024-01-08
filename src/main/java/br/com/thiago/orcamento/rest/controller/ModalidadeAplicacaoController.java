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

import br.com.thiago.orcamento.rest.dto.ModalidadeAplicacaoDto;
import br.com.thiago.orcamento.rest.form.ModalidadeAplicacaoForm;
import br.com.thiago.orcamento.service.ModalidadeAplicacaoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/modalidade-aplicacao")
public class ModalidadeAplicacaoController {
    @Autowired
    ModalidadeAplicacaoService modalidadeAplicacaoService;

    @GetMapping
    public ResponseEntity<Page<ModalidadeAplicacaoDto>> findAll(Pageable page) {
        Page<ModalidadeAplicacaoDto> modalidadeAplicacaoDtoPage = modalidadeAplicacaoService.findAll(page);
        return ResponseEntity.ok().body(modalidadeAplicacaoDtoPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModalidadeAplicacaoDto>> findAllData() {
        List<ModalidadeAplicacaoDto> modalidadeAplicacaoDtoList = modalidadeAplicacaoService.findAllData();
        return ResponseEntity.ok().body(modalidadeAplicacaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeAplicacaoDto> find(@PathVariable("id") Integer id) {
        ModalidadeAplicacaoDto modalidadeAplicacaoDto = modalidadeAplicacaoService.findById(id);
        return ResponseEntity.ok().body(modalidadeAplicacaoDto);
    }

    @PostMapping
    public ResponseEntity<ModalidadeAplicacaoDto> insert(@Valid @RequestBody ModalidadeAplicacaoForm modalidadeAplicacaoForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        ModalidadeAplicacaoDto modalidadeAplicacaoDto = modalidadeAplicacaoService.insert(modalidadeAplicacaoForm);
        return ResponseEntity.ok().body(modalidadeAplicacaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeAplicacaoDto> update(@Valid @RequestBody
        ModalidadeAplicacaoForm modalidadeAplicacaoForm, @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }
        
        ModalidadeAplicacaoDto modalidadeAplicacaoDto = modalidadeAplicacaoService.updateById(modalidadeAplicacaoForm, id);
        return ResponseEntity.ok().body(modalidadeAplicacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        modalidadeAplicacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
