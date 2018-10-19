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

import java.util.ArrayList;
import java.util.List;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanPegawaiService jabatanPegawaiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping("/")
	public String home(Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan", listJabatan);
		return "home";
	}
	
	@RequestMapping("/pegawai")
	public String viewPegawai(@RequestParam (value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip).get();
		List<JabatanPegawaiModel> jabatanPegawai = jabatanPegawaiService.getJabatanPegawaiByNip(nip).get();
		double gaji = 0.0;
		for(JabatanPegawaiModel jabatan : jabatanPegawai) {
			if (jabatan.getJabatan().getGaji_pokok()> gaji) {
				gaji=jabatan.getJabatan().getGaji_pokok();
			}
		}
		gaji += pegawai.getInstansi().getProvinsi().getPresentaseTunjangan()/100 * gaji;
		model.addAttribute("gaji", (long)gaji);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah" , method = RequestMethod.POST , params = {"addRow"})
	public String addRowJabatan(@ModelAttribute PegawaiModel pegawaiBaru , Model model) {
		PegawaiModel pegawai = pegawaiBaru;
		
		JabatanPegawaiModel jabatanPegawai = new JabatanPegawaiModel();
		jabatanPegawai.setPegawai(pegawai);
		pegawai.getListJabatan().add(jabatanPegawai);
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = new ArrayList<InstansiModel>();
		listInstansi = listProvinsi.get(0).getListInstansiProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan1", listJabatan);
		return "add-pegawai";
	}
	
	@RequestMapping("/pegawai/tambah")
	public String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setListJabatan(new ArrayList<JabatanPegawaiModel>());
		JabatanPegawaiModel jabatanPegawai = new JabatanPegawaiModel();
		jabatanPegawai.setPegawai(pegawai);
		pegawai.getListJabatan().add(jabatanPegawai);
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<JabatanModel> listJabatan1 = jabatanService.getAllJabatan();
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan1", listJabatan1);
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	public String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		
		//list jabatan
		List<JabatanPegawaiModel> listJabatanModel = pegawai.getListJabatan();
		
		pegawai.setListJabatan(new ArrayList<JabatanPegawaiModel>());
		pegawaiService.addPegawai(pegawai);
		
		for(JabatanPegawaiModel jabatanPegawai : listJabatanModel) {
			jabatanPegawai.setPegawai(pegawai);
			jabatanPegawaiService.addJabatanPegawai(jabatanPegawai);
		}
		model.addAttribute("pegawai", pegawai);
		return "add-jabatan-success";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua" , method = RequestMethod.GET)
	public String viewTertuaTermuda(@RequestParam (value = "instansiId") String id ,  Model model) {
		InstansiModel instansi = instansiService.getInstansiById(Long.parseLong(id)).get();
		PegawaiModel pegawaiTertua = pegawaiService.pegawaiTertua(instansi);
		PegawaiModel pegawaiTermuda = pegawaiService.pegawaiTermuda(instansi);
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		return "view-pegawai-termuda";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	public String ubahPegawai(@RequestParam (value = "pegawaiNip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip).get();
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<JabatanModel> listJabatan1 = jabatanService.getAllJabatan();
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan1", listJabatan1);
		model.addAttribute("pegawai", pegawai);
		return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah" , method = RequestMethod.POST)
	public String ubahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		
		pegawaiService.updatePegawai(pegawai, pegawai.getId());
	
		model.addAttribute("pegawai", pegawai);
		return "ubah-pegawai-success";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	public String cariPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setListJabatan(new ArrayList<JabatanPegawaiModel>());
		JabatanPegawaiModel jabatanPegawai = new JabatanPegawaiModel();
		jabatanPegawai.setPegawai(pegawai);
		pegawai.getListJabatan().add(jabatanPegawai);
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<JabatanModel> listJabatan1 = jabatanService.getAllJabatan();
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan1", listJabatan1);

		return "cari-pegawai";
	}
	
}
