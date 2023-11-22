package br.com.thiago.orcamento.service;

import java.util.NoSuchElementException;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.SolicitanteModel;
import br.com.thiago.orcamento.repository.SolicitanteRepository;
import br.com.thiago.orcamento.rest.dto.SolicitanteDto;
import br.com.thiago.orcamento.rest.form.SolicitanteForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class SolicitanteService {
    @Autowired
    SolicitanteRepository solicitanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SolicitanteDto findById(Integer id) {
        try {
            SolicitanteModel solicitanteModel = solicitanteRepository.findById(id).get();
            return modelMapper.map(solicitanteModel, SolicitanteDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + SolicitanteModel.class.getName());
        }
    }


    public Page<SolicitanteDto> findAll(Pageable pageable){
        Page<SolicitanteModel> solicitantePage = solicitanteRepository.findAll(pageable);
        return solicitantePage.map(solicitante -> modelMapper.map(solicitante, SolicitanteDto.class));       
    }

    public SolicitanteDto insert(SolicitanteForm solicitanteForm) {
        try {
            SolicitanteModel solicitanteNovo = modelMapper.map(solicitanteForm, SolicitanteModel.class);

            Optional<SolicitanteModel> byNome = solicitanteRepository.findByNome(solicitanteNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Solicitante já registrado.");
            }
            solicitanteNovo = solicitanteRepository.save(solicitanteNovo);
            return modelMapper.map(solicitanteNovo, SolicitanteDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Solicitante não foi(foram) preenchido(s).");
        }
    }

    public SolicitanteDto updateById(SolicitanteForm solicitanteForm, Integer id) {
        try {
            Optional<SolicitanteModel> solicitanteExistente = solicitanteRepository.findById(id);

            if (solicitanteExistente.isPresent()) {
                SolicitanteModel solicitanteAtualizado = solicitanteExistente.get();

                modelMapper.map(solicitanteForm, solicitanteAtualizado);
                solicitanteAtualizado = solicitanteRepository.save(solicitanteAtualizado);

                return modelMapper.map(solicitanteAtualizado, SolicitanteDto.class);

            }else{
                throw new DataIntegrityException("O Id da Solicitante não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Solicitante não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (solicitanteRepository.existsById(id)) {
                solicitanteRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id da Solicitante não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Solicitante!");
        }
    }
}
