package entity;

public class Cotizacion {
	private String ciclo,evaluacion;
	private Double actual=0.0d,anterior=0.0d;
	/**
	 * @param ciclo
	 * @param anterior
	 */
	public Cotizacion(String ciclo, Double anterior) {
		super();
		this.ciclo = ciclo;
		this.anterior = anterior;
	}
	/**
	 * @param ciclo
	 * @param evaluacion
	 * @param actual
	 * @param anterior
	 */
	public Cotizacion(String ciclo, Double actual, Double anterior) {
		super();
		this.ciclo = ciclo;
		this.actual = actual;
		this.anterior = anterior;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion() {
		String evalua="";
		if (this.actual!=0.0d) {
			if (this.anterior<this.actual) {
				evalua="mayor";
			} else if (this.anterior>this.actual) {
				evalua="menor";
			} else {
				evalua="igual";
			}
		} else {evalua="no cotiza";}
		this.evaluacion = evalua;
	}
	public Double getActual() {
		return actual;
	}
	public void setActual(Double actual) {
		this.actual = actual;
	}
	public Double getAnterior() {
		return anterior;
	}
	public void setAnterior(Double anterior) {
		this.anterior = anterior;
	}
	
	
}
