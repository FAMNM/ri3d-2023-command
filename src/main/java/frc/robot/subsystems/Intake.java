package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

	private final WPI_VictorSPX clamp;

	public Intake() {

		clamp = new WPI_VictorSPX(Constants.MotorID.CLAMP);

		clamp.setInverted(false); // TODO test this
		// TODO add 

	}

	public void setPower(double pwr) {
		double boundedPower = Math.min(Math.max(pwr, -1), 1);
		if (boundedPower != pwr) {
			System.err.println("Intake::setPower received out of range value: " + pwr + "!");
		}

		clamp.set(pwr);
	}

	@Override
	public void periodic() {
		
	}

}
