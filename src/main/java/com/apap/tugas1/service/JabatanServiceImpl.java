package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.*;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDB jabatandb;
	
	@Override
	public Optional<JabatanModel> getJabatanById(Long id) {
		
		return jabatandb.findById(id);
	}

	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatandb.save(jabatan);
		
	}

	@Override
	public void deleteJabatan(JabatanModel jabatan , Long id) {
		jabatandb.deleteById(id);
		
	}

	@Override
	public List<JabatanModel> getAllJabatan() {
		// TODO Auto-generated method stub
		return jabatandb.findAll();
	}

	@Override
	public void updateJabatan(JabatanModel jabatan , Long jabatanId) {
		JabatanModel jabatan1 = jabatandb.getOne(jabatanId);
		jabatan1.setNama(jabatan.getNama());
		jabatan1.setDeskripsi(jabatan.getDeskripsi());
		jabatan1.setGaji_pokok(jabatan.getGaji_pokok());
		jabatandb.save(jabatan1);
	}	

}
