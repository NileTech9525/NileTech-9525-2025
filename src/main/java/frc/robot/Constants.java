// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 0;
  }

  public static class DrivetrainConstants {
    public static final int kLeftRearID = 1;
    public static final int kLeftFrontID = 2;
    public static final int kRightRearID = 4;
    public static final int kRightFrontID = 3;
    public static final int kDrivetrainSafetyLimits = 60;
    public static final double kSlowDriveSpeed = 0.6;
  }

  public static class ElevatorConstants {
    public static final int kFollowerID = 6;
    public static final int kLeaderID = 5;
    public static final int kElevatorSafetyLimits = 40;
    public static final double kElevatorDistancePerRotation = 0.0157;
    public static final double kTolerance = 0.1;

  }

  public static class ClawConstants{
    public static final int kClawID = 7;
    public static final int kClawSafetyLimits = 40;
    public static final double kClawDistancePerRotation = 0;
    public static final double kP = 0.1;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
    public static final double kFF = 0.000156;
    public static final double kVelocityConversion = 1;
    public static final double kAlgaeTarget = 0;
    public static final double kCoralTarget = 0;
  }

  public static class ClimberConstants{
    public static final int kClimberID = 8;
    public static final int kClimberSafetyLimits = 40;
    public static final double kClimberDistancePerRotation = 0;
    public static final double kMaxClimbHeight = 0;
    public static final double kClimberUpSpeed = 0;
    public static final double kClimberDownSpeed = 0;
  }

  public static class VisionConstants {
    public static final double KCameraHeightMeters = 0;
    public static final double KTargetHeightMeters = 0;
    public static final double KCameraPitchRadian = 0;
    public static final String KCameraName = null;

  }
}
