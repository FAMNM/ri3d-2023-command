package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeHoldOpen extends CommandBase {

    public final Intake intake;

    public IntakeHoldOpen(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setPower(Constants.Intake.HOLD_OPEN_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
