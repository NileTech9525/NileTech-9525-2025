package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;

public class k3_LowAlgae extends SequentialCommandGroup {
  public k3_LowAlgae(Elevator elevator) {
    double targetPosition = ElevatorConstants.kLowAlgaeHeight; 
    double currentPosition = elevator.getPosition();

    if (Math.abs(currentPosition - targetPosition) > 0.1) { 
      addCommands(new MoveElevatorToPosition(elevator, targetPosition));
    } else {
      System.out.println("Elevator already at Low Algae (-40), command not activated.");
    }
  }
}
