package model;

public class MedicalResults {

	private String ta;
	private String av;
	private String weight;
	private String height;
	private String imc;
	private String circumference;
	private String glucose;
	private String cholesterol;
	private String ldl_c;
	private String hdl_c;
	private String triglicerides;
	private String potassium;
	private String sodium;
	private String uric_acid;
	private String creatinine;
	private String microalbuminuria;
	private String urinary_protein;
	private String urinary_creatinine;
	private String risk_factor;

	public MedicalResults(String ta, String av, String weight, String height, String imc, String circumference,
			String glucose, String cholesterol, String ldl_c, String hdl_c, String triglicerides, String potassium,
			String sodium, String uric_acid, String creatinine, String microalbuminuria, String urinary_protein,
			String urinary_creatinine, String risk_factor) {
		super();
		this.ta = ta;
		this.av = av;
		this.weight = weight;
		this.height = height;
		this.imc = imc;
		this.circumference = circumference;
		this.glucose = glucose;
		this.cholesterol = cholesterol;
		this.ldl_c = ldl_c;
		this.hdl_c = hdl_c;
		this.triglicerides = triglicerides;
		this.potassium = potassium;
		this.sodium = sodium;
		this.uric_acid = uric_acid;
		this.creatinine = creatinine;
		this.microalbuminuria = microalbuminuria;
		this.urinary_protein = urinary_protein;
		this.urinary_creatinine = urinary_creatinine;
		this.risk_factor = risk_factor;
	}

	public String getRisk_factor() {
		return risk_factor;
	}

	public void setRisk_factor(String risk_factor) {
		this.risk_factor = risk_factor;
	}

	public String getTa() {
		return ta;
	}

	public void setTa(String ta) {
		this.ta = ta;
	}

	public String getAv() {
		return av;
	}

	public void setAv(String av) {
		this.av = av;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public String getCircumference() {
		return circumference;
	}

	public void setCircumference(String circumference) {
		this.circumference = circumference;
	}

	public String getGlucose() {
		return glucose;
	}

	public void setGlucose(String glucose) {
		this.glucose = glucose;
	}

	public String getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getLdl_c() {
		return ldl_c;
	}

	public void setLdl_c(String ldl_c) {
		this.ldl_c = ldl_c;
	}

	public String getHdl_c() {
		return hdl_c;
	}

	public void setHdl_c(String hdl_c) {
		this.hdl_c = hdl_c;
	}

	public String getTriglicerides() {
		return triglicerides;
	}

	public void setTriglicerides(String triglicerides) {
		this.triglicerides = triglicerides;
	}

	public String getPotassium() {
		return potassium;
	}

	public void setPotassium(String potassium) {
		this.potassium = potassium;
	}

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	public String getUric_acid() {
		return uric_acid;
	}

	public void setUric_acid(String uric_acid) {
		this.uric_acid = uric_acid;
	}

	public String getCreatinine() {
		return creatinine;
	}

	public void setCreatinine(String creatinine) {
		this.creatinine = creatinine;
	}

	public String getMicroalbuminuria() {
		return microalbuminuria;
	}

	public void setMicroalbuminuria(String microalbuminuria) {
		this.microalbuminuria = microalbuminuria;
	}

	public String getUrinary_protein() {
		return urinary_protein;
	}

	public void setUrinary_protein(String urinary_protein) {
		this.urinary_protein = urinary_protein;
	}

	public String getUrinary_creatinine() {
		return urinary_creatinine;
	}

	public void setUrinary_creatinine(String urinary_creatinine) {
		this.urinary_creatinine = urinary_creatinine;
	}

}
