
package inverter.application;

import edu.ucsc.cross.hse.core.environment.EnvironmentSettings;
import edu.ucsc.cross.hse.core.environment.HSEnvironment;
import edu.ucsc.cross.hse.core.integrator.DormandPrince853IntegratorFactory;
import edu.ucsc.cross.hse.core.logging.Console;
import edu.ucsc.cross.hse.core.logging.ConsoleSettings;
import edu.ucsc.cross.hse.core.specification.DomainPriority;

/**
 * An environment and console setting configurer
 */
public class ConfigurationLoader {

	/**
	 * Configures the environment settings
	 * 
	 * @return EnvironmentSettings
	 */
	public static void loadEnvironmentSettings(HSEnvironment environment) {

		// Create engine settings
		EnvironmentSettings settings = environment.getSettings();
		// Specify general parameter values
		settings.maximumJumps = 1000000;
		settings.maximumTime = .08;
		settings.dataPointInterval = .05;
		settings.eventHandlerMaximumCheckInterval = 1E-3;
		settings.eventHandlerConvergenceThreshold = 1E-9;
		settings.maxEventHandlerIterations = 100;
		settings.domainPriority = DomainPriority.JUMP;
		settings.storeNonPrimativeData = false;
		// Specify integrator parameter values
		double odeMinimumStepSize = 1e-12;
		double odeMaximumStepSize = 1e-7;
		double odeRelativeTolerance = 1.0e-6;
		double odeSolverAbsoluteTolerance = 1.0e-6;
		// Create and store integrator factory
		DormandPrince853IntegratorFactory integrator = new DormandPrince853IntegratorFactory(odeMaximumStepSize,
				odeMinimumStepSize, odeRelativeTolerance, odeSolverAbsoluteTolerance);
		environment.getSettings().integrator = integrator;
	}

	/**
	 * Configures and loads console settings
	 */
	public static void loadConsoleSettings() {

		// Create new console settings
		ConsoleSettings console = new ConsoleSettings();
		// Configure message type visibility
		console.printInfo = true;
		console.printDebug = false;
		console.printWarning = true;
		console.printError = true;
		// Configure status messages
		console.printIntegratorExceptions = false;
		console.printStatusInterval = 10.0;
		console.printProgressIncrement = 10;
		// Configure input and output handling
		console.printLogToFile = true;
		console.terminateAtInput = true;
		// Load configured settings
		Console.setSettings(console);
	}

}
