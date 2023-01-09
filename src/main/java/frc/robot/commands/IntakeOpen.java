package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeOpen extends WaitCommand {
    
    private final Intake intake;

    public IntakeOpen(Intake intake) {
        super(Constants.Intake.OPEN_TIME);
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();
        intake.setPower(Constants.Intake.OPEN_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        intake.setPower(0);
    }
}
