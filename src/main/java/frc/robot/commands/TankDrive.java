// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Utils;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
    /** Creates a new TankDrive. */

    // DriveTrain subsystem
    private final DriveTrain driveTrain;
    private final double scale;
    private final XboxController driver = new XboxController(0);

    public TankDrive(DriveTrain driveTrain) {
        this(driveTrain, 0.5);
    }

    public TankDrive(DriveTrain driveTrain, double speedScale) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.driveTrain = driveTrain;
        this.scale = speedScale;
        addRequirements(this.driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        SmartDashboard.putString("Drive mode", "Tank Drive (speed scale = " + scale + ")");
        driveTrain.stopDriving();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double demandX = scale * driver.getRawAxis(XboxController.Axis.kLeftY.value);
        double demandY = scale * driver.getRawAxis(XboxController.Axis.kRightY.value);

        driveTrain.tankDrive(Utils.deadzone(demandX, 0.05), Utils.deadzone(demandY, 0.05));
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
