package frc.robot;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.IntakeClose;
import frc.robot.commands.IntakeHoldClosed;
import frc.robot.commands.IntakeOpen;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TankDriveCubed;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
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
	private final CommandXboxController commandDriver = new CommandXboxController(Constants.OperatorConstants.DRIVER);
	private final XboxController secondary = new XboxController(Constants.OperatorConstants.CODRIVER);
	private final CommandXboxController commandSecondary = new CommandXboxController(Constants.OperatorConstants.CODRIVER);

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

		commandDriver.back().onTrue(new ArcadeDrive(driveTrain));
		commandDriver.start().onTrue(new TankDrive(driveTrain));
		commandDriver.leftBumper().whileTrue(new TankDrive(driveTrain, Constants.TANK_DRIVE_SLOW_FACTOR));
		commandDriver.rightBumper().whileTrue(new TankDriveCubed(driveTrain));

		commandSecondary.leftBumper().onTrue(new IntakeOpen(intake));
		commandSecondary.rightBumper().onTrue(new IntakeClose(intake).andThen(new IntakeHoldClosed(intake)));
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
