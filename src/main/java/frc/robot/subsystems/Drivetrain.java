// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.studica.frc.AHRS;

import frc.robot.Constants.DrivetrainConstants;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;

public class Drivetrain extends SubsystemBase {
  private static SparkMax motorLeftFront = new SparkMax(DrivetrainConstants.kLeftFrontID, MotorType.kBrushed);   
  private static SparkMax motorRightFront = new SparkMax(DrivetrainConstants.kRightFrontID, MotorType.kBrushed);
  private static SparkMax motorLeftRear = new SparkMax(DrivetrainConstants.kLeftRearID, MotorType.kBrushed);
  private static SparkMax motorRightRear = new SparkMax(DrivetrainConstants.kRightRearID, MotorType.kBrushed);
  private static DifferentialDrive drivetrain = new DifferentialDrive(motorLeftFront::set, motorRightFront::set);
  private static RelativeEncoder rightEncoder = motorRightFront.getEncoder();
  private static RelativeEncoder leftEncoder = motorLeftFront.getEncoder();
  private AHRS navx = new AHRS(AHRS.NavXComType.kUSB1);

  public Drivetrain() {
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    rightLeaderConfig.inverted(true);
    rightLeaderConfig.smartCurrentLimit(DrivetrainConstants.kDrivetrainSafetyLimits); 
    rightLeaderConfig.idleMode(IdleMode.kCoast);
    motorRightFront.configure(rightLeaderConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
        
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
    rightFollowerConfig.follow(motorRightFront);
    rightFollowerConfig.smartCurrentLimit(DrivetrainConstants.kDrivetrainSafetyLimits); 
    rightFollowerConfig.idleMode(IdleMode.kCoast);
    motorRightRear.configure(rightFollowerConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();
    leftLeaderConfig.inverted(true);
    leftLeaderConfig.smartCurrentLimit(DrivetrainConstants.kDrivetrainSafetyLimits); 
    leftLeaderConfig.idleMode(IdleMode.kCoast);
    motorLeftFront.configure(leftLeaderConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    leftFollowerConfig.follow(motorLeftFront);
    leftFollowerConfig.smartCurrentLimit(DrivetrainConstants.kDrivetrainSafetyLimits); 
    leftFollowerConfig.idleMode(IdleMode.kCoast);
    motorLeftRear.configure(leftFollowerConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    rightEncoder.setPosition(0);
    leftEncoder.setPosition(0);

    drivetrain.setDeadband(0.02);

    SendableRegistry.addChild(drivetrain, motorLeftFront);
    SendableRegistry.addChild(drivetrain, motorRightFront);
   }
  
  public void arcadeDrive(double speed, double rotation) {
    drivetrain.arcadeDrive(speed, rotation);
  }

  public void stop(double speed, double rotation) {
    drivetrain.arcadeDrive(0, 0);
  }

  public double getAverageDistance(){
      return (rightEncoder.getPosition() + leftEncoder.getPosition()) / 2;
  }

  public void zeroGyro() {
		navx.reset();
	}

  public boolean isConnected() {
    return navx.isConnected();
  }
  
  public double getYaw() {
		return navx.getYaw();
	}
	
  public double getPitch() {
		return navx.getPitch();
	}
	
  public double getRoll() {
		return navx.getRoll();
	}
	
  public double getGyroAngle() { // Returns the heading of the robot
		return navx.getAngle();
	}
	
  public double getTurnRate() { // Returns the turn rate of the robot
		return -navx.getRate();
	}

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distance", getAverageDistance());
    SmartDashboard.putBoolean("NavX", isConnected());
  }
}