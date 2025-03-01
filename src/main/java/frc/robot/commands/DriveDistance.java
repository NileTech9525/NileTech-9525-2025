/* package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveDistance extends Command {
  private final Drivetrain driveSubsystem;
  private final double speed;
  private final double timeSeconds;
  private final Timer timer = new Timer();

  public DriveDistance(Drivetrain driveSubsystem, double speed, double timeSeconds) {
    this.driveSubsystem = driveSubsystem;
    this.speed = speed;
    this.timeSeconds = timeSeconds;
    addRequirements(driveSubsystem);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    driveSubsystem.arcadeDrive(speed, 0);
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(timeSeconds);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stop();
    timer.stop();
  }
}
*/