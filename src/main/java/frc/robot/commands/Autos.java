// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
    /** Example static factory for an autonomous command. */
    public static Command exampleAuto(DriveTrain driveTrain, Arm arm) {
        return new FunctionalCommand(
                () -> arm.setPower(0.4),
                () -> {},
                (Boolean interrupted) -> arm.setPower(0),
                () -> false,
                arm)
            .withTimeout(0.5).andThen(new WaitCommand(3)).andThen(
                new FunctionalCommand(
                // () -> driveTrain.setPowers(new WheelSpeeds(1 * 0.6, 0.65 * 0.6)), // curves to left
                () -> driveTrain.setPowers(new WheelSpeeds(-1 * 0.6, -0.57 * 0.6)),
                // () -> driveTrain.setPowers(new WheelSpeeds(1 * 0.6, 0.4225 * 0.6)),
                () -> {},
                (Boolean interrupted) -> driveTrain.setPowers(new WheelSpeeds(0, 0)),
                () -> false,
                driveTrain).withTimeout(3.3)
            );
            // .withTimeout(1.5);
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
