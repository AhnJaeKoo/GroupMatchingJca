package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class TbDnCrwlAtrEnrId implements Serializable{

	private static final long serialVersionUID = 1L;

	private int dnAtrId;
	private int enrAtrId;
	private int enrAtrDtlId;
}
