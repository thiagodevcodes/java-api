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

import br.com.thiago.orcamento.model.FonteRecursoModel;
import br.com.thiago.orcamento.repository.FonteRecursoRepository;
import br.com.thiago.orcamento.rest.dto.FonteRecursoDto;
import br.com.thiago.orcamento.rest.form.FonteRecursoForm;
import br.com.thiago.orcamento.service.exceptions.BusinessRuleException;
import br.com.thiago.orcamento.service.exceptions.DataIntegrityException;
import br.com.thiago.orcamento.service.exceptions.ObjectNotFoundException;

@Service
public class FonteRecursoService {
    @Autowired
    FonteRecursoRepository fonteRecursoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FonteRecursoDto findById(Integer id) {
        try {
            FonteRecursoModel fonteRecursoModel = fonteRecursoRepository.findById(id).get();
            return modelMapper.map(fonteRecursoModel, FonteRecursoDto.class);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + FonteRecursoModel.class.getName());
        }
    }

    public List<FonteRecursoDto> findAllData() {
        try {
            List<FonteRecursoModel> fonteRecursoList = fonteRecursoRepository.findAll();

            return fonteRecursoList.stream()
                    .map(fonteRecurso -> modelMapper.map(fonteRecurso, FonteRecursoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Ações!", e.getErrorMessages());
        }
    }

    public Page<FonteRecursoDto> findAll(Pageable pageable){
        Page<FonteRecursoModel> fonteRecursoPage = fonteRecursoRepository.findAll(pageable);
        return fonteRecursoPage.map(fonteRecurso -> modelMapper.map(fonteRecurso, FonteRecursoDto.class));
    }

    public FonteRecursoDto insert(FonteRecursoForm fonteRecursoForm) {
        try {
            FonteRecursoModel fonteRecursoNovo = modelMapper.map(fonteRecursoForm, FonteRecursoModel.class);

            Optional<FonteRecursoModel> byNome = fonteRecursoRepository.findByNome(fonteRecursoNovo.getNome());
            Optional<FonteRecursoModel> byCodigo = fonteRecursoRepository.findByCodigo(fonteRecursoForm.getCodigo());
            
            if (byNome.isPresent() || byCodigo.isPresent()) {
                throw new DataIntegrityException("Fonte de Recurso já registrada.");
            }

            fonteRecursoNovo = fonteRecursoRepository.save(fonteRecursoNovo);
            return modelMapper.map(fonteRecursoNovo, FonteRecursoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Fonte de Recurso não foi(foram) preenchido(s).");
        }
    }

     public FonteRecursoDto updateById(FonteRecursoForm fonteRecursoForm, Integer id) {
        try {
            Optional<FonteRecursoModel> fonteRecursoExistente = fonteRecursoRepository.findById(id);

            if (fonteRecursoExistente.isPresent()) {
                FonteRecursoModel fonteRecursoAtualizado = fonteRecursoExistente.get();

                modelMapper.map(fonteRecursoForm, fonteRecursoAtualizado);
                fonteRecursoAtualizado = fonteRecursoRepository.save(fonteRecursoAtualizado);

                return modelMapper.map(fonteRecursoAtualizado, FonteRecursoDto.class);

            }else{
                throw new DataIntegrityException("O Id da Fonte de Recurso não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Fonte de Recurso não foi(foram) preenchido(s).");
        }
    }

    public void deleteById(Integer id) {
        try {
            if (fonteRecursoRepository.existsById(id)) {
                fonteRecursoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O Id do Ação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Ação!");
        }
    }
}
