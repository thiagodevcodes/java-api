package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.UnidadeOrcamentariaModel;
import br.com.thiago.orcamento.repository.UnidadeOrcamentariaRepository;
import br.com.thiago.orcamento.rest.dto.UnidadeOrcamentariaDto;
import br.com.thiago.orcamento.rest.form.UnidadeOrcamentariaForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class UnidadeOrcamentariaService {
    @Autowired
    UnidadeOrcamentariaRepository unidadeOrcamentariaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UnidadeOrcamentariaDto findById(Integer id) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaModel = unidadeOrcamentariaRepository.findById(id).get();
            return modelMapper.map(unidadeOrcamentariaModel, UnidadeOrcamentariaDto.class);
        } catch(NoSuchElementException e) {
            throw new ObjectNotFoundException("Objecto não encontrado! Id: " + id + ", Tipo: " + UnidadeOrcamentariaModel.class.getName());
        }
    }

    public List<UnidadeOrcamentariaDto> findAll() {
        List<UnidadeOrcamentariaModel> unidadeOrcamentariaList = unidadeOrcamentariaRepository.findAll();
        
        return unidadeOrcamentariaList.stream()
            .map(unidadeOrcamentaria -> modelMapper.map(unidadeOrcamentaria, UnidadeOrcamentariaDto.class))
            .collect(Collectors.toList());
    }

    public UnidadeOrcamentariaDto insert(UnidadeOrcamentariaForm unidadeOrcamentariaForm) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaNovo = modelMapper.map(unidadeOrcamentariaForm, UnidadeOrcamentariaModel.class);
            
            Optional<UnidadeOrcamentariaModel> byNome = unidadeOrcamentariaRepository.findByNome(unidadeOrcamentariaNovo.getNome());
            Optional<UnidadeOrcamentariaModel> byCodigo = unidadeOrcamentariaRepository.findByCodigo(unidadeOrcamentariaNovo.getCodigo());
        
            if(byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Unidade Orçamentaria já registrado.");
            }

            unidadeOrcamentariaNovo = unidadeOrcamentariaRepository.save(unidadeOrcamentariaNovo);
            return modelMapper.map(unidadeOrcamentariaNovo, UnidadeOrcamentariaDto.class);
        
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Unidade Orçamentaria não foi(foram) preenchido(s).");
        }
    }

    public UnidadeOrcamentariaDto updateById(UnidadeOrcamentariaForm unidadeOrcamentariaForm, Integer id) {
        try {
            Optional<UnidadeOrcamentariaModel> unidadeOrcamentariaExistente = unidadeOrcamentariaRepository.findById(id);

            if(unidadeOrcamentariaExistente.isPresent()) {
                UnidadeOrcamentariaModel unidadeOrcamentariaAtualizado = unidadeOrcamentariaExistente.get();
                
                modelMapper.map(unidadeOrcamentariaForm, unidadeOrcamentariaAtualizado);
                unidadeOrcamentariaAtualizado = unidadeOrcamentariaRepository.save(unidadeOrcamentariaAtualizado);
                
                return modelMapper.map(unidadeOrcamentariaAtualizado, UnidadeOrcamentariaDto.class);
            } else {
                throw new DataIntegrityException("O Id do Unidade Orçamentaria não existe na base de dados!");
            }

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Unidade Orçamentaria não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if(unidadeOrcamentariaRepository.existsById(id)) {
                unidadeOrcamentariaRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Unidade Orçamentaria!");
        }
    }
}
