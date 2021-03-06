package br.com.petmagnet.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.dto.ColaboradorReqDTO;
import br.com.petmagnet.exception.BeanNotFoundException;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.repository.ColaboradorRepository;
import br.com.petmagnet.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ColaboradorServiceImpl implements ColaboradorService {
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	@Autowired
	private EstabelecimentoServiceImpl estabelecimentoService;

	@Override
	public Colaborador gravar(Colaborador obj) {
		Estabelecimento estabelecimento = this.estabelecimentoService.consultarPorId(obj.getEstabelecimento().getId());

		if (this.colaboradorRepository
				.findByNomeAndSenhaAndEstabelecimento(obj.getNome(), obj.getSenha(), estabelecimento).isPresent()) {
			throw new BeanNotFoundException("Colaborador já cadastrado");
		}
		
		Colaborador colaborador = new ModelMapper().map(obj, Colaborador.class);

		colaborador.setEstabelecimento(estabelecimento);
		colaborador.setId(null);

		return this.colaboradorRepository.save(colaborador);
	}

	@Override
	public Colaborador alterar(Long Id, ColaboradorReqDTO obj) {
		return this.colaboradorRepository.findById(Id).map(colaborador -> {
			colaborador.setSenha(obj.getSenha());

			return this.colaboradorRepository.save(colaborador);
		}).orElseThrow(() -> new BeanNotFoundException("Colaborador não cadastrado"));
	}

	@Override
	public Colaborador excluir(Long id) {
		return this.colaboradorRepository.findById(id).map(colaborador -> {
			this.colaboradorRepository.deleteById(id);
			return colaborador;
		}).orElseThrow(() -> new BeanNotFoundException("Colaborador não cadastrado"));
	}

	@Override
	public List<Colaborador> consultarTodos() {
		return this.colaboradorRepository.findAll();
	}

	@Override
	public Optional<Colaborador> consultarPorId(Long id) {
		return Optional.ofNullable(this.colaboradorRepository.findById(id)
				.orElseThrow(() -> new BeanNotFoundException("Colaborador não Cadastrado")));
	}

	@Override
	public Optional<Colaborador> consultarPorColaborador(Estabelecimento estabelecimento, Long idColaborador) {
		return Optional.ofNullable(this.colaboradorRepository.findByEstabelecimentoAndId(estabelecimento, idColaborador)
				.orElseThrow(() -> new BeanNotFoundException("Colaborador não Cadastrado")));
	}
}
