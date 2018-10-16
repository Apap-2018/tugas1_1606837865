package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.repository.*;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService{
	@Autowired
	private JabatanPegawaiDB jabatanpegawaidb;
	
	@Override
	public Optional<List<JabatanPegawaiModel>> getJabatanPegawaiByNip(String Nip) {
		
		return jabatanpegawaidb.findAllByPegawai_Nip(Nip);
	}

}
