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
	
	@Autowired
	private JabatanPegawaiService jabatanPegawaiService;
	
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
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	public String ubahJabatan(@RequestParam (value = "jabatanId") String id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(Long.parseLong(id)).get();
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah" , method = RequestMethod.POST)
	public String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.updateJabatan(jabatan, jabatan.getId());
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan-success";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	public String deleteJabatan(@RequestParam (value = "jabatanId") String id, @ModelAttribute JabatanModel jabatan,  Model model) {
//		JabatanModel jabatan = jabatanService.getJabatanById(Long.parseLong(id)).get();
//		jabatanService.deleteJabatan(jabatan, jabatan.getId());
//		model.addAttribute("jabatan", jabatan);
//		return "delete-jabatan";
		List<JabatanPegawaiModel> listJabatan = jabatanPegawaiService.getJabatanPegawaiById(Long.parseLong(id));
		if (listJabatan.isEmpty()) {
			jabatanService.deleteJabatan(jabatan, jabatan.getId());
			model.addAttribute("jabatan", jabatan);
			return "delete-jabatan";
		}else {
			return "cant-delete-jabatan";
		}
		
	}
	
	@RequestMapping(value = "/jabatan/viewall" , method = RequestMethod.GET)
	public String viewAllJabatan(Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		return "viewAll-jabatan";

	}
}
