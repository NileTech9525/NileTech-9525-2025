package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k4_HighAlgae extends SequentialCommandGroup {
  public k4_HighAlgae(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, -59) // Mid Reef: 2.0 meters
    );
  }
} 