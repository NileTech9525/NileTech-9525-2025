package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  
  private final SparkMax elevatorLeader = new SparkMax(ElevatorConstants.kLeaderID, MotorType.kBrushless);
  private final SparkMax elevatorFollower = new SparkMax(ElevatorConstants.kLeaderID, MotorType.kBrushless);
  private final RelativeEncoder elevatorEncoder = elevatorLeader.getEncoder();
  public PIDController pidController = new PIDController(Constants.ElevatorConstants.kP, Constants.ElevatorConstants.kI, Constants.ElevatorConstants.kD);

  public Elevator() {
    SparkMaxConfig leaderConfig = new SparkMaxConfig();
    leaderConfig.inverted(false);
    leaderConfig.smartCurrentLimit(ElevatorConstants.kElevatorSafetyLimits);
    leaderConfig.idleMode(IdleMode.kBrake);
    elevatorLeader.configure(leaderConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SparkMaxConfig followerConfig = new SparkMaxConfig();
    followerConfig.inverted(false);
    followerConfig.smartCurrentLimit(ElevatorConstants.kElevatorSafetyLimits);
    followerConfig.idleMode(IdleMode.kBrake);
    elevatorFollower.configure(followerConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SmartDashboard.putNumber("Elevator kP", Constants.ElevatorConstants.kP);
    SmartDashboard.putNumber("Elevator PID Tolerance", Constants.ElevatorConstants.kPIDTolerance);

    elevatorEncoder.setPosition(0);
  }

  public void moveElevator(double speed) {
    elevatorLeader.set(speed);
  }

  public double getPosition() {
    return elevatorEncoder.getPosition();
  }

  public void stopElevator() {
    elevatorLeader.stopMotor();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Position", getPosition());
  }
}
