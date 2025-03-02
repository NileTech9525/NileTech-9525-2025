package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class MoveElevatorToPosition extends Command {
  private final Elevator elevator;
  private final double targetPosition;

  public MoveElevatorToPosition(Elevator elevator, double targetPosition) {
    this.elevator = elevator;
    this.targetPosition = targetPosition;
    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    // Prevents activating the command if already at the target position
    if (Math.abs(elevator.getPosition() - targetPosition) <= ElevatorConstants.kPIDTolerance) {
      System.out.println("Elevator already at position: " + targetPosition);
      cancel();
      return;
    }
    
    System.out.println("Moving elevator to position: " + targetPosition);
  }

  @Override
  public void execute() {
    TrapezoidProfile.State goal = new TrapezoidProfile.State(targetPosition, 0);
    double speed = elevator.profiledPID.calculate(elevator.getPosition(), goal);

    // Clamp speed within safe limits
    speed = Math.max(-ElevatorConstants.KElevatorSpeed, Math.min(ElevatorConstants.KElevatorSpeed, speed));

    elevator.moveElevator(speed);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(elevator.getPosition() - targetPosition) <= ElevatorConstants.kPIDTolerance;
  }

  @Override
  public void end(boolean interrupted) {
    elevator.stopElevator();
    System.out.println("Elevator reached target position: " + targetPosition);
  }
}
