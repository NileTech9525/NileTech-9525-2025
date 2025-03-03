package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  
  private final SparkMax elevatorLeader = new SparkMax(ElevatorConstants.kLeaderID, MotorType.kBrushless);
  private final SparkMax elevatorFollower = new SparkMax(ElevatorConstants.kFollowerID, MotorType.kBrushless);
  private final RelativeEncoder elevatorEncoder = elevatorLeader.getEncoder();

  // Trapezoidal Motion Profile Constraints
  private final TrapezoidProfile.Constraints constraints =
      new TrapezoidProfile.Constraints(ElevatorConstants.kMaxVelocity, ElevatorConstants.kMaxAcceleration);

  // Profiled PID Controller for smoother motion
  public final ProfiledPIDController profiledPID = new ProfiledPIDController(
      Constants.ElevatorConstants.kP, 
      Constants.ElevatorConstants.kI, 
      Constants.ElevatorConstants.kD, 
      constraints
  );

  public Elevator() {
    SparkMaxConfig leaderConfig = new SparkMaxConfig();
    leaderConfig.inverted(false);
    leaderConfig.smartCurrentLimit(ElevatorConstants.kElevatorSafetyLimits);
    leaderConfig.idleMode(IdleMode.kBrake);
    leaderConfig.softLimit.forwardSoftLimit(ElevatorConstants.kMaxPosition);
    leaderConfig.softLimit.reverseSoftLimit(ElevatorConstants.kMinPosition);
    leaderConfig.softLimit.forwardSoftLimitEnabled(true);
    leaderConfig.softLimit.reverseSoftLimitEnabled(true);
    leaderConfig.voltageCompensation(DrivetrainConstants.KVoltage);
    leaderConfig.openLoopRampRate(ElevatorConstants.kOpenRateLimit);
    elevatorLeader.configure(leaderConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SparkMaxConfig followerConfig = new SparkMaxConfig();
    followerConfig.inverted(true);
    followerConfig.smartCurrentLimit(ElevatorConstants.kElevatorSafetyLimits);
    followerConfig.idleMode(IdleMode.kBrake);
    followerConfig.voltageCompensation(DrivetrainConstants.KVoltage);
    elevatorFollower.configure(followerConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
          
    SmartDashboard.putNumber("Elevator kP", Constants.ElevatorConstants.kP);
    SmartDashboard.putNumber("Elevator kI", Constants.ElevatorConstants.kI);
    SmartDashboard.putNumber("Elevator kD", Constants.ElevatorConstants.kD);

    elevatorEncoder.setPosition(0);
    
    // Set tolerance for stopping
    profiledPID.setTolerance(ElevatorConstants.kPIDTolerance);
  }

  public void moveElevator(double speed) {
    elevatorLeader.set(speed);
    elevatorFollower.set(-speed);
  }

  public double getPosition() {
    return elevatorEncoder.getPosition();
  }

  public void stopElevator() {
    elevatorLeader.stopMotor();
  }

  public void moveElevatorToPosition(double targetPosition) {
    TrapezoidProfile.State goal = new TrapezoidProfile.State(targetPosition, 0);
    double speed = profiledPID.calculate(getPosition(), goal);
    
    // Limit speed to safe range
    speed = Math.max(-ElevatorConstants.KElevatorSpeed, Math.min(ElevatorConstants.KElevatorSpeed, speed));

    moveElevator(speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.getNumber("Position", getPosition());
    SmartDashboard.putNumber("Elevator kP", Constants.ElevatorConstants.kP);
    SmartDashboard.putNumber("Elevator kI", Constants.ElevatorConstants.kI);
    SmartDashboard.putNumber("Elevator kD", Constants.ElevatorConstants.kD);
  }
}
