package br.com.petmagnet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.EstabelecimentoDTO;
import br.com.petmagnet.model.Estabelecimento;

@Service
public interface EstabelecimentoService {
	public Estabelecimento cadastrar(Estabelecimento obj);
	public Estabelecimento alterar(Long id, Estabelecimento obj);
	public Estabelecimento excluir(Long id);
	public Optional<Estabelecimento> consultarPorId(Long id);
	public List<Estabelecimento> consultarTodos();
//	public EstabelecimentoDTO convertParaDTO(Estabelecimento obj);
}