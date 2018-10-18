package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
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

	@Override
	public PegawaiModel pegawaiTertua(InstansiModel instansi) {
		List<PegawaiModel> listTertua = pegawaidb.findByInstansiOrderByTanggalLahirAsc(instansi);
		return listTertua.get(0);
	}

	@Override
	public PegawaiModel pegawaiTermuda(InstansiModel instansi) {
		List<PegawaiModel> listTermuda = pegawaidb.findByInstansiOrderByTanggalLahirAsc(instansi);
		return listTermuda.get(listTermuda.size()-1);
	}

	

	

}
