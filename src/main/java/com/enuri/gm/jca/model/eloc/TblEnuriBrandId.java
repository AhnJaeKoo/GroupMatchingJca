package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor // 생성자 자동 생성
@Setter
@Getter
@Embeddable
public class TblEnuriBrandId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "brand_id")
	private int brandId;
	@Column(name = "maker_id")
	private int makerId;
}
