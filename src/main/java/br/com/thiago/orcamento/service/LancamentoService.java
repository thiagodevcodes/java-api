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

import br.com.thiago.orcamento.model.LancamentoModel;
import br.com.thiago.orcamento.repository.LancamentoRepository;
import br.com.thiago.orcamento.rest.dto.LancamentoDto;
import br.com.thiago.orcamento.rest.form.LancamentoForm;
import br.com.thiago.orcamento.service.exceptions.BusinessRuleException;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class LancamentoService {
    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LancamentoDto findById(Integer id) {
        try {
            LancamentoModel lancamentoModel = lancamentoRepository.findById(id).get();
            return modelMapper.map(lancamentoModel, LancamentoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + LancamentoModel.class.getName());
        }
    }

    public List<LancamentoDto> findAllData() {
        try {
            List<LancamentoModel> lancamentoList = lancamentoRepository.findAll();

            return lancamentoList.stream()
                    .map(lancamento -> modelMapper.map(lancamento, LancamentoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Ações!", e.getErrorMessages());
        }
    }

    public Page<LancamentoDto> findAll(Pageable pageable){
        Page<LancamentoModel> lancamentoPage = lancamentoRepository.findAll(pageable);
        return lancamentoPage.map(lancamento -> modelMapper.map(lancamento, LancamentoDto.class));
    }

    public LancamentoDto insert(LancamentoForm lancamentoForm) {
        try {
            LancamentoModel lancamentoNovo = modelMapper.map(lancamentoForm, LancamentoModel.class);

            lancamentoNovo = lancamentoRepository.save(lancamentoNovo);
            return modelMapper.map(lancamentoNovo, LancamentoDto.class);

        } catch (DataIntegrityViolationException e) {
            
            if (e.getMessage().contains("FK")) {
                String mensagemDeErro = e.getMessage();

                // Divide a mensagem pelo ponto e vírgula
                String[] partes = mensagemDeErro.split(";\\s");
                System.out.println(partes[2]);
                String terceiraParte = partes[2];
                throw new DataIntegrityException("Erro ocorrido pela " + terceiraParte);
            }
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Lançamento não foi(foram) preenchido(s).");
        }
    }

    public LancamentoDto updateById(LancamentoForm lancamentoForm, Integer id) {
        try {
            Optional<LancamentoModel> lancamentoExistente = lancamentoRepository.findById(id);

            if (lancamentoExistente.isPresent()) {
                LancamentoModel lancamentoAtualizado = lancamentoExistente.get();
                
                modelMapper.map(lancamentoForm, lancamentoAtualizado);
                lancamentoAtualizado = lancamentoRepository.save(lancamentoAtualizado);

                return modelMapper.map(lancamentoAtualizado, LancamentoDto.class);

            }else{
                throw new DataIntegrityException("A Id do Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Lançamento não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (lancamentoRepository.existsById(id)) {
                lancamentoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Lançamento!");
        }
    }
}
