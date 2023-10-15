package br.com.thiago.orcamento.rest.controller;

import br.com.thiago.orcamento.rest.dto.TipoTransacaoDto;
import br.com.thiago.orcamento.rest.form.TipoTransacaoForm;
import br.com.thiago.orcamento.rest.form.TipoTransacaoUpdateForm;
import br.com.thiago.orcamento.service.TipoTransacaoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tipo-transacao")
public class TipoTransacaoController {

    @Autowired
    TipoTransacaoService tipoTransacaoService;

    @GetMapping
    public ResponseEntity<List<TipoTransacaoDto>> findAll() {
        List<TipoTransacaoDto> tipoTransacaoDtoList = tipoTransacaoService.findAll();
        return ResponseEntity.ok().body(tipoTransacaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> find(@PathVariable("id") Integer id) {
        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.findById(id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @PostMapping
    public ResponseEntity<TipoTransacaoDto> insert(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.insert(tipoTransacaoForm);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> update(@Valid @RequestBody TipoTransacaoUpdateForm tipoTransacaoUpdateForm
            , @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.updateById(tipoTransacaoUpdateForm, id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoTransacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
