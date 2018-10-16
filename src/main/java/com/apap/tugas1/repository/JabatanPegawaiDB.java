package com.apap.tugas1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.tugas1.model.JabatanPegawaiModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface JabatanPegawaiDB extends JpaRepository<JabatanPegawaiModel,Long> {
	/**
	 * 
	 *buat ngambil list jabatan si pegawai
	 */
	Optional<List<JabatanPegawaiModel>> findAllByPegawai_Nip(String nip);
	

}
