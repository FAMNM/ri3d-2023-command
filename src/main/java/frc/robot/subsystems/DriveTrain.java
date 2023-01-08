// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  WPI_VictorSPX leftDrive1;
  WPI_VictorSPX leftDrive2;
  WPI_VictorSPX rightDrive1;
  WPI_VictorSPX rightDrive2;
  
  MotorControllerGroup leftDrive;
  MotorControllerGroup rightDrive;

  DifferentialDrive differentialDrive;


  public DriveTrain() {

    leftDrive1 = new WPI_VictorSPX(6);
    leftDrive2 = new WPI_VictorSPX(7);
    rightDrive1 = new WPI_VictorSPX(8);
    rightDrive2 = new WPI_VictorSPX(9);

    rightDrive1.setInverted(false);
    rightDrive2.setInverted(true);

    leftDrive = new MotorControllerGroup(leftDrive1, leftDrive2);
    rightDrive = new MotorControllerGroup(rightDrive1, rightDrive2);

    differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
    differentialDrive.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  } // Famnms really awesome dude

  public void arcadeDrive(double xSpeed, double zRotation) {

    if(Math.abs(xSpeed) >= 0.05 || Math.abs(zRotation) >= 0.05) {

      differentialDrive.arcadeDrive(.6 * xSpeed, .5 * zRotation); // Fine tune these coefficients

    } else {

      differentialDrive.arcadeDrive(0, 0);

    }

  }

  
  public void tankDrive(double leftSpeed, double rightSpeed) {
  
    differentialDrive.tankDrive( (leftSpeed < 0.05) ? (0) : (leftSpeed), (rightSpeed < 0.05) ? (0) : (rightSpeed));
  
  }

  public void stopDriving() {

    differentialDrive.tankDrive(0, 0);
    
  }



}