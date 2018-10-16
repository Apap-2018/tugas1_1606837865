package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.*;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
	@Autowired
	private ProvinsiDB provinsidb;
	
	@Override
	public Optional<ProvinsiModel> getProvinsiById(Long id) {
		
		return provinsidb.findById(id);
	}

	@Override
	public List<ProvinsiModel> getAllProvinsi() {
		// TODO Auto-generated method stub
		return provinsidb.findAll();
	}
	

}
