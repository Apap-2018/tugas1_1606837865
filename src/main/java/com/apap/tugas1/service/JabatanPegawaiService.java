package com.apap.tugas1.service;
import com.apap.tugas1.model.JabatanPegawaiModel;
import java.util.Optional;
import java.util.List;

public interface JabatanPegawaiService {
	
	Optional<List<JabatanPegawaiModel>> getJabatanPegawaiByNip(String Nip);
	List<JabatanPegawaiModel> getJabatanPegawaiById(Long id);
	
}
