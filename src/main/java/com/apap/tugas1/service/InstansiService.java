package com.apap.tugas1.service;
import java.util.*;
import com.apap.tugas1.model.*;

public interface InstansiService {
	Optional<InstansiModel> getInstansiById(Long id);
	
	List<InstansiModel> getAllInstansi();
}
