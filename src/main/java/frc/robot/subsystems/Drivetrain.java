// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.DrivetrainConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Drivetrain extends SubsystemBase {
  private static SparkMax motorLeftFront;
  private static SparkMax motorRightFront; 
  private static SparkMax motorLeftRear;
  private static SparkMax motorRightRear; 
  private static DifferentialDrive drivetrain;
  
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    motorLeftFront = new SparkMax(DrivetrainConstants.kLeftFrontID, MotorType.kBrushed);   
    motorLeftRear = new SparkMax(DrivetrainConstants.kLeftRearID, MotorType.kBrushed);
    motorRightFront = new SparkMax(DrivetrainConstants.kRightFrontID, MotorType.kBrushed);
    motorRightRear = new SparkMax(DrivetrainConstants.kRightRearID, MotorType.kBrushed);

    drivetrain = new DifferentialDrive(motorLeftFront, motorRightFront);

    motorLeftFront.setCANTimeout(250);
    motorLeftRear.setCANTimeout(250);
    motorRightFront.setCANTimeout(250);
    motorRightRear.setCANTimeout(250);

    SparkMaxConfig config = new SparkMaxConfig();
    config.voltageCompensation(12);
    config.smartCurrentLimit(DrivetrainConstants.kDrivetrainSafetyLimits);

    config.follow(motorLeftFront);
    motorLeftRear.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    config.follow(motorRightFront);
    motorRightRear.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    config.disableFollowerMode();
    motorRightFront.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    config.inverted(true);
    motorLeftFront.configure(config,  ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void arcadeDrive(double speed, double rotation) {
    drivetrain.arcadeDrive(speed, rotation);
  }

  public void stop() {
    drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}