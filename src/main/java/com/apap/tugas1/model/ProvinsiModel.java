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
@Table(name = "provinsi")
public class ProvinsiModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max  = 255)
	@Column(name = "nama" , nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "presentase_tunjangan", nullable=false)
	private Double presentaseTunjangan;
	
	@OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<InstansiModel> listInstansiProvinsi;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Double getPresentaseTunjangan() {
		return presentaseTunjangan;
	}

	public void setPresentaseTunjangan(Double presentaseTunjangan) {
		this.presentaseTunjangan = presentaseTunjangan;
	}

	public List<InstansiModel> getListInstansiProvinsi() {
		return listInstansiProvinsi;
	}

	public void setListInstansiProvinsi(List<InstansiModel> listInstansiProvinsi) {
		this.listInstansiProvinsi = listInstansiProvinsi;
	}
	
	
}
