package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.UnidadeModel;
import br.com.thiago.orcamento.repository.UnidadeRepository;
import br.com.thiago.orcamento.rest.dto.UnidadeDto;
import br.com.thiago.orcamento.rest.form.UnidadeForm;
import br.com.thiago.orcamento.rest.form.UnidadeUpdateForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class UnidadeService {

    @Autowired
    UnidadeRepository unidadeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UnidadeDto findById(Integer id) {
        try {
            UnidadeModel unidadeModel = unidadeRepository.findById(id).get();
            return modelMapper.map(unidadeModel, UnidadeDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + UnidadeModel.class.getName());
        }
    }

    public List<UnidadeDto> findAll(){
        List<UnidadeModel> unidadeList = unidadeRepository.findAll();

        return unidadeList.stream()
                .map(unidade -> modelMapper.map(unidade, UnidadeDto.class))
                .collect(Collectors.toList());
    }

    public UnidadeDto insert(UnidadeForm unidadeForm) {
        try {
            UnidadeModel UnidadeNovo = modelMapper.map(unidadeForm, UnidadeModel.class);

            Optional<UnidadeModel> byNome = unidadeRepository.findByNome(UnidadeNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Unidade já registrada.");
            }
            
            UnidadeNovo = unidadeRepository.save(UnidadeNovo);
            return modelMapper.map(UnidadeNovo, UnidadeDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) de Unidade não foi(foram) preenchido(s).");
        }
    }

    public UnidadeDto updateById(UnidadeUpdateForm unidadeUpdateForm, Integer id) {
        try {
            Optional<UnidadeModel> unidadeExistente = unidadeRepository.findById(id);

            if (unidadeExistente.isPresent()) {
                UnidadeModel unidadeAtualizado = unidadeExistente.get();
                unidadeAtualizado.setNome(unidadeUpdateForm.getNome());
                unidadeAtualizado = unidadeRepository.save(unidadeAtualizado);

                return modelMapper.map(unidadeAtualizado, UnidadeDto.class);

            }else{
                throw new DataIntegrityException("O Id da Unidade não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (unidadeRepository.existsById(id)) {
                unidadeRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id da Unidade não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Unidade!");
        }
    }
}
