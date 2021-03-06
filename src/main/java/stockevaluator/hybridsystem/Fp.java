
package stockevaluator.hybridsystem;

import edu.ucsc.cross.hse.core.modeling.FlowMap;

public class Fp implements FlowMap<State> {

	@Override
	public void F(State x, State x_dot) {

		for (SubState stock : x_dot.stocks) // iterate through evaluated stocks
		{
			stock.stockSlope = 0.0; // set stock slope derivative

			stock.stockValue = 0.0; // set stock value derivative

			stock.stockLogSlope = 0.0;
		}

		x_dot.evaluationTimer = -1.0; // set the evaluation timer derivative

		x_dot.bestSlopeValue = 0.0; // set the best slope value derivative

		x_dot.bestLogSlopeValue = 0.0; // set the best log slope derivative

		x_dot.worstSlopeValue = 0.0; // set the worst slope value derivative
	}
}
