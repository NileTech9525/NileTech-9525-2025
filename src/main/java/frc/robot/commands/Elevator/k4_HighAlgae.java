package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;

public class k4_HighAlgae extends SequentialCommandGroup {
  public k4_HighAlgae(Elevator elevator) {
    double targetPosition = ElevatorConstants.kHighAlgaeHeight;
    double currentPosition = elevator.getPosition();

    if (Math.abs(currentPosition - targetPosition) > 0.1) { 
      addCommands(new MoveElevatorToPosition(elevator, targetPosition));
    } else {
      System.out.println("Elevator already at High Algae (-59), command not activated.");
    }
  }
}
