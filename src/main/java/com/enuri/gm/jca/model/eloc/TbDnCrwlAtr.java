package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dnAtrId")
public class TbDnCrwlAtr implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int dnAtrId;
	private String dnAtrNm;
	private String cateCd;
	private String useYn;
	private Timestamp insDtm;
	private String insEmpId;
	private Timestamp updDtm;
	private String updEmpId;
	private String dnAtr;

	@OneToMany(mappedBy = "att", cascade = CascadeType.ALL)
	private Set<TbDnCrwlAtrEnr> attEnrs;
}
