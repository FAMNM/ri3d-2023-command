package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeClose extends WaitCommand {
    private final Intake intake;

    public IntakeClose(Intake intake) {
        super(Constants.Intake.CLOSE_TIME);
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();
        intake.setPower(Constants.Intake.CLOSE_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
    }
}
