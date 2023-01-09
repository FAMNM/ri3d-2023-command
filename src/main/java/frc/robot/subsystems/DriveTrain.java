// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    /** Creates a new DriveTrain. */

    private final WPI_VictorSPX leftDrive1;
    private final WPI_VictorSPX leftDrive2;
    private final WPI_VictorSPX rightDrive1;
    private final WPI_VictorSPX rightDrive2;

    private final MotorControllerGroup leftDrive;
    private final MotorControllerGroup rightDrive;

    private final DifferentialDrive differentialDrive;

    public DriveTrain() {

        leftDrive1 = new WPI_VictorSPX(Constants.MotorID.LEFT_DRIVE_1);
        leftDrive2 = new WPI_VictorSPX(Constants.MotorID.LEFT_DRIVE_2);
        rightDrive1 = new WPI_VictorSPX(Constants.MotorID.RIGHT_DRIVE_1);
        rightDrive2 = new WPI_VictorSPX(Constants.MotorID.RIGHT_DRIVE_2);

        rightDrive1.setInverted(false);
        rightDrive2.setInverted(true);
    
        leftDrive = new MotorControllerGroup(leftDrive1, leftDrive2);
        rightDrive = new MotorControllerGroup(rightDrive1, rightDrive2);

        leftDrive.setInverted(true);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
        differentialDrive.setSafetyEnabled(false);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    } // Famnms really awesome dude

    public void arcadeDrive(double xSpeed, double zRotation) {
        arcadeDrive(xSpeed, zRotation, false);
    }

    public void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {
        setPowers(DifferentialDrive.arcadeDriveIK(xSpeed, zRotation, squareInputs));
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        tankDrive(leftSpeed, rightSpeed, false);
    }

    public void tankDrive(double leftSpeed, double rightSpeed, boolean squareInputs) {
        setPowers(DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, squareInputs));
    }

    public void stopDriving() {
        setPowers(new WheelSpeeds());
    }

    public void setPowers(WheelSpeeds speeds) {
        WheelSpeeds adjusted = new WheelSpeeds(
            (speeds.left * (1 - Constants.DriveTrain.MIN_MOTOR_OUTPUT)) + Math.signum(speeds.left) * Constants.DriveTrain.MIN_MOTOR_OUTPUT,
            (speeds.right * (1 - Constants.DriveTrain.MIN_MOTOR_OUTPUT)) + Math.signum(speeds.right) * Constants.DriveTrain.MIN_MOTOR_OUTPUT);
        differentialDrive.tankDrive(-adjusted.left, -adjusted.right, false);
        SmartDashboard.putNumber("Drivetrain/left demand", speeds.left);
        SmartDashboard.putNumber("Drivetrain/right demand", speeds.right);
        SmartDashboard.putNumber("Drivetrain/left output", adjusted.left);
        SmartDashboard.putNumber("Drivetrain/right output", adjusted.right);
    }
}
