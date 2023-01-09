package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final WPI_VictorSPX clamp;

    public Intake() {
        clamp = new WPI_VictorSPX(Constants.MotorID.CLAMP);
    }

    public void setPower(double power) {
        double bounded = Math.min(Math.max(power, -1), 1);
        if (bounded != power) {
            System.err.println("Intake::setPower received out of range value: " + power + "!");
        }

        clamp.set(power);
    }
}
