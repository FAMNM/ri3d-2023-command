package frc.robot;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TankDriveCubed;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
	private final DriveTrain driveTrain = new DriveTrain();
	private final Arm arm = new Arm();
	private final Intake intake = new Intake();

	// Driver controllers
	private final XboxController driver = new XboxController(Constants.OperatorConstants.DRIVER);
	private final XboxController secondary = new XboxController(Constants.OperatorConstants.CODRIVER);

	// Driver button triggers
	private final Trigger driverA = new JoystickButton(driver, XboxController.Button.kA.value);
	private final Trigger driverB = new JoystickButton(driver, XboxController.Button.kB.value);
	private final Trigger driverX = new JoystickButton(driver, XboxController.Button.kX.value);
	private final Trigger driverY = new JoystickButton(driver, XboxController.Button.kY.value);
	private final Trigger driverLB = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
	private final Trigger driverRB = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
	private final Trigger driverStart = new JoystickButton(driver, XboxController.Button.kStart.value);
	private final Trigger driverBack = new JoystickButton(driver, XboxController.Button.kBack.value);
	private final Trigger driverJoystickLeft = new JoystickButton(driver, XboxController.Button.kLeftStick.value);
	private final Trigger driverJoystickRight = new JoystickButton(driver, XboxController.Button.kRightStick.value);

	// Secondary controller button triggers
	private final Trigger secondaryA = new JoystickButton(secondary, XboxController.Button.kA.value);
	private final Trigger secondaryB = new JoystickButton(secondary, XboxController.Button.kB.value);
	private final Trigger secondaryX = new JoystickButton(secondary, XboxController.Button.kX.value);
	private final Trigger secondaryY = new JoystickButton(secondary, XboxController.Button.kY.value);
	private final Trigger secondaryLB = new JoystickButton(secondary, XboxController.Button.kLeftBumper.value);
	private final Trigger secondaryRB = new JoystickButton(secondary, XboxController.Button.kRightBumper.value);
	private final Trigger secondaryStart = new JoystickButton(secondary, XboxController.Button.kStart.value);
	private final Trigger secondaryBack = new JoystickButton(secondary, XboxController.Button.kBack.value);
	private final Trigger secondaryJoystickLeft = new JoystickButton(secondary, XboxController.Button.kLeftStick.value);
	private final Trigger secondaryJoystickRight = new JoystickButton(secondary,
			XboxController.Button.kRightStick.value);

	private final Trigger driverRightTriggerActivated = new Trigger(() -> driver.getRightTriggerAxis() >= 0.5);
	private final Trigger driverLeftTriggerActivated = new Trigger(() -> driver.getLeftTriggerAxis() >= 0.5);

	// Replace with CommandPS4Controller or CommandJoystick if needed
	// private final CommandXboxController m_driverController =
	// new CommandXboxController(OperatorConstants.kDriverControllerPort);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the trigger bindings
		configureBindings();

	}

	/**
	 * Use this method to define your trigger->command mappings. Triggers can be
	 * created via the
	 * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
	 * an arbitrary
	 * predicate, or via the named factories in {@link
	 * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
	 * {@link
	 * CommandXboxController
	 * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
	 * PS4} controllers or
	 * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
	 * joysticks}.
	 */
	private void configureBindings() {
		driveTrain.setDefaultCommand(new TankDriveCubed(driveTrain));

		driverBack.onTrue(new ArcadeDrive(driveTrain));
		driverStart.onTrue(new TankDrive(driveTrain));
		driverLB.whileTrue(new TankDrive(driveTrain, Constants.TANK_DRIVE_SLOW_FACTOR));
		driverRB.whileTrue(new TankDriveCubed(driveTrain));
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An example command will be run in autonomous
    return null; // TODO: Add autonomous command
	}
}
