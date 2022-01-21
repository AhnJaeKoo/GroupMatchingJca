package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "qaId")
public class TbDnCrwlQa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long qaId;
	private String cateCd;
	private int dnRnk;
	private String dnGrCatlId;
	private String dnCatlId;
	private String nwYn;
	private String btcId;
	private String modelNm;
	private int mkrId;
	private String mkrNm;
	private int brndId;
	private String brndNm;
	private String dnCndNm;
	private String dnAtr;
	private String dnCpVlu;
	private String dnCpUnt;
	private String useYn;
	private float dnVolume;
	private int dnQnt;
	private String chgCateCd;
	private Timestamp updDtm;
	private String updEmpId;
	private String regDate;

	@OneToMany(mappedBy = "qa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TbDnCrwlQaPl> pricelists;

	@OneToMany(mappedBy = "qa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TbDnCrwlQaModel> models;
}
