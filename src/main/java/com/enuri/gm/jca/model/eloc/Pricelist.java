package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Pricelist implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private long plNo;
	private int plModelno;
	private String plGoodsnm;
	private String plSrvflag;
}
