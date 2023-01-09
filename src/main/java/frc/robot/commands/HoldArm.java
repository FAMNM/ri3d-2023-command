// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class HoldArm extends CommandBase {
  /** Creates a new RunArm. */

  private final Arm arm;
  private final double power;

  public HoldArm(Arm arm, double power) {
    this.arm = arm;
    this.power = power;
    addRequirements(arm);
  }

  @Override
  public void initialize() {
    arm.setPower(power);
  }

  @Override
  public void end(boolean interrupted) {
    arm.setPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
