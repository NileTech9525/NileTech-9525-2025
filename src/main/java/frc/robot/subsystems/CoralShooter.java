// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralConstants;
import frc.robot.Constants.DrivetrainConstants;

public class CoralShooter extends SubsystemBase {
  private final SparkMax coralShooter = new SparkMax(CoralConstants.kCoralID, MotorType.kBrushed);
  /** Creates a new CoralShooter. */
  public CoralShooter() {
  SparkMaxConfig coralConfig = new SparkMaxConfig();
    coralConfig.inverted(false); // Adjust based on motor direction
    coralConfig.smartCurrentLimit(CoralConstants.kCoralSafetyLimits);
    coralConfig.idleMode(IdleMode.kBrake); // Typically set to brake for elevators
    coralConfig.voltageCompensation(DrivetrainConstants.KVoltage);
    coralShooter.configure(coralConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void setSpeed(double speed) {
    coralShooter.set(-speed);
  }

  public void stop() {
    coralShooter.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}