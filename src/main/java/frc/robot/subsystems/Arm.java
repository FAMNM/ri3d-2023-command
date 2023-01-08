package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

	private final WPI_VictorSPX pivot;
	private final Encoder encoder;
	private final ArmFeedforward feedforward;
	private final PIDController pidController;
	private double setpoint;

	public Arm() {

		pivot = new WPI_VictorSPX(Constants.MotorID.ARM);

		pivot.setInverted(false); // TODO test this
		// TODO add

		encoder = new Encoder(
			Constants.Arm.ENCODER_PORT_A,
			Constants.Arm.ENCODER_PORT_B,
			Constants.Arm.ENCODER_REVERSED,
			Constants.Arm.ENCODING_TYPE);
		encoder.setDistancePerPulse(Constants.Arm.DISTANCE_PER_ENCODER_PULSE);
		encoder.setSamplesToAverage(((int) SmartDashboard.getNumber("Arm encoder samples to average", 0)));

		pidController = new PIDController(
			Constants.Arm.KP,
			Constants.Arm.KI,
			Constants.Arm.KD);

		feedforward = new ArmFeedforward(
			Constants.Arm.STATIC_GAIN,
			Constants.Arm.GRAVITY_GAIN,
			Constants.Arm.VELOCITY_GAIN);
	}

	public void setPower(double pwr) {
		assert pwr >= -1d : "Lower Bound";
		assert pwr <= 1d : "Upper Bound";

		pivot.set(pwr);
	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}

	@Override
	public void periodic() {
		double pidOutput = pidController.calculate(encoder.getDistance(), setpoint);
		double feedforwardOutput = feedforward.calculate(encoder.getDistance(), encoder.getRate());
	}

}
