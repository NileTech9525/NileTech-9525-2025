package frc.robot;

import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Drivetrain.SlowDrive;
import frc.robot.commands.Elevator.k1_GroundPosition;
import frc.robot.commands.Elevator.k2_AutoPosition;
import frc.robot.commands.Elevator.k3_ProcessorPosition;
import frc.robot.commands.Elevator.k4_LowReef;
import frc.robot.commands.Elevator.k5_MidReef;
import frc.robot.commands.Claw.AlgaeIntake;
import frc.robot.commands.Claw.AlgaeLaunch;
import frc.robot.commands.Claw.CoralLaunch;
import frc.robot.commands.Climber.Climb;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain();
  private final Elevator elevator = new Elevator();
  private final Claw claw = new Claw();
  private final Vision vision = new Vision();
  private final Climber climber = new Climber();
  
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  public RobotContainer() {
    configureBindings();
    SmartDashboard.putNumber("Match Time", DriverStation.getMatchTime());
  }

  private void configureBindings() {
    // Driver Controller 
    drivetrain.setDefaultCommand(new Drive(drivetrain, driverController));
    driverController.rightTrigger().toggleOnTrue(new SlowDrive(drivetrain, driverController));

    driverController.leftBumper().whileTrue(new AlgaeIntake(claw));
    driverController.rightBumper().whileTrue(new AlgaeLaunch(claw));
    driverController.a().whileTrue(new CoralLaunch(claw));

    // Operator Controller 
    operatorController.leftBumper().onTrue(new k1_GroundPosition(elevator));
    operatorController.b().onTrue(new k2_AutoPosition(elevator));
    operatorController.x().onTrue(new k3_ProcessorPosition(elevator));
    operatorController.y().onTrue(new k4_LowReef (elevator));
    operatorController.a().onTrue(new k5_MidReef(elevator));
    
    operatorController.rightTrigger().onTrue(new Climb(climber, ClimberConstants.kClimberUpSpeed));
    operatorController.leftTrigger().onTrue(new Climb(climber, ClimberConstants.kClimberDownSpeed));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
