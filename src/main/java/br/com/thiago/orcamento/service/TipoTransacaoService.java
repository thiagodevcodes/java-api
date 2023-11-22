package br.com.thiago.orcamento.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.thiago.orcamento.model.TipoTransacaoModel;
import br.com.thiago.orcamento.repository.TipoTransacaoRepository;
import br.com.thiago.orcamento.rest.dto.TipoTransacaoDto;
import br.com.thiago.orcamento.rest.form.TipoTransacaoForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class TipoTransacaoService {
    @Autowired
    TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TipoTransacaoDto findById(Integer id) {
        try {
            TipoTransacaoModel tipoTransacaoModel = tipoTransacaoRepository.findById(id).get();
            return modelMapper.map(tipoTransacaoModel, TipoTransacaoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TipoTransacaoModel.class.getName());
        }
    }

    public Page<TipoTransacaoDto> findAll(Pageable pageable){
        Page<TipoTransacaoModel> tipoTransacaoPage = tipoTransacaoRepository.findAll(pageable);
        return tipoTransacaoPage.map(tipoTransacao -> modelMapper.map(tipoTransacao, TipoTransacaoDto.class));
    }

    public TipoTransacaoDto insert(TipoTransacaoForm tipoTransacaoForm) {
        try {
            TipoTransacaoModel tipoTransacaoNovo = modelMapper.map(tipoTransacaoForm, TipoTransacaoModel.class);

            Optional<TipoTransacaoModel> byNome = tipoTransacaoRepository.findByNome(tipoTransacaoNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Tipo de Transação já registrado.");
            }
            tipoTransacaoNovo = tipoTransacaoRepository.save(tipoTransacaoNovo);
            return modelMapper.map(tipoTransacaoNovo, TipoTransacaoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Transação não foi(foram) preenchido(s).");
        }
    }

    public TipoTransacaoDto updateById(TipoTransacaoForm tipoTransacaoForm, Integer id) {
        try {
            Optional<TipoTransacaoModel> tipoTransacaoExistente = tipoTransacaoRepository.findById(id);

            if (tipoTransacaoExistente.isPresent()) {
                TipoTransacaoModel tipoTransacaoAtualizado = tipoTransacaoExistente.get();
                
                modelMapper.map(tipoTransacaoForm, tipoTransacaoAtualizado);
                tipoTransacaoAtualizado = tipoTransacaoRepository.save(tipoTransacaoAtualizado);

                return modelMapper.map(tipoTransacaoAtualizado, TipoTransacaoDto.class);

            }else{
                throw new DataIntegrityException("A Id do Tipo de Transação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Transação não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (tipoTransacaoRepository.existsById(id)) {
                tipoTransacaoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id Tipo de Transação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Transação!");
        }
    }
}
