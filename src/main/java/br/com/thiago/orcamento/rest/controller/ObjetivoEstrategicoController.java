package br.com.thiago.orcamento.rest.controller;

import br.com.thiago.orcamento.rest.dto.ObjetivoEstrategicoDto;
import br.com.thiago.orcamento.rest.form.ObjetivoEstrategicoForm;
import br.com.thiago.orcamento.rest.form.ObjetivoEstrategicoUpdateForm;
import br.com.thiago.orcamento.service.ObjetivoEstrategicoService;

import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/objetivo-estrategico")
public class ObjetivoEstrategicoController {

    @Autowired
    ObjetivoEstrategicoService objetivoEstrategicoService;

    @GetMapping
    public ResponseEntity<List<ObjetivoEstrategicoDto>> findAll() {
        List<ObjetivoEstrategicoDto> objetivoEstrategicoDtoList = objetivoEstrategicoService.findAll();
        return ResponseEntity.ok().body(objetivoEstrategicoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> find(@PathVariable("id") Integer id) {
        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.findById(id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PostMapping
    public ResponseEntity<ObjetivoEstrategicoDto> insert(@Valid @RequestBody ObjetivoEstrategicoForm objetivoEstrategicoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        objetivoEstrategicoForm.setDataCadastro(LocalDateTime.now().minusHours(3));
        objetivoEstrategicoForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.insert(objetivoEstrategicoForm);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> update(@Valid @RequestBody
        ObjetivoEstrategicoUpdateForm objetivoEstrategicoUpdateForm, @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        objetivoEstrategicoUpdateForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.updateById(objetivoEstrategicoUpdateForm, id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        objetivoEstrategicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
