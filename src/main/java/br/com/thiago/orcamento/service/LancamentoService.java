package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.LancamentoModel;
import br.com.thiago.orcamento.repository.LancamentoRepository;
import br.com.thiago.orcamento.rest.dto.LancamentoDto;
import br.com.thiago.orcamento.rest.form.LancamentoForm;
import br.com.thiago.orcamento.rest.form.LancamentoUpdateForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;
import br.com.thiago.orcamento.utils.Utils;

@Service
public class LancamentoService {
    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LancamentoDto findById(Integer id) {
        try {
            LancamentoModel lancamentoModel = lancamentoRepository.findById(id).get();
            return modelMapper.map(lancamentoModel, LancamentoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + LancamentoModel.class.getName());
        }
    }

    public List<LancamentoDto> findAll(){
        List<LancamentoModel> lancamentoList = lancamentoRepository.findAll();

        return lancamentoList.stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDto.class))
                .collect(Collectors.toList());
    }

    public LancamentoDto insert(LancamentoForm lancamentoForm) {
        try {
            LancamentoModel lancamentoNovo = modelMapper.map(lancamentoForm, LancamentoModel.class);

            lancamentoNovo = lancamentoRepository.save(lancamentoNovo);
            return modelMapper.map(lancamentoNovo, LancamentoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Lançamento não foi(foram) preenchido(s).");
        }
    }

    public LancamentoDto updateById(LancamentoUpdateForm lancamentoUpdateForm, Integer id) {
        try {
            Optional<LancamentoModel> lancamentoExistente = lancamentoRepository.findById(id);

            if (lancamentoExistente.isPresent()) {
                LancamentoModel lancamentoAtualizado = lancamentoExistente.get();

                Utils.copyNonNullProperties(lancamentoUpdateForm, lancamentoAtualizado);
                lancamentoAtualizado = lancamentoRepository.save(lancamentoAtualizado);

                return modelMapper.map(lancamentoAtualizado, LancamentoDto.class);

            }else{
                throw new DataIntegrityException("A Id do Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Lançamento não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (lancamentoRepository.existsById(id)) {
                lancamentoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Lançamento!");
        }
    }
}
