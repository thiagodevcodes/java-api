package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.ObjetivoEstrategicoModel;
import br.com.thiago.orcamento.repository.ObjetivoEstrategicoRepository;
import br.com.thiago.orcamento.rest.dto.ObjetivoEstrategicoDto;
import br.com.thiago.orcamento.rest.form.ObjetivoEstrategicoForm;
import br.com.thiago.orcamento.rest.form.ObjetivoEstrategicoUpdateForm;
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

    public List<ObjetivoEstrategicoDto> findAll(){
        List<ObjetivoEstrategicoModel> objetivoEstrategicoList = objetivoEstrategicoRepository.findAll();

        return objetivoEstrategicoList.stream()
                .map(objetivoEstrategico -> modelMapper.map(objetivoEstrategico, ObjetivoEstrategicoDto.class))
                .collect(Collectors.toList());
    }

    public ObjetivoEstrategicoDto insert(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
        try {
            ObjetivoEstrategicoModel ObjetivoEstrategicoNovo = modelMapper.map(objetivoEstrategicoForm, ObjetivoEstrategicoModel.class);

            Optional<ObjetivoEstrategicoModel> byNome = objetivoEstrategicoRepository.findByNome(ObjetivoEstrategicoNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Objetivo Estratégico já registrado.");
            }
            ObjetivoEstrategicoNovo = objetivoEstrategicoRepository.save(ObjetivoEstrategicoNovo);
            return modelMapper.map(ObjetivoEstrategicoNovo, ObjetivoEstrategicoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estratégico não foi(foram) preenchido(s).");
        }
    }

    public ObjetivoEstrategicoDto updateById(ObjetivoEstrategicoUpdateForm objetivoEstrategicoUpdateForm, Integer id) {
        try {
            Optional<ObjetivoEstrategicoModel> objetivoEstrategicoExistente = objetivoEstrategicoRepository.findById(id);

            if (objetivoEstrategicoExistente.isPresent()) {
                ObjetivoEstrategicoModel objetivoEstrategicoAtualizado = objetivoEstrategicoExistente.get();
                objetivoEstrategicoAtualizado.setNome(objetivoEstrategicoUpdateForm.getNome());
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
            throw new DataIntegrityException("Não é possível excluir um Objetivo Estratégico!");
        }
    }
}
