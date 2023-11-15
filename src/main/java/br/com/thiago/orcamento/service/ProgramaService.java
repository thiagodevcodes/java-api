package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.ProgramaModel;
import br.com.thiago.orcamento.repository.ProgramaRepository;
import br.com.thiago.orcamento.rest.dto.ProgramaDto;
import br.com.thiago.orcamento.rest.form.ProgramaForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class ProgramaService {
    @Autowired
    ProgramaRepository programaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProgramaDto findById(Integer id) {
        try {
            ProgramaModel programaModel = programaRepository.findById(id).get();
            return modelMapper.map(programaModel, ProgramaDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ProgramaModel.class.getName());
        }
    }

    public List<ProgramaDto> findAll(){
        List<ProgramaModel> programaList = programaRepository.findAll();

        return programaList.stream()
                .map(acao -> modelMapper.map(acao, ProgramaDto.class))
                .collect(Collectors.toList());
    }

    public ProgramaDto insert(ProgramaForm programaForm) {
        try {
            ProgramaModel programaNovo = modelMapper.map(programaForm, ProgramaModel.class);

            Optional<ProgramaModel> byNome = programaRepository.findByNome(programaNovo.getNome());
            Optional<ProgramaModel> byCodigo = programaRepository.findByCodigo(programaForm.getCodigo());
            
            if (byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Programa já registrado.");
            }

            programaNovo = programaRepository.save(programaNovo);
            return modelMapper.map(programaNovo, ProgramaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Programa não foi(foram) preenchido(s).");
        }
    }

     public ProgramaDto updateById(ProgramaForm programaForm, Integer id) {
        try {
            Optional<ProgramaModel> programaExistente = programaRepository.findById(id);

            if (programaExistente.isPresent()) {
                ProgramaModel programaAtualizado = programaExistente.get();

                modelMapper.map(programaForm, programaAtualizado);
                programaAtualizado = programaRepository.save(programaAtualizado);

                return modelMapper.map(programaAtualizado, ProgramaDto.class);

            }else{
                throw new DataIntegrityException("O Id do Programa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Programa não foi(foram) preenchido(s) ou Informações já existem.");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (programaRepository.existsById(id)) {
                programaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id do Programa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Programa!");
        }
    }
}
