// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDriveSlow extends CommandBase {
  /** Creates a new TankDriveSlow. */

  private final DriveTrain driveTrain;
  private final XboxController driver = new XboxController(0);

  public TankDriveSlow(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    addRequirements(this.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    driveTrain.stopDriving();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    driveTrain.tankDrive(0.25 * driver.getRawAxis(XboxController.Axis.kLeftY.value), 0.25 * driver.getRawAxis(XboxController.Axis.kRightY.value));
  
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