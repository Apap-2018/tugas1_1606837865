package com.apap.tugas1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.tugas1.model.PegawaiModel;
import java.util.Optional;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel,Long>{
	
	Optional<PegawaiModel> findByNip(String nip);
}