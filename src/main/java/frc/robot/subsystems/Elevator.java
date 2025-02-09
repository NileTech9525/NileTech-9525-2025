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
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  
  private final SparkMax elevatorLeader = new SparkMax(ElevatorConstants.kLeaderID, MotorType.kBrushless);
  private final SparkMax elevatorFollower = new SparkMax(ElevatorConstants.kFollowerID, MotorType.kBrushless);
  private final RelativeEncoder elevatorEncoder = elevatorLeader.getEncoder();

  public Elevator() {
    SparkMaxConfig leaderConfig = new SparkMaxConfig();
    leaderConfig.inverted(false); // Adjust based on motor direction
    leaderConfig.smartCurrentLimit(ElevatorConstants.kElevatorSafetyLimits);
    leaderConfig.idleMode(IdleMode.kBrake); // Typically set to brake for elevators
    elevatorLeader.configure(leaderConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SparkMaxConfig followerConfig = new SparkMaxConfig();
    followerConfig.follow(elevatorLeader);
    followerConfig.smartCurrentLimit(ElevatorConstants.kElevatorSafetyLimits);
    followerConfig.idleMode(IdleMode.kBrake);
    elevatorFollower.configure(followerConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    elevatorEncoder.setPosition(0);
  }

  public void setSpeed(double speed) {
    elevatorLeader.set(speed);
  }

  public void stop() {
    elevatorLeader.set(0);
  }

  public double getPositionMeters() {
    return (elevatorEncoder.getPosition() * ElevatorConstants.kElevatorDistancePerRotation);
  }

  public void resetPosition() {
    elevatorEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // Send data to SmartDashboard
    SmartDashboard.putNumber("Elevator Position", getPositionMeters());
  }
}
