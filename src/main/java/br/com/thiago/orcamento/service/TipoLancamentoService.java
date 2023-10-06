package br.com.thiago.orcamento.service;

import br.com.thiago.orcamento.model.TipoLancamentoModel;
import br.com.thiago.orcamento.repository.TipoLancamentoRepository;
import br.com.thiago.orcamento.rest.form.TipoLancamentoForm;
import br.com.thiago.orcamento.rest.form.TipoLancamentoUpdateForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.thiago.orcamento.rest.dto.TipoLancamentoDto;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoLancamentoService {
    TipoLancamentoRepository tipoLancamentoRepository;
    private final ModelMapper modelMapper;
    public TipoLancamentoService(TipoLancamentoRepository tipoLancamentoRepository, ModelMapper modelMapper) {
        this.tipoLancamentoRepository = tipoLancamentoRepository;
        this.modelMapper = modelMapper;
    }

    public TipoLancamentoDto findById(Integer id) {
        try {
            TipoLancamentoModel tipoLancamentoModel = tipoLancamentoRepository.findById(id).get();
            return modelMapper.map(tipoLancamentoModel, TipoLancamentoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TipoLancamentoModel.class.getName());
        }
    }

    public List<TipoLancamentoDto> findAll(){
        List<TipoLancamentoModel> alunoList = tipoLancamentoRepository.findAll();

        return alunoList.stream()
                .map(aluno -> modelMapper.map(aluno, TipoLancamentoDto.class))
                .collect(Collectors.toList());
    }

    public TipoLancamentoDto insert(TipoLancamentoForm tipoLancamentoForm) {
        try {
            TipoLancamentoModel TipoLancamentoNovo = modelMapper.map(tipoLancamentoForm, TipoLancamentoModel.class);

            Optional<TipoLancamentoModel> byNome = tipoLancamentoRepository.findByNome(TipoLancamentoNovo.getNome());

            if (byNome.isPresent()) {
                throw new IllegalStateException("Tipo de Lançamento já registrado.");
            }
            TipoLancamentoNovo = tipoLancamentoRepository.save(TipoLancamentoNovo);
            return modelMapper.map(TipoLancamentoNovo, TipoLancamentoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Lançamento não foi(foram) preenchido(s).");
        }
    }

    public TipoLancamentoDto updateById(TipoLancamentoUpdateForm alunoUpdateForm, Integer id) {
        try {
            Optional<TipoLancamentoModel> tipoLancamentoExistente = tipoLancamentoRepository.findById(id);

            if (tipoLancamentoExistente.isPresent()) {
                TipoLancamentoModel tipoLancamentoAtualizado = tipoLancamentoExistente.get();
                tipoLancamentoAtualizado.setNome(alunoUpdateForm.getNome());
                tipoLancamentoAtualizado = tipoLancamentoRepository.save(tipoLancamentoAtualizado);

                return modelMapper.map(tipoLancamentoAtualizado, TipoLancamentoDto.class);

            }else{
                throw new DataIntegrityException("A Id do Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Lançamento não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (tipoLancamentoRepository.existsById(id)) {
                tipoLancamentoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Lançamento!");
        }
    }
}
