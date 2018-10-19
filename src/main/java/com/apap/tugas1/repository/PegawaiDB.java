package com.apap.tugas1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

import java.sql.Date;
import java.util.List;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel,Long>{
	
	Optional<PegawaiModel> findByNip(String nip);
	
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	
	List<PegawaiModel> findByTanggalLahirAndTahunMasukAndInstansi(Date tanggalLahir, String tahunMasuk,InstansiModel instansi);
}
