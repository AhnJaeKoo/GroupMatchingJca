package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TbDnCrwlStt implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbDnCrwlSttId id;
	private int colcQaCnt;
	private int colcQaMtcCnt;
	private int uprcsQaCnt;
	private int uprcsQaMtcCnt;
	private int addModelCnt;
	private int addModelMtcCnt;
	private int updModelCnt;
	private int updModelMtcCnt;
}