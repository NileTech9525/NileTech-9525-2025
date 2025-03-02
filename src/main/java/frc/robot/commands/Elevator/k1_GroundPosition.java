package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;

public class k1_GroundPosition extends SequentialCommandGroup {
  public k1_GroundPosition(Elevator elevator) {
    double targetPosition = ElevatorConstants.kGroundHeight; 
    double currentPosition = elevator.getPosition(); 

    if (Math.abs(currentPosition - targetPosition) > 0.1) { 
      addCommands(new MoveElevatorToPosition(elevator, targetPosition));
    } else {
      System.out.println("Elevator already at ground position (-2), command not activated.");
    }
  }
}
