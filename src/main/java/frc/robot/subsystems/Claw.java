package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;


public class Claw extends SubsystemBase {
  private final SparkMax clawRightMotor = new SparkMax(ClawConstants.kClawRightID, MotorType.kBrushless);
  private final SparkMax clawLeftMotor = new SparkMax(ClawConstants.kClawLeftID, MotorType.kBrushless);

  public Claw() {
    SparkMaxConfig clawRightConfig = new SparkMaxConfig();
    clawRightMotor.configure(clawRightConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    clawRightConfig
      .inverted(false)
      .smartCurrentLimit(ClawConstants.kClawSafetyLimits)
      .idleMode(IdleMode.kBrake); 

      SparkMaxConfig clawLeftConfig = new SparkMaxConfig();
    clawRightMotor.configure(clawLeftConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    clawLeftConfig
      .inverted(true)
      .smartCurrentLimit(ClawConstants.kClawSafetyLimits)
      .idleMode(IdleMode.kBrake); 
  }

  public void stop() {
    clawRightMotor.set(0);
    clawLeftMotor.set(0);
  }

  public void setSpeed(double speed) {
    clawRightMotor.set(speed);
    clawLeftMotor.set(speed);
  }

  @Override
  public void periodic() {}
}
