package com.apap.tugas1.service;

import com.apap.tugas1.model.PegawaiModel;
import java.util.Optional;

public interface PegawaiService {
	
	void addPegawai(PegawaiModel pegawai);
	
	Optional<PegawaiModel> getPegawaiByNip(String nip);
}
