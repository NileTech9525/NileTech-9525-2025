package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;


public class Claw extends SubsystemBase {
  
  private final SparkMax clawMotor = new SparkMax(ClawConstants.kClawID, MotorType.kBrushless);
  private final RelativeEncoder clawEncoder = clawMotor.getEncoder();
  private final SparkClosedLoopController clawController = clawMotor.getClosedLoopController();

  public Claw() {
    SparkMaxConfig clawConfig = new SparkMaxConfig();
    clawMotor.configure(clawConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    clawConfig
      .inverted(false)
      .smartCurrentLimit(ClawConstants.kClawSafetyLimits)
      .idleMode(IdleMode.kBrake); // Typically set to brake for claws
    clawConfig.encoder.velocityConversionFactor(ClawConstants.kVelocityConversion);
    clawConfig
      .closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      .p(ClawConstants.kP)
      .i(ClawConstants.kI)
      .d(ClawConstants.kD)
      .velocityFF(ClawConstants.kFF)
      .outputRange(-1, 1);

    clawEncoder.setPosition(0);
  }

  public void setVelocity(double targetVelocity) {
    clawController.setReference(targetVelocity, ControlType.kPosition, ClosedLoopSlot.kSlot0);
  }

  public void stop() {
    clawMotor.set(0);
  }

  public double getVelocity() {
    return (clawEncoder.getVelocity());
  }

  public void resetPosition() {
    clawEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // Send data to SmartDashboard
    SmartDashboard.putNumber("Claw Velocity", getVelocity());
  }
}
