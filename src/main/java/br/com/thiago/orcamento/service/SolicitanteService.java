package br.com.thiago.orcamento.service;

import br.com.thiago.orcamento.model.SolicitanteModel;
import br.com.thiago.orcamento.repository.SolicitanteRepository;
import br.com.thiago.orcamento.rest.dto.SolicitanteDto;
import br.com.thiago.orcamento.rest.form.SolicitanteForm;
import br.com.thiago.orcamento.rest.form.SolicitanteUpdateForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<SolicitanteDto> findAll(){
        List<SolicitanteModel> solicitanteList = solicitanteRepository.findAll();

        return solicitanteList.stream()
                .map(solicitante -> modelMapper.map(solicitante, SolicitanteDto.class))
                .collect(Collectors.toList());
    }

    public SolicitanteDto insert(SolicitanteForm solicitanteForm) {
        try {
            SolicitanteModel SolicitanteNovo = modelMapper.map(solicitanteForm, SolicitanteModel.class);

            Optional<SolicitanteModel> byNome = solicitanteRepository.findByNome(SolicitanteNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Solicitante já registrado.");
            }
            SolicitanteNovo = solicitanteRepository.save(SolicitanteNovo);
            return modelMapper.map(SolicitanteNovo, SolicitanteDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Solicitante não foi(foram) preenchido(s).");
        }
    }

    public SolicitanteDto updateById(SolicitanteUpdateForm solicitanteUpdateForm, Integer id) {
        try {
            Optional<SolicitanteModel> solicitanteExistente = solicitanteRepository.findById(id);

            if (solicitanteExistente.isPresent()) {
                SolicitanteModel solicitanteAtualizado = solicitanteExistente.get();
                solicitanteAtualizado.setNome(solicitanteUpdateForm.getNome());
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
