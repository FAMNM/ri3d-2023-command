// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Utils;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
    /** Creates a new TankDrive. */

    // DriveTrain subsystem
    private final DriveTrain driveTrain;
    private final double scale;
    private final DoubleSupplier left;
    private final DoubleSupplier right;

    public TankDrive(DriveTrain driveTrain, DoubleSupplier leftSupplier, DoubleSupplier rightSupplier) {
        this(driveTrain, 0.5, leftSupplier, rightSupplier);
    }

    public TankDrive(DriveTrain driveTrain, double speedScale, DoubleSupplier leftSupplier, DoubleSupplier rightSupplier) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.driveTrain = driveTrain;
        this.scale = speedScale;
        this.left = leftSupplier;
        this.right = rightSupplier;
        addRequirements(this.driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        SmartDashboard.putString("Drive mode", "Tank Drive Linear (speed scale = " + scale + ")");
        driveTrain.stopDriving();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double demandX = scale * left.getAsDouble();
        double demandY = scale * right.getAsDouble();

        driveTrain.tankDrive(Utils.deadzone(demandX, 0.05), Utils.deadzone(demandY, 0.05));
        // driveTrain.tankDrive(0.5 * Utils.deadzone(demandX, 0.05), 0.4 * Utils.deadzone(demandY, 0.05));
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
