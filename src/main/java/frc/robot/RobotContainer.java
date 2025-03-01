package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CoralShooter.CoralShoot;
import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Drivetrain.ReverseDrive;
import frc.robot.commands.Drivetrain.SlowDrive;
import frc.robot.commands.Elevator.k1_GroundPosition;
import frc.robot.commands.Elevator.k2_AutoPosition;
import frc.robot.commands.Elevator.k3_LowAlgae;
import frc.robot.commands.Elevator.k4_HighAlgae;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.CoralShooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain();
  private final Elevator elevator = new Elevator();
  private final CoralShooter coral = new CoralShooter();
  
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Driver Controller 
    drivetrain.setDefaultCommand(new Drive(drivetrain, driverController));
    driverController.rightBumper().toggleOnTrue(new SlowDrive(drivetrain, driverController));
    driverController.rightTrigger().toggleOnTrue(new ReverseDrive(drivetrain, driverController));
    driverController.leftBumper().whileTrue(new CoralShoot(coral));

    // Operator Controller 
    driverController.b().onTrue(new k1_GroundPosition(elevator));
    driverController.x().onTrue(new k2_AutoPosition(elevator));
    driverController.y().onTrue(new k3_LowAlgae (elevator)); 
    driverController.a().onTrue(new k4_HighAlgae(elevator)); 
  }

  public Command getAutonomousCommand() {
    return null;
  }
}