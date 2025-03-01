/* package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.CoralShootAuto;
import frc.robot.commands.DriveDistance;
import frc.robot.subsystems.CoralShooter;

public class AutoDriveAndShoot extends SequentialCommandGroup {
  public AutoDriveAndShoot(Drivetrain drive, CoralShooter shooter, Timer timer) {
    addCommands(
      new DriveDistance(drive, 0.8, 2.0), // Drive forward 2 meters
      new CoralShootAuto(shooter, timer, 1) // Shoot coral
    );
  }
}
*/