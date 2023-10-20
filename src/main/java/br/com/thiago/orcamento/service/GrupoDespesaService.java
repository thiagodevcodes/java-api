package br.com.thiago.orcamento.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.GrupoDespesaModel;
import br.com.thiago.orcamento.repository.GrupoDespesaRepository;
import br.com.thiago.orcamento.rest.dto.GrupoDespesaDto;
import br.com.thiago.orcamento.rest.form.GrupoDespesaForm;
import br.com.thiago.orcamento.rest.form.GrupoDespesaUpdateForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class GrupoDespesaService {
    @Autowired
    GrupoDespesaRepository grupoDespesaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDespesaDto findById(Integer id) {
        try {
            GrupoDespesaModel grupoDespesaModel = grupoDespesaRepository.findById(id).get();
            return modelMapper.map(grupoDespesaModel, GrupoDespesaDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + GrupoDespesaModel.class.getName());
        }
    }

    public List<GrupoDespesaDto> findAll(){
        List<GrupoDespesaModel> grupoDespesaList = grupoDespesaRepository.findAll();

        return grupoDespesaList.stream()
                .map(grupoDespesa -> modelMapper.map(grupoDespesa, GrupoDespesaDto.class))
                .collect(Collectors.toList());
    }

    public GrupoDespesaDto insert(GrupoDespesaForm grupoDespesaForm) {
        try {
            GrupoDespesaModel GrupoDespesaNovo = modelMapper.map(grupoDespesaForm, GrupoDespesaModel.class);

            Optional<GrupoDespesaModel> byNome = grupoDespesaRepository.findByNome(GrupoDespesaNovo.getNome());
            Optional<GrupoDespesaModel> byCodigo = grupoDespesaRepository.findByCodigo(grupoDespesaForm.getCodigo());

            if (byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Grupo Despesa já registrado.");
            }

            GrupoDespesaNovo = grupoDespesaRepository.save(GrupoDespesaNovo);
            return modelMapper.map(GrupoDespesaNovo, GrupoDespesaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Grupo Despesa não foi(foram) preenchido(s).");
        }
    }

     public GrupoDespesaDto updateById(GrupoDespesaUpdateForm grupoDespesaUpdateForm, Integer id) {
        try {
            Optional<GrupoDespesaModel> grupoDespesaExistente = grupoDespesaRepository.findById(id);

            if (grupoDespesaExistente.isPresent()) {
                GrupoDespesaModel grupoDespesaAtualizado = grupoDespesaExistente.get();
                grupoDespesaAtualizado.setNome(grupoDespesaUpdateForm.getNome());
                grupoDespesaAtualizado.setCodigo(grupoDespesaUpdateForm.getCodigo());
                grupoDespesaAtualizado = grupoDespesaRepository.save(grupoDespesaAtualizado);

                return modelMapper.map(grupoDespesaAtualizado, GrupoDespesaDto.class);

            }else{
                throw new DataIntegrityException("O Id do Grupo Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Grupo Despesa não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (grupoDespesaRepository.existsById(id)) {
                grupoDespesaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id do Grupo Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Grupo Despesa!");
        }
    }
}
