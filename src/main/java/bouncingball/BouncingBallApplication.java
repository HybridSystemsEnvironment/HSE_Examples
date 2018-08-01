
package bouncingball;

import org.jfree.chart.ChartPanel;

import edu.ucsc.cross.hse.core.chart.ChartUtils;
import edu.ucsc.cross.hse.core.environment.HSEnvironment;
import edu.ucsc.cross.hse.core.figure.Figure;
import edu.ucsc.cross.hse.core.trajectory.HybridTime;
import edu.ucsc.cross.hse.core.trajectory.TrajectorySet;

/**
 * A bouncing ball application that prepares and operates the environment, and
 * generates a figure.
 * 
 * @author Brendan Short
 *
 */
public class BouncingBallApplication {

	/**
	 * Main method for running application
	 * 
	 * @param args
	 *            none
	 */
	public static void main(String args[]) {

		// Initialize environment
		HSEnvironment environment = new HSEnvironment();
		// Initialize bouncing ball parameters (restitution, gravity)
		BouncingBallParameters parameters = new BouncingBallParameters(.97, 9.81);
		// Initialize bouncing ball state (y position, y velocity)
		BouncingBallState state = new BouncingBallState(1.0, 1.0);
		// Initialize bouncing ball system
		BouncingBallSystem system = new BouncingBallSystem(state, parameters);
		// Add bouncing ball system to environment
		environment.getSystems().add(system);
		// Run environment (max time duration, max jumps)
		environment.run(10.0, 10);
		// Generate a figure of the trajectories
		Figure figure = generateVerticalStateFigure(environment.getTrajectories());
		// Display the figure in new window
		figure.display();

	}

	/**
	 * Generate a figure with the vertical (y position and velocity) bouncing ball
	 * state elements
	 * 
	 * @param solution
	 *            trajectory set containing data to load into figure
	 * @return a figure displaying all vertical bouncing ball state elements
	 */
	public static Figure generateVerticalStateFigure(TrajectorySet solution) {

		// Create figure w:1000 h:600
		Figure figure = new Figure(1000, 600);
		// Assign title to figure
		figure.getTitle().setText("Bouncing Ball Simulation");
		// Create charts
		ChartPanel yPos = ChartUtils.createPanel(solution, HybridTime.TIME, "yPosition");
		ChartPanel yVel = ChartUtils.createPanel(solution, HybridTime.TIME, "yVelocity");
		// Label chart axis and configure legend visibility
		ChartUtils.configureLabels(yPos, "Time (sec)", "Y Position (m)", null, false);
		ChartUtils.configureLabels(yVel, "Time (sec)", "Y Velocity (m/s)", null, false);
		// Add charts to figure
		figure.addComponent(0, 0, yPos);
		figure.addComponent(0, 1, yVel);
		// Return generated figure
		return figure;
	}

}