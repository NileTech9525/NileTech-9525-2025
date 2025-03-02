package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.CoralConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.CoralShooter;

public class DriveAndShoot extends SequentialCommandGroup {

  public DriveAndShoot(Drivetrain drivetrain, CoralShooter coralShooter) {
    addCommands(
      new RunCommand(() -> drivetrain.arcadeDrive(0.5, 0), drivetrain) 
          .withTimeout(AutoConstants.kDriveTime)
          .andThen(() -> drivetrain.stop())
          .handleInterrupt(() -> drivetrain.stop()),

      new RunCommand(() -> coralShooter.setSpeed(CoralConstants.kCoralSpeed), coralShooter) 
          .withTimeout(AutoConstants.kCoralTime) 
          .andThen(() -> coralShooter.stop())
          .handleInterrupt(() -> coralShooter.stop()) 
    );
  }
}
