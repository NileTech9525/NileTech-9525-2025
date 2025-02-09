package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k5_MidReef extends SequentialCommandGroup {
  public k5_MidReef(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, 2.0, 0.5) // Mid Reef: 2.0 meters
    );
  }
}
