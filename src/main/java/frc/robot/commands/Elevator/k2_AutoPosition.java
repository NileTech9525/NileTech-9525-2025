 package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k2_AutoPosition extends SequentialCommandGroup {
  public k2_AutoPosition(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, -16.4) // Auto position: 0.5 meters
    );
  }
}
  