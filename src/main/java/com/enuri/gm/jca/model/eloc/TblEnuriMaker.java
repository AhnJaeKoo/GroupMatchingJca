package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblEnuriMaker implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int makerId;
	private String makerNm;
	private String delYn;

	@OneToMany(mappedBy = "maker", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<TblEnuriBrand> brands = new ArrayList<>();
}
