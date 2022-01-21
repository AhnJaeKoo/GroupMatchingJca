package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class TbDnCrwlAtrEnr implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbDnCrwlAtrEnrId id;
	private Timestamp insDtm;
	private Timestamp updDtm;
	private String updEmpId;
	private String enrAtrDtlRngVlu;

	@ManyToOne
	@JoinColumn(name = "dnAtrId", insertable = false, updatable = false)
	private TbDnCrwlAtr att;
}
