// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Utils;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
    /** Creates a new ArcadeDrive. */

    private final DriveTrain driveTrain;
    private final DoubleSupplier forward;
    private final DoubleSupplier turning;
    
    public ArcadeDrive(DriveTrain driveTrain, DoubleSupplier forwardSupplier, DoubleSupplier turningSupplier) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.driveTrain = driveTrain;
        forward = forwardSupplier;
        turning = turningSupplier;
        addRequirements(this.driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        SmartDashboard.putString("Drive mode", "Arcade drive");
        driveTrain.stopDriving();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveTrain.arcadeDrive(
            Utils.deadzone(forward.getAsDouble(), 0.05),
            Utils.deadzone(turning.getAsDouble(), 0.05),
            true);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.stopDriving();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
