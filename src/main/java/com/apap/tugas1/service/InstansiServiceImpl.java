package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.repository.*;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	private InstansiDB instansidb;
	
	@Override
	public Optional<InstansiModel> getInstansiById(Long id) {
		return instansidb.findById(id);
	}

	@Override
	public List<InstansiModel> getAllInstansi() {
		// TODO Auto-generated method stub
		return instansidb.findAll();
	}

}
