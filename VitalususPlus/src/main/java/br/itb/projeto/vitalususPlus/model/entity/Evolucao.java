package br.itb.projeto.vitalususPlus.model.entity;

public class Evolucao{
	private long codigo;
	private float imc;
	private float metBasal;
	private float pesoAtual;
	private float alturaAtual;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public float getImc() {
		return imc;
	}

	public void setImc(float imc) {
		this.imc = imc;
	}

	public float getMetBasal() {
		return metBasal;
	}

	public void setMetBasal(float metBasal) {
		this.metBasal = metBasal;
	}

	public float getPesoAtual() {
		return pesoAtual;
	}

	public void setPesoAtual(float pesoAtual) {
		this.pesoAtual = pesoAtual;
	}

	public float getAlturaAtual() {
		return alturaAtual;
	}

	public void setAlturaAtual(float alturaAtual) {
		this.alturaAtual = alturaAtual;
	}

}
