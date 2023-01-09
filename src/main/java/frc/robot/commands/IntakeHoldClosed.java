package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeHoldClosed extends StartEndCommand {
    public IntakeHoldClosed(Intake intake) {
        super(
            () -> intake.setPower(Constants.Intake.HOLD_POWER),
            () -> intake.setPower(0));
    }
}
