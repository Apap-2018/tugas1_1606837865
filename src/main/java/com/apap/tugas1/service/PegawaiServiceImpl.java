package com.apap.tugas1.service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.*;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaidb;
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		String nip = "";
		nip+= pegawai.getInstansi().getId();
		Date date = pegawai.getTanggalLahir();
		String[] tglLahir = (""+date).split("-");
		for (int i = tglLahir.length-1; i >= 0; i--) {
			int ukuranTgl = tglLahir[i].length();
			nip += tglLahir[i].substring(ukuranTgl-2, ukuranTgl);
		}
		System.out.println(nip);
		nip+=pegawai.getTahunMasuk();
		List<PegawaiModel> listPegawai = pegawaidb.findByTanggalLahirAndTahunMasukAndInstansi(date, pegawai.getTahunMasuk(), pegawai.getInstansi());
		if(listPegawai.size() >= 10) {
			nip += listPegawai;
		}else {
			nip += "0" + (listPegawai.size()+1);
		}
		
		System.out.println(nip);
		pegawai.setNip(nip);
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

	@Override
	public void updatePegawai(PegawaiModel pegawai , Long id) {
		PegawaiModel pegawai1 = pegawaidb.getOne(id);
		pegawai1.setNama(pegawai.getNama());
		pegawai1.setTempat_lahir(pegawai.getTempat_lahir());
		pegawai1.setTanggalLahir(pegawai.getTanggalLahir());
		pegawai1.setTahunMasuk(pegawai.getTahunMasuk());
		pegawaidb.save(pegawai1);
	}

	@Override
	public List<PegawaiModel> getAllPegawai() {
		// TODO Auto-generated method stub
		return pegawaidb.findAll();
	}	
	
	
	

	

}
