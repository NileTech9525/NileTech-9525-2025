package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;

public class MoveElevatorToPosition extends Command {
  private final Elevator elevator;
  private final double targetPositionMeters;
  private final double speed;

  public MoveElevatorToPosition(Elevator elevator, double targetPositionMeters, double speed) {
    this.elevator = elevator;
    this.targetPositionMeters = targetPositionMeters;
    this.speed = Math.abs(speed); // Ensure speed is positive
    addRequirements(elevator); // Declare subsystem dependencies
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double currentPosition = elevator.getPositionMeters();
    double direction = Math.signum(targetPositionMeters - currentPosition);
    elevator.setSpeed(direction * speed);
  }

  @Override
  public boolean isFinished() {
    // Check if the elevator is within the target position tolerance
    return Math.abs(targetPositionMeters - elevator.getPositionMeters()) <= ElevatorConstants.kTolerance;
  }

  @Override
  public void end(boolean interrupted) {
    elevator.stop();
    if (interrupted) {
      System.out.println("Elevator movement interrupted");
    } else {
      System.out.println("Elevator reached target position: " + targetPositionMeters + " meters");
    }
  }
}
