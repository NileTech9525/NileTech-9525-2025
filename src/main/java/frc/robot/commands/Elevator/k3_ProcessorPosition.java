package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k3_ProcessorPosition extends SequentialCommandGroup {
  public k3_ProcessorPosition(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, 1.0, 0.5) // Processor position: 1.0 meters
    );
  }
}
