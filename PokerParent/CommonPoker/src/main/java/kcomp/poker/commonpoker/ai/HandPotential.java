package kcomp.poker.commonpoker.ai;

public class HandPotential {

	double posPotential;
	double negPotential;

	public HandPotential(double posPotential, double negPotential) {
		super();
		this.posPotential = posPotential;
		this.negPotential = negPotential;
	}

	public double getPosPotential() {
		return posPotential;
	}

	public void setPosPotential(double posPotential) {
		this.posPotential = posPotential;
	}

	public double getNegPotential() {
		return negPotential;
	}

	public void setNegPotential(double negPotential) {
		this.negPotential = negPotential;
	}

}
