package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class TbDnCrwlQaModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int modelNo;
	@Column(name = "qa_id", insertable = false, updatable = false)
	private long qaId;
	private String modelNm;

	@ManyToOne
	//@Column(insertable = false, updatable = false)	// 수정불가로 해야 조인시 사용가능하다
	@JoinColumn(name = "qa_id")
	private TbDnCrwlQa qa;
}
