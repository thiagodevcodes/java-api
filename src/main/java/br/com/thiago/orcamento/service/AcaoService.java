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

import br.com.thiago.orcamento.model.AcaoModel;

import br.com.thiago.orcamento.repository.AcaoRepository;
import br.com.thiago.orcamento.rest.dto.AcaoDto;

import br.com.thiago.orcamento.rest.form.AcaoForm;
import br.com.thiago.orcamento.service.exceptions.BusinessRuleException;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class AcaoService {
    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AcaoDto findById(Integer id) {
        try {
            AcaoModel acaoModel = acaoRepository.findById(id).get();
            return modelMapper.map(acaoModel, AcaoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + AcaoModel.class.getName());
        }
    }

    public List<AcaoDto> findAllData() {
        try {
            List<AcaoModel> acaoModelList = acaoRepository.findAll();

            return acaoModelList.stream()
                    .map(acao -> modelMapper.map(acao, AcaoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Ações!", e.getErrorMessages());
        }
    }

    public Page<AcaoDto> findAll(Pageable pageable){
        Page<AcaoModel> acaoPage = acaoRepository.findAll(pageable);
        return acaoPage.map(acao -> modelMapper.map(acao, AcaoDto.class));
    }

    public AcaoDto insert(AcaoForm acaoForm) {
        try {
            AcaoModel acaoNovo = modelMapper.map(acaoForm, AcaoModel.class);
           
            Optional<AcaoModel> byNome = acaoRepository.findByNome(acaoNovo.getNome());
            Optional<AcaoModel> byCodigo = acaoRepository.findByCodigo(acaoForm.getCodigo());
            
            if (byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Ação já registrada.");
            }

            acaoNovo = acaoRepository.save(acaoNovo);
            return modelMapper.map(acaoNovo, AcaoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Ação não foi(foram) preenchido(s).");
        }
    }

    public AcaoDto updateById(AcaoForm acaoForm, Integer id) {
        try {
            Optional<AcaoModel> acaoExistente = acaoRepository.findById(id);

            if (acaoExistente.isPresent()) {
                AcaoModel acaoAtualizado = acaoExistente.get();

                modelMapper.map(acaoForm, acaoAtualizado);
                acaoAtualizado = acaoRepository.save(acaoAtualizado);

                return modelMapper.map(acaoAtualizado, AcaoDto.class);

            }else{
                throw new DataIntegrityException("O Id do Ação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Ação não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (acaoRepository.existsById(id)) {
                acaoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id do Ação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Ação!");
        }
    }
}
