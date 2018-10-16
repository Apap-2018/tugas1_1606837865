package com.apap.tugas1.service;
import java.util.*;
import com.apap.tugas1.model.*;

public interface ProvinsiService {
	Optional<ProvinsiModel> getProvinsiById(Long id);
	List<ProvinsiModel> getAllProvinsi();
}
