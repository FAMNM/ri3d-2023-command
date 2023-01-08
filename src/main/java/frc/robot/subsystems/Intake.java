package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

	private WPI_VictorSPX clamp;

	public Intake() {

		clamp = new WPI_VictorSPX(Constants.MotorID.CLAMP);

		clamp.setInverted(false); // TODO test this
		// TODO add 

	}

	public void setPower(double pwr) {
		assert pwr >= -1d : "Lower Bound";
		assert pwr <= 1d : "Upper Bound";

		clamp.set(pwr);
	}

	@Override
	public void periodic() {
		
	}

}
