package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.ElementoDespesaModel;
import br.com.thiago.orcamento.repository.ElementoDespesaRepository;
import br.com.thiago.orcamento.rest.dto.ElementoDespesaDto;
import br.com.thiago.orcamento.rest.form.ElementoDespesaForm;
import br.com.thiago.orcamento.rest.form.ElementoDespesaUpdateForm;
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

        public List<ElementoDespesaDto> findAll(){
        List<ElementoDespesaModel> elementoDespesaList = elementoDespesaRepository.findAll();

        return elementoDespesaList.stream()
                .map(elementoDespesa -> modelMapper.map(elementoDespesa, ElementoDespesaDto.class))
                .collect(Collectors.toList());
    }

    public ElementoDespesaDto insert(ElementoDespesaForm elementoDespesaForm) {
        try {
            ElementoDespesaModel ElementoDespesaNovo = modelMapper.map(elementoDespesaForm, ElementoDespesaModel.class);

            Optional<ElementoDespesaModel> byNome = elementoDespesaRepository.findByNome(ElementoDespesaNovo.getNome());
            Optional<ElementoDespesaModel> byCodigo = elementoDespesaRepository.findByCodigo(elementoDespesaForm.getCodigo());
            
            if (byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Elemento Despesa já registrado.");
            }

            ElementoDespesaNovo = elementoDespesaRepository.save(ElementoDespesaNovo);
            return modelMapper.map(ElementoDespesaNovo, ElementoDespesaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento Despesa não foi(foram) preenchido(s).");
        }
    }

     public ElementoDespesaDto updateById(ElementoDespesaUpdateForm elementoDespesaUpdateForm, Integer id) {
        try {
            Optional<ElementoDespesaModel> elementoDespesaExistente = elementoDespesaRepository.findById(id);

            if (elementoDespesaExistente.isPresent()) {
                ElementoDespesaModel elementoDespesaAtualizado = elementoDespesaExistente.get();
                elementoDespesaAtualizado.setNome(elementoDespesaUpdateForm.getNome());
                elementoDespesaAtualizado.setCodigo(elementoDespesaUpdateForm.getCodigo());
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
