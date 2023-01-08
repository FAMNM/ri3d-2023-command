package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

	private final WPI_VictorSPX pivot;

	public Arm() {

		pivot = new WPI_VictorSPX(Constants.MotorID.ARM);

		pivot.setInverted(false); // TODO test this
		// TODO add 

	}

	public void setPower(double pwr) {
		assert pwr >= -1d : "Lower Bound";
		assert pwr <= 1d : "Upper Bound";

		pivot.set(pwr);
	}

	@Override
	public void periodic() {
		
	}

}
