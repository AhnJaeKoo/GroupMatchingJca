package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblEnuriBrand implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblEnuriBrandId id;
	private String brandNm;
	private String delYn;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maker_id", insertable = false, updatable = false)
	@JsonBackReference
	private TblEnuriMaker maker;
}