package com.enuri.gm.jca.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnAttDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cateCd;
	private int dnAtrId;
	private String dnAtrNm;
	private String dnAtr;
	private int attributeId;
	private String galleryAttributeNm;
	private int attributeElementId;
	private String attributeElement;
	private String enrAtrDtlRngVlu;
	private String useClassCode;
}
