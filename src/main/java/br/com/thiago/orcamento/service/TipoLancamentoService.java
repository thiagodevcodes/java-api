package br.com.thiago.orcamento.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.thiago.orcamento.rest.dto.TipoLancamentoDto;

import br.com.thiago.orcamento.model.TipoLancamentoModel;
import br.com.thiago.orcamento.repository.TipoLancamentoRepository;
import br.com.thiago.orcamento.rest.form.TipoLancamentoForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class TipoLancamentoService {

    @Autowired
    TipoLancamentoRepository tipoLancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TipoLancamentoDto findById(Integer id) {
        try {
            TipoLancamentoModel tipoLancamentoModel = tipoLancamentoRepository.findById(id).get();
            return modelMapper.map(tipoLancamentoModel, TipoLancamentoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TipoLancamentoModel.class.getName());
        }
    }

    public Page<TipoLancamentoDto> findAll(Pageable pageable){
        Page<TipoLancamentoModel> tipoLancamentoPage = tipoLancamentoRepository.findAll(pageable);
        return tipoLancamentoPage.map(tipoLancamento -> modelMapper.map(tipoLancamento, TipoLancamentoDto.class));
    }

    public TipoLancamentoDto insert(TipoLancamentoForm tipoLancamentoForm) {
        try {
            TipoLancamentoModel tipoLancamentoNovo = modelMapper.map(tipoLancamentoForm, TipoLancamentoModel.class);

            Optional<TipoLancamentoModel> byNome = tipoLancamentoRepository.findByNome(tipoLancamentoNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Tipo de Lançamento já registrado.");
            }


            tipoLancamentoNovo = tipoLancamentoRepository.save(tipoLancamentoNovo);
            return modelMapper.map(tipoLancamentoNovo, TipoLancamentoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Lançamento não foi(foram) preenchido(s).");
        }
    }

    public TipoLancamentoDto updateById(TipoLancamentoForm tipoLancamentoForm, Integer id) {
        try {
            Optional<TipoLancamentoModel> tipoLancamentoExistente = tipoLancamentoRepository.findById(id);

            if (tipoLancamentoExistente.isPresent()) {
                TipoLancamentoModel tipoLancamentoAtualizado = tipoLancamentoExistente.get();

                modelMapper.map(tipoLancamentoForm, tipoLancamentoAtualizado);
                tipoLancamentoAtualizado = tipoLancamentoRepository.save(tipoLancamentoAtualizado);

                return modelMapper.map(tipoLancamentoAtualizado, TipoLancamentoDto.class);

            }else{
                throw new DataIntegrityException("A Id do Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Lançamento não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (tipoLancamentoRepository.existsById(id)) {
                tipoLancamentoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Lançamento!");
        }
    }
}
