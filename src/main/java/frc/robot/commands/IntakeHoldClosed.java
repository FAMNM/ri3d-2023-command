package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeHoldClosed extends CommandBase {

    public final Intake intake;

    public IntakeHoldClosed(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setPower(Constants.Intake.HOLD_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
    }
}
