package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k4_LowReef extends SequentialCommandGroup {
  public k4_LowReef(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, 1.5, 0.5) // Low Reef: 1.5 meters
    );
  }
}
