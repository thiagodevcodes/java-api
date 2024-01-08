package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.ElementoDespesaModel;
import br.com.thiago.orcamento.repository.ElementoDespesaRepository;
import br.com.thiago.orcamento.rest.dto.ElementoDespesaDto;
import br.com.thiago.orcamento.rest.form.ElementoDespesaForm;
import br.com.thiago.orcamento.service.exceptions.BusinessRuleException;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class ElementoDespesaService {
    @Autowired
    ElementoDespesaRepository elementoDespesaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ElementoDespesaDto findById(Integer id) {
        try {
            ElementoDespesaModel elementoDespesaModel = elementoDespesaRepository.findById(id).get();
            return modelMapper.map(elementoDespesaModel, ElementoDespesaDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ElementoDespesaModel.class.getName());
        }
    }

    public List<ElementoDespesaDto> findAllData() {
        try {
            List<ElementoDespesaModel> elementoDespesaList = elementoDespesaRepository.findAll();

            return elementoDespesaList.stream()
                    .map(elementoDespesa -> modelMapper.map(elementoDespesa, ElementoDespesaDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Ações!", e.getErrorMessages());
        }
    }

    public Page<ElementoDespesaDto> findAll(Pageable pageable){
        Page<ElementoDespesaModel> elementoDespesaPage = elementoDespesaRepository.findAll(pageable);
        return elementoDespesaPage.map(elementoDespesa -> modelMapper.map(elementoDespesa, ElementoDespesaDto.class));
    }

    public ElementoDespesaDto insert(ElementoDespesaForm elementoDespesaForm) {
        try {
            ElementoDespesaModel elementoDespesaNovo = modelMapper.map(elementoDespesaForm, ElementoDespesaModel.class);

            Optional<ElementoDespesaModel> byNome = elementoDespesaRepository.findByNome(elementoDespesaNovo.getNome());
            Optional<ElementoDespesaModel> byCodigo = elementoDespesaRepository.findByCodigo(elementoDespesaForm.getCodigo());
            
            if (byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Elemento Despesa já registrado.");
            }

            elementoDespesaNovo = elementoDespesaRepository.save(elementoDespesaNovo);
            return modelMapper.map(elementoDespesaNovo, ElementoDespesaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento Despesa não foi(foram) preenchido(s).");
        }
    }

     public ElementoDespesaDto updateById(ElementoDespesaForm elementoDespesaForm, Integer id) {
        try {
            Optional<ElementoDespesaModel> elementoDespesaExistente = elementoDespesaRepository.findById(id);

            if (elementoDespesaExistente.isPresent()) {
                ElementoDespesaModel elementoDespesaAtualizado = elementoDespesaExistente.get();

                modelMapper.map(elementoDespesaForm, elementoDespesaAtualizado);
                elementoDespesaAtualizado = elementoDespesaRepository.save(elementoDespesaAtualizado);

                return modelMapper.map(elementoDespesaAtualizado, ElementoDespesaDto.class);

            }else{
                throw new DataIntegrityException("O Id do Elemento Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento Despesa não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (elementoDespesaRepository.existsById(id)) {
                elementoDespesaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id do Elemento Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Elemento Despesa!");
        }
    }
}
