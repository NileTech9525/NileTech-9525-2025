package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k3_LowAlgae extends SequentialCommandGroup {
  public k3_LowAlgae(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, -40) // Low Reef: 1.5 meters
    );
  }
} 