package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "instansi")
public class InstansiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama" , nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi" , nullable = false)
	private String deskripsi;
	
	@OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<PegawaiModel> listPegawaiInstansi;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_provinsi", referencedColumnName = "id", nullable = false)
	@OnDelete(action= OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ProvinsiModel provinsi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public List<PegawaiModel> getListPegawaiInstansi() {
		return listPegawaiInstansi;
	}

	public void setListPegawaiInstansi(List<PegawaiModel> listPegawaiInstansi) {
		this.listPegawaiInstansi = listPegawaiInstansi;
	}

	public ProvinsiModel getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(ProvinsiModel provinsi) {
		this.provinsi = provinsi;
	}
	
	
}
