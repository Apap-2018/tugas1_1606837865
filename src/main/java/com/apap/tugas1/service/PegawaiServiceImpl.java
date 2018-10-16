package com.apap.tugas1.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.*;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaidb;
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaidb.save(pegawai);
		
	}

	@Override
	public Optional<PegawaiModel> getPegawaiByNip(String nip) {
		
		return pegawaidb.findByNip(nip);
	}

	

	

}
