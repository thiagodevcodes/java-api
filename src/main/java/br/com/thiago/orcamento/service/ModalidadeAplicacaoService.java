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
import br.com.thiago.orcamento.model.ModalidadeAplicacaoModel;
import br.com.thiago.orcamento.repository.ModalidadeAplicacaoRepository;
import br.com.thiago.orcamento.rest.dto.ModalidadeAplicacaoDto;
import br.com.thiago.orcamento.rest.form.ModalidadeAplicacaoForm;
import br.com.thiago.orcamento.rest.form.ModalidadeAplicacaoUpdateForm;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class ModalidadeAplicacaoService {
    @Autowired
    ModalidadeAplicacaoRepository modalidadeAplicacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ModalidadeAplicacaoDto findById(Integer id) {
        try {
            ModalidadeAplicacaoModel modalidadeAplicacaoModel = modalidadeAplicacaoRepository.findById(id).get();
            return modelMapper.map(modalidadeAplicacaoModel, ModalidadeAplicacaoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + GrupoDespesaModel.class.getName());
        }
    }

        public List<ModalidadeAplicacaoDto> findAll(){
        List<ModalidadeAplicacaoModel> modalidadeAplicacaoList = modalidadeAplicacaoRepository.findAll();

        return modalidadeAplicacaoList.stream()
                .map(modalidadeAplicacao -> modelMapper.map(modalidadeAplicacao, ModalidadeAplicacaoDto.class))
                .collect(Collectors.toList());
    }

    public ModalidadeAplicacaoDto insert(ModalidadeAplicacaoForm modalidadeAplicacaoForm) {
        try {
            ModalidadeAplicacaoModel modalidadeAplicacaoNovo = modelMapper.map(modalidadeAplicacaoForm, ModalidadeAplicacaoModel.class);

            Optional<ModalidadeAplicacaoModel> byNome = modalidadeAplicacaoRepository.findByNome(modalidadeAplicacaoNovo.getNome());

            if (byNome.isPresent()) {
                throw new DataIntegrityException("Grupo Despesa já registrado.");
            }
            
            modalidadeAplicacaoNovo = modalidadeAplicacaoRepository.save(modalidadeAplicacaoNovo);
            return modelMapper.map(modalidadeAplicacaoNovo, ModalidadeAplicacaoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Modalidade da Aplicação não foi(foram) preenchido(s).");
        }
    }

     public ModalidadeAplicacaoDto updateById(ModalidadeAplicacaoUpdateForm modalidadeAplicacaoUpdateForm, Integer id) {
        try {
            Optional<ModalidadeAplicacaoModel> modalidadeAplicacaoExistente = modalidadeAplicacaoRepository.findById(id);

            if (modalidadeAplicacaoExistente.isPresent()) {
                ModalidadeAplicacaoModel modalidadeAplicacaoAtualizado = modalidadeAplicacaoExistente.get();
                modalidadeAplicacaoAtualizado.setNome(modalidadeAplicacaoUpdateForm.getNome());
                modalidadeAplicacaoAtualizado.setCodigo(modalidadeAplicacaoUpdateForm.getCodigo());
                modalidadeAplicacaoAtualizado = modalidadeAplicacaoRepository.save(modalidadeAplicacaoAtualizado);

                return modelMapper.map(modalidadeAplicacaoAtualizado, ModalidadeAplicacaoDto.class);

            }else{
                throw new DataIntegrityException("O Id da Modalidade da Aplicação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Modalidade da Aplicação não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (modalidadeAplicacaoRepository.existsById(id)) {
                modalidadeAplicacaoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id da Modalidade da Aplicação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Modalidade da Aplicação!");
        }
    }
}
