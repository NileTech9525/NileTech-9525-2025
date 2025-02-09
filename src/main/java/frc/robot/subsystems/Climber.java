package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
  
  private final SparkMax climberMotor = new SparkMax(ClimberConstants.kClimberID, MotorType.kBrushless);
  private final RelativeEncoder climberEncoder = climberMotor.getEncoder();

  public Climber() {
    SparkMaxConfig climberConfig = new SparkMaxConfig();
    climberConfig.inverted(false); // Adjust based on motor direction
    climberConfig.smartCurrentLimit(ClimberConstants.kClimberSafetyLimits);
    climberConfig.idleMode(IdleMode.kBrake); // Typically set to brake for climbers
    climberMotor.configure(climberConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    climberEncoder.setPosition(0);
  }

  public void setSpeed(double speed) {
    climberMotor.set(speed);
  }

  public void stop() {
    climberMotor.set(0);
  }

  public double getPositionMeters() {
    return (climberEncoder.getPosition() * ClimberConstants.kClimberDistancePerRotation);
  }

  public void resetPosition() {
    climberEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // Send data to SmartDashboard
    SmartDashboard.putNumber("Climber Position", getPositionMeters());
  }
}
