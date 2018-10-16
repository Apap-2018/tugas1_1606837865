package com.apap.tugas1.service;
import com.apap.tugas1.model.*;
import java.util.*;

public interface JabatanService {
	Optional<JabatanModel> getJabatanById(Long id);
	void addJabatan(JabatanModel jabatan);
	void deleteJabatan(JabatanModel jabatan);
	List<JabatanModel> getAllJabatan();
}
