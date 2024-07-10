package br.itb.projeto.vitalususPlus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.itb.projeto.vitalususPlus.model.entity.*;
import br.itb.projeto.vitalususPlus.model.repository.SeguidorRepository;
import br.itb.projeto.vitalususPlus.model.repository.VideoaulaRepository;
import org.springframework.stereotype.Service;

import br.itb.projeto.vitalususPlus.model.repository.CanalRepository;
import br.itb.projeto.vitalususPlus.model.repository.TreinadorRepository;

@Service
public class CanalService {
	private CanalRepository canalRepository;
	private SeguidorRepository seguidorRepository;
	private VideoaulaRepository videoaulaRepository;

	public CanalService(CanalRepository canalRepository, SeguidorRepository seguidorRepository, VideoaulaRepository videoaulaRepository) {
		super();
		this.canalRepository = canalRepository;
		this.seguidorRepository = seguidorRepository;
		this.videoaulaRepository = videoaulaRepository;
	}

	public List<Canal> findAll() {
		List<Canal> canais = canalRepository.findAll();
		return canais;
	}

	public Canal findById(long id) {
		Optional<Canal> canal = this.canalRepository.findById(id);
		return canal.orElseThrow(() -> new RuntimeException("treinador não encontrado"));
	}

	public Canal save(Canal canal) {
		canal.setId(null);
		List<Videoaula> videoaula = videoaulaRepository.findAllByCanal(canal);
		if (canal.getAlunos() == null) {
			canal.setAlunos(new ArrayList<>());
		}
		if (videoaula == null){
			videoaula = new ArrayList<>();
		}
		canal.setVisualizacoes(0);
		for (int i = 0; i < videoaula.size(); i++) {
			canal.setVisualizacoes(canal.getVisualizacoes() + videoaula.get(i).getVisualizacoes());
		}
		canal.setSeguidores(canal.getAlunos().size());
		canal.setVisualizacoes(videoaula.size());
		return canalRepository.save(canal);
	}

	public void delete(Canal canal) {
		this.canalRepository.delete(canal);
	}

	public Canal updateFix(Long id) {
		Optional<Canal> _canal = canalRepository.findById(id);
		if (_canal.isPresent()) {
			Canal canalUpdatado = _canal.get();
			List<Videoaula> videoaula = videoaulaRepository.findAllByCanal(canalUpdatado);
			if (videoaula == null){
				videoaula = new ArrayList<>();
			}
			if (canalUpdatado.getAlunos() == null) {
				canalUpdatado.setAlunos(new ArrayList<>());
			}
			if (canalUpdatado.getAlunos() == null) {
				canalUpdatado.setAlunos(new ArrayList<>());
			}
			canalUpdatado.setVisualizacoes(0);
			for (int i = 0; i < videoaula.size(); i++) {
				canalUpdatado.setVisualizacoes(canalUpdatado.getVisualizacoes() + videoaula.get(i).getVisualizacoes());
			}
			canalUpdatado.setSeguidores(canalUpdatado.getAlunos().size());
			return canalRepository.save(canalUpdatado);
		}
		return null;
	}
	public Canal addAlunos(Long id, Canal canal) {
		Optional<Canal> _canal = canalRepository.findById(id);
		if (_canal.isPresent()) {
			Canal canalUpdatado = _canal.get();
			List<Videoaula> videoaula = videoaulaRepository.findAllByCanal(canalUpdatado);
			canalUpdatado.getAlunos().addAll(canal.getAlunos());
			if (canalUpdatado.getAlunos() == null) {
				canalUpdatado.setAlunos(new ArrayList<>());
			}
			canalUpdatado.setVisualizacoes(0);
			for (int i = 0; i < videoaula.size(); i++) {
				canalUpdatado.setVisualizacoes(canalUpdatado.getVisualizacoes() + videoaula.get(i).getVisualizacoes());
			}
			canalUpdatado.setSeguidores(canalUpdatado.getAlunos().size());
			return canalRepository.save(canalUpdatado);
		}
		return null;
	}
	public Canal removeAlunos(Long id, Aluno aluno) {
		Optional<Canal> _canal = canalRepository.findById(id);
		if (_canal.isPresent()) {
			Canal canalUpdatado = _canal.get();
			List<Videoaula> videoaula = videoaulaRepository.findAllByCanal(canalUpdatado);
			List<Seguidor> seguidor = seguidorRepository.findAllByAlunoAndCanal(aluno, canalUpdatado);
			seguidorRepository.deleteAll(seguidor);
			if (canalUpdatado.getAlunos() == null) {
				canalUpdatado.setAlunos(new ArrayList<>());
			}
			canalUpdatado.setVisualizacoes(0);
			for (int i = 0; i < videoaula.size(); i++) {
				canalUpdatado.setVisualizacoes(canalUpdatado.getVisualizacoes() + videoaula.get(i).getVisualizacoes());
			}
			canalUpdatado.setSeguidores(canalUpdatado.getAlunos().size());
			return canalRepository.save(canalUpdatado);
		}
		return null;
	}
	public Canal updateNome(Long id, Canal canal) {
		Optional<Canal> _canal = canalRepository.findById(id);
		if (_canal.isPresent()) {
			Canal canalUpdatado = _canal.get();
			List<Videoaula> videoaula = videoaulaRepository.findAllByCanal(canalUpdatado);
			canalUpdatado.setNome(canal.getNome());
			if (canalUpdatado.getAlunos() == null) {
				canalUpdatado.setAlunos(new ArrayList<>());
			}
			canalUpdatado.setVisualizacoes(0);
			for (int i = 0; i < videoaula.size(); i++) {
				canalUpdatado.setVisualizacoes(canalUpdatado.getVisualizacoes() + videoaula.get(i).getVisualizacoes());
			}
			return canalRepository.save(canalUpdatado);
		}
		return null;
	}
}
