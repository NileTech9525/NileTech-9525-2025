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
    public static final int kLeftRearID = 2;
    public static final int kLeftFrontID = 1;
    public static final int kRightRearID = 4;
    public static final int kRightFrontID = 3;
    public static final int kDrivetrainSafetyLimits = 60;
    public static final double kSlowDriveSpeed = 0.6;
  }

  public static class CoralConstants {
    public static final int kCoralID = 5;
    public static final int kCoralSafetyLimits = 60;
    public static final double kCoralSpeed = 0.5;
  }

  public static class ElevatorConstants {
    public static final int kLeaderID = 6;
    public static final int kFollowerID = 7;
    public static final int kElevatorSafetyLimits = 60;
    public static final double KElevatorSpeed = 1;
    
    public static final double kPIDTolerance = 0.7;
    public static final double kP = 0.5;
    public static final double kI = 0.03;
    public static final double kD = 0.01;

    public static final double kGroundHeight = -2.0;
    public static final double KAutoHeight = -16.7;
    public static final double kLowAlgaeHeight = -40.0;
    public static final double kHighAlgaeHeight = -57;
    public static final double kMinPosition = -2.0;
    public static final double kMaxPosition = -58.0;
    public static final double kMaxVelocity = 0.5;
    public static final double kMaxAcceleration = 3.0;
  }

  public static class ClawConstants {
    public static final int kClawSafetyLimits = 60;
    public static final int kClawRightID = 8;
    public static final int kClawLeftID = 9;
    public static final double kClawSpeed = 0.5;
  }

  public static class AutoConstants {
    public static final double kDriveTime = 2.0;
    public static final double kCoralTime = 0.6;
  }
}
