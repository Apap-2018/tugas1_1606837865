package com.apap.tugas1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.service.*;
import com.apap.tugas1.model.*;
import java.util.List;

@Controller
public class InstansiController {
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping(value = "/instansi/add" , method = RequestMethod.GET)
	public @ResponseBody List<InstansiModel> getInstansiProvinsi(@RequestParam (value = "idProvinsi") String id){
		ProvinsiModel provinsi = provinsiService.getProvinsiById(Long.parseLong(id)).get();
		return provinsi.getListInstansiProvinsi();	
	}
}
