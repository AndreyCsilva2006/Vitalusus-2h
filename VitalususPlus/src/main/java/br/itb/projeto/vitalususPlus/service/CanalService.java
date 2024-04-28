package br.itb.projeto.vitalususPlus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.itb.projeto.vitalususPlus.model.entity.Canal;
import br.itb.projeto.vitalususPlus.model.entity.Treinador;
import br.itb.projeto.vitalususPlus.model.repository.CanalRepository;
import br.itb.projeto.vitalususPlus.model.repository.TreinadorRepository;

@Service
public class CanalService {
	 private CanalRepository canalRepository;

	    public CanalService(CanalRepository canalRepository) {
	        super();
	        this.canalRepository = canalRepository;
	    }
	    public List<Canal> findAll(){
	        List<Canal> canais = canalRepository.findAll();
	        return canais;
	    }
	    public Canal findById(long id) {
	        Optional<Canal> canal = this.canalRepository.findById(id);
	        return canal.orElseThrow(() -> new RuntimeException(
	                "treinador não encontrado"
	        ));
	    }
	    public Canal save(Canal canal){
	        canal.setId(null);
			if (canal.getAlunos()==null){
				canal.setAlunos(new ArrayList<>());
			}
			canal.setSeguidores(canal.getAlunos().size());
			canal.setVisualizacoes(0);
			return canalRepository.save(canal);
	    }
	    public void delete(Canal canal) {
	        this.canalRepository.delete(canal);
	    }
	    public Canal update(Canal canal){
			if (canal.getAlunos()==null){
				canal.setAlunos(new ArrayList<>());
			}
			canal.setSeguidores(canal.getAlunos().size());
			return canalRepository.save(canal);
	    }
}
