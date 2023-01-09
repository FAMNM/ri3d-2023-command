package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.opencv.core.Mat;

import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.apriltag.AprilTagDetector;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagPoseEstimator;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Vision extends SubsystemBase {

    private final UsbCamera camera;
    private final CvSink cvSink;
    private Mat mat = new Mat(); // image
    private final AprilTagDetector detector;
    private final AprilTagPoseEstimator poseEstimator;
    private final Map<Integer, Transform3d> poseEstimations;
    private final Optional<AprilTagFieldLayout> field;

    public Vision() {
        // Start & Config Camera
        camera = CameraServer.startAutomaticCapture();
        camera.setResolution(Constants.VisionConstants.RESOLUTION_WIDTH, Constants.VisionConstants.RESOLUTION_WIDTH);
        cvSink = CameraServer.getVideo();

        // Configure April Tag Field
        field = getField();

        // April Tag Detector Initialization
        detector = new AprilTagDetector();
        detector.addFamily(Constants.VisionConstants.FAMILY);

        // April Tag Pose Estimator Initialization
        AprilTagPoseEstimator.Config estimatorConfig = new AprilTagPoseEstimator.Config(
                Constants.VisionConstants.APRILTAG_SIZE,
                Constants.VisionConstants.CAMERA_CX,
                Constants.VisionConstants.CAMERA_CY,
                Constants.VisionConstants.CAMERA_FX,
                Constants.VisionConstants.CAMERA_FY);
        poseEstimator = new AprilTagPoseEstimator(estimatorConfig);
        poseEstimations = new TreeMap<Integer, Transform3d>();
    }

    @Override
    public void periodic() {

        // Read Frame from Camera
        if (cvSink.grabFrame(mat) == 0)
            return;

        // WPILib April Tag Recognition & Pose Estimation
        AprilTagDetection[] detections = detector.detect(mat);
        poseEstimations.clear();
        for (var detection : detections) {
            poseEstimations.put(detection.getId(), poseEstimator.estimate(detection));
        }

        if (field.isPresent()) {
            analyze();
        }

        // Display robot offset to grid on smartdashboard
        if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
            if (poseEstimations.containsKey(1)) {
                SmartDashboard.putNumber("grid_offset", poseEstimations.get(1).getY());
            } else if (poseEstimations.containsKey(2)) {
                SmartDashboard.putNumber("grid_offset", poseEstimations.get(2).getY());
            } else if (poseEstimations.containsKey(3)) {
                SmartDashboard.putNumber("grid_offset", poseEstimations.get(2).getY());
            }
        } else {
            if (poseEstimations.containsKey(6)) {
                SmartDashboard.putNumber("grid_offset", poseEstimations.get(6).getY());
            } else if (poseEstimations.containsKey(7)) {
                SmartDashboard.putNumber("grid_offset", poseEstimations.get(7).getY());
            } else if (poseEstimations.containsKey(8)) {
                SmartDashboard.putNumber("grid_offset", poseEstimations.get(8).getY());
            }
        }
    }

    private Optional<AprilTagFieldLayout> getField() {
        try {
            return Optional.of(AprilTagFieldLayout
                    .loadFromResource((new File(Filesystem.getDeployDirectory(), "2023-chargedup.json")).toString()));
        } catch (IOException ioe) {
            System.err.println("Failed to open April Tag Field Location Configuration File");
            ioe.printStackTrace();
            return Optional.empty();
        }
    }

    private void analyze() {
        // Convert Robot-Relative April Tg Pose to Robot Pos on Field
        Translation2d avgRobotPos = new Translation2d();
        Rotation2d avgRobotRot = new Rotation2d();

        for (var estimate : poseEstimations.entrySet()) {
            // Transform locations of april tags to get robot pos
            Pose3d tagPose = field.get().getTagPose(estimate.getKey()).get();
            Transform3d tagToRobot = Constants.VisionConstants.ROBOT_CENTER_TO_CAMERA.plus(estimate.getValue());
            Pose3d robot3d = tagPose.transformBy(tagToRobot.inverse());
            Pose2d robot2d = robot3d.toPose2d(); // At this point Z should be 0

            // Sum pos components
            avgRobotPos.plus(robot2d.getTranslation());
            avgRobotRot.plus(robot2d.getRotation());
        }

        // Take average of cumulative poses
        avgRobotPos.times(1d / ((double) poseEstimations.size()));
        avgRobotRot.times(1d / ((double) poseEstimations.size()));
        Pose2d robotPose = new Pose2d(avgRobotPos, avgRobotRot);
        SmartDashboard.putString("robot_pose", robotPose.toString());
    }
}
