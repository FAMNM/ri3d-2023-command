package frc.robot;

import edu.wpi.first.math.geometry.Transform3d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static final double TANK_DRIVE_SLOW_FACTOR = 0.25;

	public static class MotorID {
		// TODO: Find motor ports
		public static final int LEFT_DRIVE_1 = 1;
		public static final int LEFT_DRIVE_2 = 2;
		public static final int RIGHT_DRIVE_1 = 3;
		public static final int RIGHT_DRIVE_2 = 4;
		public static final int ARM = 5;
		public static final int CLAMP = 6;
	}

	public static class OperatorConstants {
		public static final int DRIVER = 0;
		public static final int CODRIVER = 1;
	}

	public static class VisionConstants {
		public static final String FAMILY = "tag16h5";

		public static final Transform3d ROBOT_CENTER_TO_CAMERA = new Transform3d(); // TODO figure this value out
		
		public static final int RESOLUTION_WIDTH = 320;
		public static final int RESOLUTION_HEIGHT = 240;
		public static final double APRILTAG_SIZE = 0.1933; // meters // 0.2032 for regulation field

		// update to camera specifics
		public static final double CAMERA_FX = 0d; // TODO what are these numbers?
		public static final double CAMERA_FY = 0d; // TODO what are these numbers?
		public static final double CAMERA_CX = RESOLUTION_WIDTH / 2; // TODO what are these numbers?
		public static final double CAMERA_CY = RESOLUTION_WIDTH / 2; // TODO what are these numbers?
	}
}
