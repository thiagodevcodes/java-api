package br.com.thiago.orcamento.rest.controller;

import br.com.thiago.orcamento.rest.dto.UnidadeDto;
import br.com.thiago.orcamento.rest.form.UnidadeForm;
import br.com.thiago.orcamento.rest.form.UnidadeUpdateForm;
import br.com.thiago.orcamento.service.UnidadeService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<UnidadeDto>> findAll() {
        List<UnidadeDto> unidadeDtoList = unidadeService.findAll();
        return ResponseEntity.ok().body(unidadeDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDto> find(@PathVariable("id") Integer id) {
        UnidadeDto unidadeDto = unidadeService.findById(id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeDto> insert(@Valid @RequestBody UnidadeForm unidadeForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        unidadeForm.setDataCadastro(LocalDateTime.now().minusHours(3));
        unidadeForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        UnidadeDto unidadeDto = unidadeService.insert(unidadeForm);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDto> update(@Valid @RequestBody UnidadeUpdateForm unidadeUpdateForm
            , @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        unidadeUpdateForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        UnidadeDto unidadeDto = unidadeService.updateById(unidadeUpdateForm, id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
