package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

    private final WPI_VictorSPX pivot;
    // private final Encoder encoder;
    // private final ArmFeedforward feedforward;
    // private final PIDController pidController;
    // private double setpoint;

    public Arm() {
        pivot = new WPI_VictorSPX(Constants.MotorID.ARM);
        SmartDashboard.putNumber("Arm Power", 0);
    }

    public void setPower(double power) {
        double clamped = Math.max(Math.min(power, 1), -1);
        if (clamped != power) {
            System.err.println("Arm received out of range power " + power + "!");
            System.err.println("Clamping to " + clamped);
        }
        pivot.set(clamped);
    }

    @Override
    public void periodic() {
    }

}
