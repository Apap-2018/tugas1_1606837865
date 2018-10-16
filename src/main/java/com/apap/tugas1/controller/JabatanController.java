package com.apap.tugas1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.apap.tugas1.service.*;
import com.apap.tugas1.model.*;
import java.util.List;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/tambah")
	public String addJabatan(Model model) {
		JabatanModel jabatan = new JabatanModel();
		model.addAttribute("jabatan", jabatan);
		return "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah" , method = RequestMethod.POST)
	public String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		System.out.println(jabatan.getGaji_pokok());
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return "jabatan-success";
	}
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	public String viewJabatan(@RequestParam (value = "jabatanId") String id , Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(Long.parseLong(id)).get();
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	}
}
