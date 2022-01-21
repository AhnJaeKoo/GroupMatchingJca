package com.enuri.gm.jca.model.main;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String caCode;
	private String cName;
	private int cSeqno;

}
