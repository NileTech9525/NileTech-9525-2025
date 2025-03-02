package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Auto.DriveAndShoot;
import frc.robot.commands.Claw.ClawIntake;
import frc.robot.commands.Claw.ClawOutake;
import frc.robot.commands.CoralShooter.CoralShoot;
import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Drivetrain.SlowDrive;
import frc.robot.commands.Elevator.k1_GroundPosition;
import frc.robot.commands.Elevator.k2_AutoPosition;
import frc.robot.commands.Elevator.k3_LowAlgae;
import frc.robot.commands.Elevator.k4_HighAlgae;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.CoralShooter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain();
  private final Elevator elevator = new Elevator();
  private final CoralShooter coral = new CoralShooter();
  private final Claw claw = new Claw();
  
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  private final Command DriveAndShoot = new DriveAndShoot(drivetrain, coral);

  SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();

    chooser.setDefaultOption("Drive And Shoot", DriveAndShoot);

    SmartDashboard.putData("Auto choices", chooser);
    SmartDashboard.getNumber("Match Time", DriverStation.getMatchTime());
    SmartDashboard.getNumber("Match Number", DriverStation.getMatchNumber());
  }

  private void configureBindings() {
    // Driver Controller 
    drivetrain.setDefaultCommand(new Drive(drivetrain, driverController));
    driverController.rightBumper().toggleOnTrue(new SlowDrive(drivetrain, driverController));

    // Operator Controller 
    operatorController.leftBumper().whileTrue(new ClawIntake(claw));
    operatorController.rightBumper().whileTrue(new ClawOutake(claw));
    operatorController.povUp().whileTrue(new CoralShoot(coral));
    operatorController.b().onTrue(new k1_GroundPosition(elevator));
    operatorController.x().onTrue(new k2_AutoPosition(elevator));
    operatorController.y().onTrue(new k3_LowAlgae (elevator)); 
    operatorController.a().onTrue(new k4_HighAlgae(elevator)); 
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}