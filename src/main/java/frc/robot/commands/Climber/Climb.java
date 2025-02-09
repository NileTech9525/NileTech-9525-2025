package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.Constants.ClimberConstants;

public class Climb extends Command {
  private final Climber climber;
  private final double speed;

  public Climb(Climber climber, double speed) {
    this.climber = climber;
    this.speed = speed;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.resetPosition(); 
  }

  @Override
  public void execute() {
    climber.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  @Override
  public boolean isFinished() {
    return climber.getPositionMeters() >= ClimberConstants.kMaxClimbHeight;
  }
}
