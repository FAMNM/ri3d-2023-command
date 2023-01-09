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
		setPowers(DifferentialDrive.arcadeDriveIK(0.6 * xSpeed, 0.5 * zRotation, false)); //tune these
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		setPowers(DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, false));
	}

	public void stopDriving() {
		setPowers(new WheelSpeeds());
	}

	public void setPowers(WheelSpeeds speeds) {
		WheelSpeeds speeds2 = new WheelSpeeds((speeds.left * 0.7) + Math.signum(speeds.left) * 0.3, (speeds.right * 0.7) + Math.signum(speeds.right) * 0.3);
		differentialDrive.tankDrive(speeds2.left, speeds2.right, false);
		SmartDashboard.putNumber("Drivetrain/left speed", speeds.left);
		SmartDashboard.putNumber("Drivetrain/right speed", speeds.right);
		SmartDashboard.putNumber("Drivetrain/left speed2", speeds2.left);
		SmartDashboard.putNumber("Drivetrain/right speed2", speeds2.right);
	}
}
