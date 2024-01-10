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

import br.com.thiago.orcamento.model.ObjetivoEstrategicoModel;
import br.com.thiago.orcamento.repository.ObjetivoEstrategicoRepository;
import br.com.thiago.orcamento.rest.dto.ObjetivoEstrategicoDto;
import br.com.thiago.orcamento.rest.form.ObjetivoEstrategicoForm;
import br.com.thiago.orcamento.service.exceptions.BusinessRuleException;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class ObjetivoEstrategicoService {
    @Autowired
    ObjetivoEstrategicoRepository objetivoEstrategicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ObjetivoEstrategicoDto findById(Integer id) {
        try {
            ObjetivoEstrategicoModel objetivoEstrategicoModel = objetivoEstrategicoRepository.findById(id).get();
            return modelMapper.map(objetivoEstrategicoModel, ObjetivoEstrategicoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ObjetivoEstrategicoModel.class.getName());
        }
    }

    public List<ObjetivoEstrategicoDto> findAllData() {
        try {
            List<ObjetivoEstrategicoModel> objetivoEstrategicoDtoList = objetivoEstrategicoRepository.findAll();

            return objetivoEstrategicoDtoList.stream()
                    .map(objetivoEstrategicoDto -> modelMapper.map(objetivoEstrategicoDto, ObjetivoEstrategicoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Ações!", e.getErrorMessages());
        }
    }

    public Page<ObjetivoEstrategicoDto> findAll(Pageable pageable){
        Page<ObjetivoEstrategicoModel> objetivoEstrategicoPage = objetivoEstrategicoRepository.findAll(pageable);
        return objetivoEstrategicoPage.map(objetivoEstrategico -> modelMapper.map(objetivoEstrategico, ObjetivoEstrategicoDto.class));
    }

    public ObjetivoEstrategicoDto insert(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
        try {
            ObjetivoEstrategicoModel objetivoEstrategicoNovo = modelMapper.map(objetivoEstrategicoForm, ObjetivoEstrategicoModel.class);

            Optional<ObjetivoEstrategicoModel> byNome = objetivoEstrategicoRepository.findByNome(objetivoEstrategicoNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Objetivo Estratégico já registrado.");
            }
            objetivoEstrategicoNovo = objetivoEstrategicoRepository.save(objetivoEstrategicoNovo);
            return modelMapper.map(objetivoEstrategicoNovo, ObjetivoEstrategicoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estratégico não foi(foram) preenchido(s).");
        }
    }

    public ObjetivoEstrategicoDto updateById(ObjetivoEstrategicoForm objetivoEstrategicoForm, Integer id) {
        try {
            Optional<ObjetivoEstrategicoModel> objetivoEstrategicoExistente = objetivoEstrategicoRepository.findById(id);

            if (objetivoEstrategicoExistente.isPresent()) {
                ObjetivoEstrategicoModel objetivoEstrategicoAtualizado = objetivoEstrategicoExistente.get();
                
                modelMapper.map(objetivoEstrategicoForm, objetivoEstrategicoAtualizado);
                objetivoEstrategicoAtualizado = objetivoEstrategicoRepository.save(objetivoEstrategicoAtualizado);

                return modelMapper.map(objetivoEstrategicoAtualizado, ObjetivoEstrategicoDto.class);

            }else{
                throw new DataIntegrityException("O Id do Objeto Estratégico não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estratégico não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (objetivoEstrategicoRepository.existsById(id)) {
                objetivoEstrategicoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id do Objetivo Estratégico não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o Objetivo Estratégico!");
        }
    }
}
